# BaseEnum 枚举持久化使用指南

## 概述

`BaseEnum` 是 cartisan-boot 框架提供的业务枚举基类，实现了枚举与数据库之间的自动转换，解决了以下问题：

- **避免 ordinal 存储导致的数据错乱**：使用 code 值而非 Java ordinal 存储到数据库
- **自动序列化/反序列化**：JPA、Jackson、Spring MVC 自动转换
- **类型安全**：编译时类型检查，避免无效值

## 为什么需要 BaseEnum？

### 问题：Java 默认的 Enum 存储方式

Java 枚举默认使用 `ordinal()`（序号）存储到数据库：

```java
// ❌ 错误示例
public enum UserStatus {
    ACTIVE,    // ordinal = 0
    DISABLED   // ordinal = 1
}

// 问题1：增删枚举值会导致已有数据错乱
// 问题2：调整枚举顺序会导致数据映射错误
```

### 解决方案：BaseEnum 使用 code 值存储

```java
// ✅ 正确示例
public enum UserStatus implements BaseEnum<UserStatus> {
    ACTIVE(1, "激活"),
    DISABLED(0, "禁用");

    private final Integer code;
    private final String name;

    UserStatus(Integer code, String name) {
        this.code = code;
        this.name = name;
    }

    @Override
    public Integer getCode() { return code; }

    @Override
    public String getName() { return name; }
}
```

**优势：**
- 数据库存储 code 值（1, 0），不依赖枚举声明顺序
- 增删枚举值不影响已有数据
- code 值具有业务含义

## 快速开始

### 1. 定义枚举

```java
package com.example.domain;

import com.cartisan.core.domain.BaseEnum;
import com.cartisan.data.jpa.converter.BaseEnumConverter;
import jakarta.persistence.Converter;

public enum OrderStatus implements BaseEnum<OrderStatus> {
    PENDING(1, "待支付"),
    PAID(2, "已支付"),
    CANCELLED(3, "已取消");

    private final Integer code;
    private final String name;

    OrderStatus(Integer code, String name) {
        this.code = code;
        this.name = name;
    }

    @Override
    public Integer getCode() {
        return code;
    }

    @Override
    public String getName() {
        return name;
    }

    /**
     * JPA Converter - 必须声明为 public static class
     * <p>
     * 通过 @Converter(autoApply = true) 让 Hibernate 自动应用到所有
     * OrderStatus 类型的字段，无需在实体类中显式标注。
     * <p>
     * <b>重要提示：</b>内部类名使用 {@code JpaConverter} 而非 {@code Converter}，
     * 避免与 {@code @Converter} 注解产生命名冲突。
     */
    @Converter(autoApply = true)
    public static class JpaConverter extends BaseEnumConverter<OrderStatus> {
        public JpaConverter() {
            super(OrderStatus.class);
        }
    }
}
```

### 2. 实体类使用

```java
import jakarta.persistence.*;

@Entity
@Table(name = "orders")
public class Order {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // 无需 @Convert 或 @Enumerated 注解
    // OrderStatus.JpaConverter 会自动应用
    private OrderStatus status;

    private OrderType type;

    // Getters and Setters
}
```

**关键点：**
- 实体字段**无需任何注解**
- Hibernate 自动扫描并应用 `@Converter(autoApply = true)`
- 数据库存储 code 值（1, 2, 3），不是 ordinal（0, 1, 2）

### 3. 验证持久化

```java
@DataJpaTest
class OrderRepositoryTest {

    @Autowired
    private OrderRepository orderRepository;

    @Test
    void shouldSaveAndLoadEnumWithCorrectCode() {
        // Given
        Order order = new Order();
        order.setStatus(OrderStatus.PAID); // code = 2

        // When
        orderRepository.save(order);
        Order loaded = orderRepository.findById(order.getId()).orElseThrow();

        // Then
        assertThat(loaded.getStatus()).isEqualTo(OrderStatus.PAID);
        // 数据库存储的是 code 值 2，不是 ordinal 1
    }
}
```

## 架构说明

### BaseEnumConverter 抽象基类

```java
public abstract class BaseEnumConverter<E extends Enum<E> & BaseEnum<E>>
        implements AttributeConverter<E, Integer> {

    private final Class<E> enumType;

    protected BaseEnumConverter(Class<E> enumType) {
        this.enumType = enumType;
    }

    @Override
    public Integer convertToDatabaseColumn(E attribute) {
        if (attribute == null) {
            return null;
        }
        return attribute.getCode();
    }

    @Override
    public E convertToEntityAttribute(Integer dbData) {
        if (dbData == null) {
            return null;
        }
        return BaseEnum.requireByCode(enumType, dbData);
    }
}
```

**职责：**
- 封装 BaseEnum 与 Integer 的转换逻辑
- 提供类型安全的泛型支持
- 作为所有枚举 Converter 的基类

### 自动转换机制

