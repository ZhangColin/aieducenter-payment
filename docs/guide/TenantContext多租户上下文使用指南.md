# TenantContext 多租户上下文使用指南

> **适用版本**：cartisan-boot 0.3.0+
> **最后更新**：2026-04-07

## 一、什么是 TenantContext？

**TenantContext** 是一个多租户上下文工具类，用于在单个请求中存储和访问当前租户的 ID。

```
┌─────────────────────────────────────────────────────┐
│  HTTP 请求                                          │
│  Header: X-Tenant-Id: 123                           │
├─────────────────────────────────────────────────────┤
│  TenantContextFilter 拦截                           │
│  ↓ 解析租户 ID → 123                                │
│  ↓ 绑定到 TenantContext                             │
├─────────────────────────────────────────────────────┤
│  Controller → Service → Repository                 │
│  ↓                                                  │
│  TenantContext.getCurrentTenantId() → 返回 123      │
│  用于数据隔离、权限控制等                           │
└─────────────────────────────────────────────────────┘
```

## 二、解决什么问题？

### 业务场景

SaaS 平台需要为不同租户（企业/组织）隔离数据：

- **租户 A** 的用户只能看到 **租户 A** 的订单
- **租户 B** 的用户只能看到 **租户 B** 的订单
- 管理员可以跨租户查看数据

### 问题

如何在业务代码中知道"当前请求属于哪个租户"？

### 方案

不用在每个方法参数都传 `tenantId`，而是从 **TenantContext** 上下文中获取：

```java
// ❌ 不推荐：每个方法都传 tenantId
public void createOrder(Long tenantId, CreateOrderRequest request) { }

// ✅ 推荐：从上下文获取
public void createOrder(CreateOrderRequest request) {
    Long tenantId = TenantContext.requireTenant();
}
```

## 三、工作原理

### 3.1 租户 ID 解析优先级

`TenantContextFilter` 按以下优先级解析租户 ID：

```
1. 优先：HTTP Header
   Header: X-Tenant-Id: 123

2. 其次：Sa-Token Session
   StpUtil.getSession().get("tenantId")

3. 都没有：返回 null（无租户上下文）
```

### 3.2 生命周期

```java
// 请求开始
TenantContextFilter.doFilter() {
    Long tenantId = resolveTenantId(request);  // 123

    // 绑定租户 ID 到当前线程
    TenantContext.runWithTenant(tenantId, () -> {
        // ===== 在这个作用域内，所有代码都能访问租户 ID =====
        chain.doFilter(request, response);
        // ↑ Controller → Service → Repository
        // ↑ 都可以调用 TenantContext.getCurrentTenantId()
        // ===== 作用域结束，自动清理 ======================
    });
}
```

**关键特性**：
- **自动绑定**：Filter 自动解析并绑定租户 ID
- **自动清理**：请求结束后自动清理，无需手动操作
- **线程安全**：每个请求线程独立的租户上下文

## 四、API 使用方法

### 4.1 获取租户 ID（可选）

```java
@Service
public class OrderService {

    public void createOrder(CreateOrderRequest request) {
        // 获取租户 ID（可能为 null）
        Long tenantId = TenantContext.getCurrentTenantId();

        if (tenantId != null) {
            // 有租户：使用租户 ID 创建订单
            Order order = new Order(tenantId, request);
            orderRepository.save(order);
        } else {
            // 无租户：可能是管理员或系统操作
            Order order = new Order(null, request);
            orderRepository.save(order);
        }
    }
}
```

### 4.2 获取租户 ID（强制）

```java
@Service
public class OrderService {

    public void deleteOrder(Long orderId) {
        // 强制必须有租户上下文
        Long tenantId = TenantContext.requireTenant();
        // ↑ 如果没有租户，抛出 IllegalStateException

        // 只能删除自己租户的订单
        Order order = orderRepository
            .findByIdAndTenantId(orderId, tenantId)
            .orElseThrow(() -> new NotFoundException("订单不存在"));

        orderRepository.delete(order);
    }
}
```

