package com.isystk.sample.common.helper

import com.google.common.reflect.ClassPath
import com.isystk.sample.common.values.Values
import org.springframework.stereotype.Component
import java.util.function.Function
import java.util.stream.Collectors

@Component("vh")
class ValuesHelper private constructor() {
    private val valuesObjList: Map<String, String>

    init {
        val loader = Thread.currentThread().contextClassLoader
        valuesObjList = ClassPath.from(loader)
                .getTopLevelClassesRecursive("com.isystk.sample.common.values").stream()
                .filter { classInfo: ClassPath.ClassInfo ->
                    try {
                        val clazz = Class.forName(classInfo.name)
                        return@filter clazz != Values::class.java && Values::class.java.isAssignableFrom(clazz)
                    } catch (e: ClassNotFoundException) {
                        throw RuntimeException(e)
                    }
                }
                .collect(Collectors.toList())
                .associateBy({ it.simpleName }, { it.name })
    }

    /**
     * JSPからアクセス用
     */
    @Throws(ClassNotFoundException::class)
    fun <T> values(classSimpleName: String): Array<T> where T : Enum<T>?, T : Values<*>? {
        val enumType = Class.forName(valuesObjList[classSimpleName]) as Class<T>
        return enumType.enumConstants
    }
}