1. **Hibernate 扫描**：启动时扫描 `@Converter` 注解
2. **自动应用**：发现 `autoApply = true` 时，自动应用到对应类型的字段
3. **类型匹配**：根据枚举类型匹配对应的 Converter
4. **双向转换**：
   - 存储时：`枚举 → code`（通过 `getCode()`）
   - 读取时：`code → 枚举`（通过 `requireByCode()`）

## 重要注意事项

### ⚠️ 命名冲突问题

**问题：** Java 不允许使用 `Converter` 作为内部类名

```java
// ❌ 编译错误！
@Converter(autoApply = true)
public static class Converter extends BaseEnumConverter<OrderStatus> {
    // Error: 不兼容的类型: OrderStatus.Converter无法转换为java.lang.annotation.Annotation
}
```

**原因：** 内部类名 `Converter` 与 `@Converter` 注解冲突

**解决方案：** 使用 `JpaConverter` 作为内部类名

```java
// ✅ 正确写法
@Converter(autoApply = true)
public static class JpaConverter extends BaseEnumConverter<OrderStatus> {
    public JpaConverter() {
        super(OrderStatus.class);
    }
}
```

### code = 0 的边缘情况

**注意：** code 值为 0 是合法的，但需要特别注意：

```java
public enum AdminUserStatus implements BaseEnum<AdminUserStatus> {
    ACTIVE(1, "激活"),    // ordinal = 0, code = 1
    DISABLED(0, "禁用");  // ordinal = 1, code = 0

    // ...
}
```

**数据库存储：**
- `ACTIVE` → 存储为 `1`
- `DISABLED` → 存储为 `0`（不是 ordinal 的 1）

**测试验证：**
```java
@Test
void shouldHandleCodeZero() {
    // Given: DISABLED(0, "禁用")
    // ordinal = 1（第二个声明）
    // code = 0

    AdminUser user = new AdminUser();
    user.setStatus(AdminUserStatus.DISABLED);

    // When
    repository.save(user);
    AdminUser loaded = repository.findById(user.getId()).orElseThrow();

    // Then
    assertThat(loaded.getStatus()).isEqualTo(AdminUserStatus.DISABLED);

    // 验证数据库存储的是 code=0，不是 ordinal=1
    Integer statusInDb = (Integer) entityManager.getEntityManager()
        .createNativeQuery("SELECT status FROM admin_users WHERE id = ?")
        .setParameter(1, user.getId())
        .getSingleResult();

    assertThat(statusInDb).isEqualTo(0); // ✅ code 值
}
```

### 无效 code 处理

如果数据库中存在无效的 code 值，会抛出异常：

```java
// 数据库中 status = 999（无效值）
Order order = repository.findById(1L);

// 抛出异常：
// IllegalArgumentException: Unknown code: 999
```

**建议：**
- 在数据库层面添加外键约束或检查约束
- 确保数据库中的枚举值都是有效的
- 考虑使用 `@NotNull` 约束

## 迁移指南

### 从旧的 @EnumConvert 模式迁移

**旧代码（已废弃）：**

```java
// ❌ 旧模式（已删除）
@Entity
public class Order {
    @EnumConvert(OrderStatus.class)  // 这个注解已删除
    private OrderStatus status;
}

public enum OrderStatus implements BaseEnum<OrderStatus> {
    PENDING(1, "待支付"),
    PAID(2, "已支付");
    // ...
}
```

**新代码：**

```java
// ✅ 新模式
@Entity
public class Order {
    // 删除 @EnumConvert 注解
    private OrderStatus status;
}

public enum OrderStatus implements BaseEnum<OrderStatus> {
    PENDING(1, "待支付"),
    PAID(2, "已支付");

    // ...

    @Converter(autoApply = true)
    public static class JpaConverter extends BaseEnumConverter<OrderStatus> {
        public JpaConverter() {
            super(OrderStatus.class);
        }
    }
}
```

### 迁移步骤

1. **为枚举添加内部 JpaConverter 类**
   ```java
   @Converter(autoApply = true)
   public static class JpaConverter extends BaseEnumConverter<OrderStatus> {
       public JpaConverter() {
           super(OrderStatus.class);
       }
   }
   ```

2. **删除实体类中的 @EnumConvert 注解**
   ```java
   // 删除这行
   // @EnumConvert(OrderStatus.class)
   private OrderStatus status;
   ```

3. **运行测试验证**
   ```bash
   mvn test -Dtest=OrderRepositoryTest
   ```

4. **检查数据库存储**
   ```sql
   SELECT status FROM orders;  -- 应该显示 code 值（1, 2, 3）
   ```

## 最佳实践

### 1. code 值设计

**推荐：**
- 使用正整数（1, 2, 3）作为 code 值
- code 值应该具有业务含义
- 避免使用负数或过大的值

**示例：**
```java
public enum OrderStatus implements BaseEnum<OrderStatus> {
    PENDING(1, "待支付"),
    PAID(2, "已支付"),
    SHIPPED(3, "已发货"),
    DELIVERED(4, "已送达"),
    CANCELLED(5, "已取消");
}
```