### 4.3 判断是否有租户

```java
@Service
public class ReportService {

    public void generateReport() {
        if (TenantContext.hasTenant()) {
            // 生成租户专属报告
            generateTenantReport();
        } else {
            // 生成全局报告（管理员）
            generateGlobalReport();
        }
    }
}
```

## 五、jOOQ 查询自动过滤

### 5.1 使用 `eqTenantId` 辅助方法

```java
import static com.cartisan.data.query.support.JooqTenantSupport.eqTenantId;

@Service
public class UserService {
    private final DSLContext dsl;

    // 自动添加租户过滤
    public List<User> listUsers() {
        return dsl.selectFrom(USER)
            .where(eqTenantId(USER.TENANT_ID))  // 关键！
            .fetchInto(User.class);
    }

    // eqTenantId 的行为：
    // - 有租户上下文：WHERE tenant_id = 123
    // - 无租户上下文：不添加这个条件（查所有）
}
```

### 5.2 组合条件查询

```java
@Service
public class UserService {

    // 租户过滤 + 其他条件
    public List<User> listActiveUsers() {
        return dsl.selectFrom(USER)
            .where(
                USER.STATUS.eq("ACTIVE")
                .and(eqTenantId(USER.TENANT_ID))
            )
            .fetchInto(User.class);
    }
}
```

### 5.3 管理员场景

```java
@Service
public class UserService {

    // 无租户上下文时，eqTenantId 返回 noCondition()
    // 管理员可以查询所有用户
    public List<User> listAllUsersForAdmin() {
        return dsl.selectFrom(USER)
            .where(eqTenantId(USER.TENANT_ID))
            .fetchInto(User.class);
    }
}
```

## 六、业务平台集成步骤

### 6.1 确保 Filter 已注册

```java
@Configuration
public class WebConfig {

    @Bean
    public FilterRegistrationBean<TenantContextFilter> tenantFilter() {
        FilterRegistrationBean<TenantContextFilter> reg = new FilterRegistrationBean<>();
        reg.setFilter(new TenantContextFilter());
        reg.addUrlPatterns("/*");
        reg.setOrder(1);  // 尽早执行
        return reg;
    }
}
```

### 6.2 客户端传递租户 ID

**方式 A：HTTP Header（推荐）**

```javascript
// 前端在请求中添加 Header
fetch('/api/orders', {
    headers: {
        'X-Tenant-Id': '123',
        'Authorization': 'Bearer ' + token
    }
})
```

```java
// axios 拦截器自动添加
axios.interceptors.request.use(config => {
    const tenantId = localStorage.getItem('tenantId');
    if (tenantId) {
        config.headers['X-Tenant-Id'] = tenantId;
    }
    return config;
});
```

**方式 B：登录时存入 Session**

```java
@PostMapping("/login")
public ApiResponse<TokenInfo> login(LoginRequest request) {
    User user = authService.validateUser(request);

    // 登录成功，保存租户 ID 到 Session
    StpUtil.getSession().set("tenantId", user.getTenantId());

    return ApiResponse.ok(tokenInfo);
}

// 后续请求自动从 Session 读取租户 ID
```

### 6.3 数据库表设计

每个需要租户隔离的表都要有 `tenant_id` 字段：

```sql
CREATE TABLE users (
    id BIGINT PRIMARY KEY,
    tenant_id BIGINT NOT NULL,  -- 租户 ID
    username VARCHAR(50),
    ...
    INDEX idx_tenant_id (tenant_id)
);

CREATE TABLE orders (
    id BIGINT PRIMARY KEY,
    tenant_id BIGINT NOT NULL,  -- 租户 ID
    user_id BIGINT,
    ...
    INDEX idx_tenant_id (tenant_id)
);

-- jOOQ 代码生成后，自动生成 USER.TENANT_ID、ORDERS.TENANT_ID 等字段
```

