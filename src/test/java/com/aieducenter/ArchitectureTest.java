package com.aieducenter;

import com.tngtech.archunit.core.importer.ImportOption;
import com.tngtech.archunit.junit.AnalyzeClasses;
import com.cartisan.test.archunit.CartisanLayeringRules;

/**
 * 架构守护测试。
 *
 * <p>继承 cartisan-boot 框架的分层规则（4条）。</p>
 *
 * <h2>✅ 框架已更新（2026-04-05）</h2>
 * <p>框架已修复 14 次违规：</p>
 * <ul>
 *   <li>✅ Repository 接口可使用 {@code @Query}、{@code @Param}（规则排除接口）</li>
 *   <li>✅ Controller 可使用领域枚举（框架支持 BaseEnum 参数绑定）</li>
 *   <li>✅ Controller 可使用 Pageable（Spring Data 标准用法）</li>
 * </ul>
 *
 * <h2>⚠️ 项目特定例外</h2>
 * <p><b>User 聚合根使用 BCryptPasswordEncoder</b>（6 次违规）</p>
 *
 * <p><b>理由</b>：密码编码是领域层核心职责，{@code User.matchesPassword()} 是领域逻辑</p>
 * <p><b>依据</b>：项目规范 DDD-005 明确许可；cartisan-boot 使用手册提到 User/AdminUser 例外</p>
 * <p><b>状态</b>：已反馈给框架，等待添加例外</p>
 *
 * @see CartisanLayeringRules
 */
@AnalyzeClasses(packages = "com.aieducenter", importOptions = ImportOption.DoNotIncludeTests.class)
public class ArchitectureTest extends CartisanLayeringRules {

}