### 2. 枚举命名

**推荐：**
- 枚举类型使用业务名称
- 内部 Converter 类统一使用 `JpaConverter`
- 保持命名一致性

```java
public enum PaymentStatus implements BaseEnum<PaymentStatus> {
    // ...

    @Converter(autoApply = true)
    public static class JpaConverter extends BaseEnumConverter<PaymentStatus> {
        // ...
    }
}
```

### 3. 测试覆盖

**单元测试：**
```java
@Test
void shouldConvertEnumToCode() {
    PaymentStatus.JpaConverter converter = new PaymentStatus.JpaConverter();

    assertThat(converter.convertToDatabaseColumn(PaymentStatus.SUCCESS))
        .isEqualTo(2);
}
```

**集成测试：**
```java
@DataJpaTest
class PaymentRepositoryTest {
    @Test
    void shouldSaveAndLoadPayment() {
        // Given
        Payment payment = new Payment();
        payment.setStatus(PaymentStatus.SUCCESS);

        // When
        repository.save(payment);
        Payment loaded = repository.findById(payment.getId()).orElseThrow();

        // Then
        assertThat(loaded.getStatus()).isEqualTo(PaymentStatus.SUCCESS);

        // 验证数据库存储的是 code 值
        Integer statusInDb = entityManager.getEntityManager()
            .createNativeQuery("SELECT status FROM payments WHERE id = ?")
            .setParameter(1, payment.getId())
            .getSingleResult();

        assertThat(statusInDb).isEqualTo(2); // SUCCESS.getCode()
    }
}
```

## 常见问题

### Q1: 为什么要使用内部 Converter 类，而不是独立的 Converter 文件？

**A:** 内部类模式有以下优势：
- **高内聚**：枚举和它的持久化规则在同一文件
- **易维护**：删除枚举时，Converter 不会被遗漏
- **简洁**：只需 3 行代码，无需额外文件
- **类型安全**：编译时类型检查

### Q2: 可以使用其他名称作为内部类名吗？

**A:** 可以，但不能使用 `Converter`。推荐使用 `JpaConverter`：
- ✅ `JpaConverter` - 推荐
- ✅ `EnumConverter` - 可接受
- ❌ `Converter` - 编译错误（与注解冲突）

### Q3: 如何验证数据库存储的是 code 值？

**A:** 使用原生 SQL 查询：

```java
Integer statusInDb = (Integer) entityManager.getEntityManager()
    .createNativeQuery("SELECT status FROM orders WHERE id = ?")
    .setParameter(1, orderId)
    .getSingleResult();

assertThat(statusInDb).isEqualTo(2); // 期望的 code 值
```

### Q4: code 值可以是 0 吗？

**A:** 可以，但需要特别注意。code=0 是合法的：
- 数据库存储：`0`
- 不是 Java ordinal（可能是 1）
- 测试时需要验证数据库实际存储的值

### Q5: 如何处理枚举值的增删？

**A:** 因为使用 code 值存储，增删枚举值相对安全：

**新增枚举值：**
```java
public enum OrderStatus implements BaseEnum<OrderStatus> {
    PENDING(1, "待支付"),
    PAID(2, "已支付"),
    REFUNDING(3, "退款中"),  // 新增
    REFUNDED(4, "已退款");   // 新增
}
```
- ✅ 不影响已有数据
- ✅ 新 code 值不会与旧数据冲突

**删除枚举值：**
```java
// 删除前
public enum OrderStatus implements BaseEnum<OrderStatus> {
    PENDING(1, "待支付"),
    PAID(2, "已支付"),
    CANCELLED(3, "已取消");
}

// 删除后（需要先处理数据库中的 CANCELLED 订单）
public enum OrderStatus implements BaseEnum<OrderStatus> {
    PENDING(1, "待支付"),
    PAID(2, "已支付");
}
```
- ⚠️ 需要先更新或删除数据库中引用该值的记录
- ⚠️ 考虑使用软删除而非硬删除

## 相关文档

- **编码规范**：[PITFALLS.md](../../PITFALLS.md) - 规则 DATA-007
- **BaseEnum API**：[BaseEnum.java](../../cartisan-core/src/main/java/com/cartisan/core/domain/BaseEnum.java)
- **BaseEnumConverter API**：[BaseEnumConverter.java](../../cartisan-data-jpa/src/main/java/com/cartisan/data/jpa/converter/BaseEnumConverter.java)
- **集成测试示例**：[BaseEnumJpaIntegrationTest.java](../../cartisan-data-jpa/src/test/java/com/cartisan/data/jpa/integration/BaseEnumJpaIntegrationTest.java)

## 版本历史

- **2026-04-07**: 初始版本
  - 从 `@EnumConvert` 扫描机制迁移到内部 `JpaConverter` 模式
  - 修复 Java 命名冲突问题（使用 `JpaConverter` 而非 `Converter`）
  - 添加完整的测试覆盖和文档