## 七、实际使用示例

### 7.1 订单服务（租户隔离）

```java
@Service
public class OrderService {
    private final OrderRepository orderRepository;
    private final DSLContext dsl;

    // 创建订单（自动关联当前租户）
    public Order createOrder(CreateOrderRequest request) {
        Long tenantId = TenantContext.requireTenant();

        Order order = new Order();
        order.setTenantId(tenantId);  // 关键！
        order.setUserId(request.getUserId());
        order.setAmount(request.getAmount());

        return orderRepository.save(order);
    }

    // 查询订单（只查当前租户的）
    public List<Order> listOrders() {
        Long tenantId = TenantContext.requireTenant();
        return orderRepository.findByTenantId(tenantId);
    }

    // 使用 jOOQ 自动过滤
    public List<Order> listOrdersWithJooq() {
        return dsl.selectFrom(ORDERS)
            .where(eqTenantId(ORDERS.TENANT_ID))
            .fetchInto(Order.class);
    }

    // 删除订单（只能删除自己租户的）
    public void deleteOrder(Long orderId) {
        Long tenantId = TenantContext.requireTenant();

        Order order = orderRepository
            .findByIdAndTenantId(orderId, tenantId)
            .orElseThrow(() -> new NotFoundException("订单不存在"));

        orderRepository.delete(order);
    }
}
```

### 7.2 混合场景（管理员 + 租户）

```java
@Service
public class UserService {

    // 管理员可以查所有用户，租户只能查自己的
    public List<User> listUsers() {
        Long tenantId = TenantContext.getCurrentTenantId();

        if (tenantId != null) {
            // 租户用户：只查自己租户的
            return userRepository.findByTenantId(tenantId);
        } else {
            // 管理员：查所有用户
            return userRepository.findAll();
        }
    }

    // 使用 jOOQ 更简洁
    public List<User> listUsersWithJooq() {
        return dsl.selectFrom(USER)
            .where(eqTenantId(USER.TENANT_ID))  // 自动处理有无租户
            .fetchInto(User.class);
    }
}
```

### 7.3 跨服务调用

```java
@Service
public class OrderService {
    private final UserServiceClient userClient;

    public OrderDTO createOrder(CreateOrderRequest request) {
        Long tenantId = TenantContext.requireTenant();

        // 调用其他服务时，需要传递租户 ID
        UserDTO user = userClient.getUser(tenantId, request.getUserId());

        Order order = new Order(tenantId, user, request);
        return orderRepository.save(order);
    }
}
```

## 八、调试技巧

### 8.1 调试接口

```java
@RestController
@RequestMapping("/debug")
public class DebugController {

    @GetMapping("/tenant")
    public ApiResponse<Map<String, Object>> getCurrentTenant() {
        Long tenantId = TenantContext.getCurrentTenantId();

        return ApiResponse.ok(Map.of(
            "tenantId", tenantId,
            "hasTenant", TenantContext.hasTenant(),
            "message", tenantId != null
                ? "当前租户: " + tenantId
                : "无租户上下文"
        ));
    }
}
```

### 8.2 日志输出

```java
@Service
public class OrderService {
    private static final Logger log = LoggerFactory.getLogger(OrderService.class);

    public void createOrder(CreateOrderRequest request) {
        Long tenantId = TenantContext.getCurrentTenantId();
        log.info("Creating order for tenant: {}", tenantId);

        // ...
    }
}
```

## 九、测试

### 9.1 单元测试

```java
@Test
void testCreateOrder() {
    // 设置租户上下文
    TenantContext.runWithTenantId(123L, () -> {
        // 在这个作用域内，getCurrentTenantId() 返回 123
        Order order = orderService.createOrder(request);

        assertEquals(123L, order.getTenantId());
    });

    // 作用域外，getCurrentTenantId() 返回 null
    assertNull(TenantContext.getCurrentTenantId());
}
```

