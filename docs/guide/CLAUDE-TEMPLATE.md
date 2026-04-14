# CLAUDE.md 模板

> 复制本文件到项目根目录，重命名为 CLAUDE.md，按注释填写后删除注释。
> 目标：30 行以内，只放 AI 每次对话都需要的核心信息。

---

# {项目名称}

{一句话描述项目是什么、做什么。}

## 技术栈

{列出主要技术，不超过 5 行。示例：}
- Java 21 / Spring Boot 3.4.x / Maven
- 数据库：PostgreSQL + Redis
- 测试：JUnit 5 + AssertJ + ArchUnit

## 架构约束

{列出 AI 最容易违反的 3-5 条硬性规则。示例：}
- 领域层零外部依赖，不引入框架注解
- 构造函数注入，禁止字段注入
- 所有金额使用 BigDecimal，禁止浮点数

## 编码规范

{列出团队核心编码约定，3-5 条。示例：}
- DTO 使用 Java Record
- 测试命名：given_{条件}_when_{操作}_then_{预期结果}
- 测试使用 AssertJ，禁止无意义断言

{如有踩坑经验文件：}
- 踩坑经验见 docs/PITFALLS.md，遇到相关问题时查阅

## 常用命令

- 编译：`mvn compile`
- 测试：`mvn test`
- {其他项目特有命令}

## 开发流程

使用 Superpowers 技能驱动开发，按需求规模分层：

- **大需求**：先充分讨论，产出需求设计文档（含 Epic 拆解），再逐个 Epic 推进
- **Epic / 中需求**：讨论后产出 Backlog 文档（含 Feature 拆解），再逐个 Feature 推进
- **Feature / 小需求 / Bug**：直接用 Superpowers 技能（brainstorming -> writing-plans -> TDD -> verification）

阶段性完成后人工触发归档：提取有价值内容到 docs/guide/，然后清理过程文档。
