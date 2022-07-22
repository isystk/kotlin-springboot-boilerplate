<#-- このテンプレートに対応するデータモデルのクラスは org.seasar.doma.extension.gen.EntityDesc です -->
<#import "/lib.ftl" as lib>
<#if lib.copyright??>
${lib.copyright}
</#if>
<#if packageName??>
package ${packageName};
</#if>

import java.time.LocalDateTime;

/**
<#if showDbComment && comment??>
 * ${comment}
</#if>
 *
 * 自動生成のため原則修正禁止!!
 *
<#if lib.author??>
 * @author ${lib.author}
</#if>
 */
class ${simpleName}Criteria {

<#list ownEntityPropertyDescs as property>

<#if property.name == "version">
<#elseif 0 lt property.name?index_of("Flg") >
    <#if !useAccessor>public </#if>var ${property.name}True: Boolean = false
    <#if !useAccessor>public </#if>var ${property.name}False: Boolean = false
<#else>
    <#if !useAccessor>public </#if>var ${property.name}Eq: ${property.propertyClassSimpleName}? = null
    <#if !useAccessor>public </#if>var ${property.name}Ne: ${property.propertyClassSimpleName}? = null
    <#if !useAccessor>public </#if>var ${property.name}Lt: ${property.propertyClassSimpleName}? = null
    <#if !useAccessor>public </#if>var ${property.name}Le: ${property.propertyClassSimpleName}? = null
    <#if !useAccessor>public </#if>var ${property.name}Gt: ${property.propertyClassSimpleName}? = null
    <#if !useAccessor>public </#if>var ${property.name}Ge: ${property.propertyClassSimpleName}? = null
    <#if !useAccessor>public </#if>var ${property.name}IsNull: Boolean = false
    <#if !useAccessor>public </#if>var ${property.name}IsNotNull: Boolean = false
<#if (property.date || property.timestamp)>
<#elseif property.number>
    <#if !useAccessor>public </#if>var ${property.name}In: List<${property.propertyClassSimpleName}?>? = null
    <#if !useAccessor>public </#if>var ${property.name}NotIn: List<${property.propertyClassSimpleName}?>? = null
<#else>
    <#if !useAccessor>public </#if>var ${property.name}In: List<${property.propertyClassSimpleName}?>? = null
    <#if !useAccessor>public </#if>var ${property.name}NotIn: List<${property.propertyClassSimpleName}?>? = null
    <#if !useAccessor>public </#if>var ${property.name}Like: ${property.propertyClassSimpleName}? = null
    <#if !useAccessor>public </#if>var ${property.name}NotLike: ${property.propertyClassSimpleName}? = null
    <#if !useAccessor>public </#if>var ${property.name}Starts: ${property.propertyClassSimpleName}? = null
    <#if !useAccessor>public </#if>var ${property.name}NotStarts: ${property.propertyClassSimpleName}? = null
    <#if !useAccessor>public </#if>var ${property.name}Ends: ${property.propertyClassSimpleName}? = null
    <#if !useAccessor>public </#if>var ${property.name}NotEnds: ${property.propertyClassSimpleName}? = null
</#if>
</#if>
</#list>

    var orderBy: String? = null;
}