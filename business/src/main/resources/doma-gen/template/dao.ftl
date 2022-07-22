<#-- このテンプレートに対応するデータモデルのクラスは org.seasar.doma.extension.gen.DaoDesc です -->
<#import "/lib.ftl" as lib>
<#if lib.copyright??>
${lib.copyright}
</#if>
<#if packageName??>
package ${packageName};
</#if>

import com.isystk.sample.domain.dto.${entityDesc.simpleName}Criteria;
import com.isystk.sample.domain.entity.${entityDesc.simpleName}
import org.seasar.doma.*
import org.seasar.doma.boot.ConfigAutowireable
import org.seasar.doma.jdbc.SelectOptions
import java.util.*
import java.util.stream.Collector

/**
<#if lib.author??>
 * @author ${lib.author}
</#if>
 */
 <#list lib.annotationHash?keys as annotation>
@${annotation}
</#list>
@Dao<#if configClassSimpleName??>(config = ${configClassSimpleName}.class)</#if>
interface ${simpleName} {

    /**
     * @param entity
     * @return affected rows
     */
    @Insert
    fun insert(entity: ${entityDesc.simpleName}): Int

    /**
     * @param entity
     * @return affected rows
     */
    @Update
    fun update(entity: ${entityDesc.simpleName}): Int

    /**
     * @param entity
     * @return affected rows
     */
    @Delete
    fun delete(entity: ${entityDesc.simpleName}): Int

    /**
     * @param criteria
     * @param options
     * @return
     */
    @Select(strategy = SelectType.COLLECT)
    fun <R> findAll(criteria: ${entityDesc.simpleName}Criteria, options: SelectOptions, collector: Collector<${entityDesc.simpleName}, *, R>): R

    /**
     * @param criteria
     * @return
     */
    @Select
    fun findAll(criteria: ${entityDesc.simpleName}Criteria): List<${entityDesc.simpleName}>

<#if entityDesc.idEntityPropertyDescs?size gt 0>
    /**
<#list entityDesc.idEntityPropertyDescs as property>
     * @param ${property.name}
</#list>
     * @return the ${entityDesc.simpleName} entity
     */
    @Select
    fun selectById(<#list entityDesc.idEntityPropertyDescs as property>${property.name}: ${property.propertyClassSimpleName}<#if property_has_next>, </#if></#list>): ${entityDesc.simpleName}?

</#if>
<#if entityDesc.idEntityPropertyDescs?size gt 0 && entityDesc.versionEntityPropertyDesc??>
    /**
<#list entityDesc.idEntityPropertyDescs as property>
     * @param ${property.name}
</#list>
     * @param ${entityDesc.versionEntityPropertyDesc.name}
     * @return the ${entityDesc.simpleName} entity
     */
    @Select(ensureResult = true)
    fun selectByIdAndVersion(<#list entityDesc.idEntityPropertyDescs as property>${property.name}: ${property.propertyClassSimpleName}, </#list>${entityDesc.versionEntityPropertyDesc.name}: ${entityDesc.versionEntityPropertyDesc.propertyClassSimpleName}): ${entityDesc.simpleName}?

</#if>

    /**
     * @param criteria
     * @return
     */
    @Select
    fun findOne(criteria: ${entityDesc.simpleName}Criteria): ${entityDesc.simpleName}?

}