### 9.2 集成测试

```java
@SpringBootTest
@AutoConfigureMockMvc
class OrderControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Test
    void testListOrders() throws Exception {
        // 设置租户 Header
        mockMvc.perform(get("/api/orders")
                .header("X-Tenant-Id", "123"))
            .andExpect(status().isOk());
    }
}
```

## 十、常见问题

### Q1：什么时候没有租户上下文？

**答**：
- 未登录的公开接口
- 管理员跨租户操作
- 系统内部任务
- 前端未传递 `X-Tenant-Id` Header

### Q2：`tenant_id` 字段必须 NOT NULL 吗？

**答**：
- **建议是 NOT NULL**（强制租户隔离，避免数据混淆）
- 如果有系统级数据（如配置表），可以允许 NULL
- 根据业务场景决定

### Q3：如何处理历史数据（没有 tenant_id）？

**答**：
```sql
-- 为历史数据设置默认租户
UPDATE orders SET tenant_id = 0 WHERE tenant_id IS NULL;

-- 然后设置字段为 NOT NULL
ALTER TABLE orders MODIFY COLUMN tenant_id BIGINT NOT NULL;
```

### Q4：Virtual Threads 兼容性？

**答**：
- 当前实现使用 **ScopedValue**（Java 21 预览 API）
- 兼容 Virtual Threads，租户上下文自动传递给子任务
- 如需移除 `--enable-preview`，可替换为 ThreadLocal（参见 PITFALLS.md）

### Q5：性能影响？

**答**：
- **几乎无性能开销**：ThreadLocal/ScopedValue 访问非常快
- **不影响连接池**：租户上下文是线程级别的，不影响数据库连接复用
- **索引优化**：确保 `tenant_id` 字段有索引

### Q6：如何切换租户？

**答**：
```javascript
// 前端切换租户
function switchTenant(tenantId) {
    localStorage.setItem('tenantId', tenantId);
    // 更新后续请求的 Header
    axios.defaults.headers.common['X-Tenant-Id'] = tenantId;
    // 刷新页面或重新加载数据
    location.reload();
}
```

## 十一、最佳实践

### ✅ 推荐做法

1. **Repository 层强制过滤**
   ```java
   public List<Order> findByTenantId(Long tenantId) {
       return repository.findByTenantId(
           TenantContext.requireTenant()
       );
   }
   ```

2. **使用 jOOQ 辅助方法**
   ```java
   .where(eqTenantId(ORDERS.TENANT_ID))
   ```

3. **关键业务使用 requireTenant()**
   ```java
   Long tenantId = TenantContext.requireTenant();
   ```

### ❌ 避免做法

1. **不要在 Repository 参数传 tenantId**
   ```java
   // ❌ 不推荐
   Order findById(Long id, Long tenantId);

   // ✅ 推荐
   Order findByIdAndTenantId(Long id, Long tenantId);
   ```

2. **不要忘记索引**
   ```sql
   -- ❌ 忘记建索引
   CREATE TABLE orders (tenant_id BIGINT);

   -- ✅ 建索引
   CREATE INDEX idx_tenant_id ON orders(tenant_id);
   ```

3. **不要在循环中调用 getCurrentTenantId()**
   ```java
   // ❌ 不必要
   for (Order order : orders) {
       Long tenantId = TenantContext.getCurrentTenantId();
   }

   // ✅ 提取到循环外
   Long tenantId = TenantContext.getCurrentTenantId();
   for (Order order : orders) {
       // ...
   }
   ```

## 十二、相关文档

- **PITFALLS.md**：SECURITY-003、QUERY-003
- **设计文档**：cartisan-security 模块架构
- **API 文档**：TenantContext、TenantContextFilter

---

**文档维护**：如有疑问或更新建议，请联系架构团队。
