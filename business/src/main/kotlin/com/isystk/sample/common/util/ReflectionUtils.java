package com.isystk.sample.common.util;

import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.util.Arrays;
import java.util.Optional;
import java.util.stream.Stream;
import org.slf4j.Logger;

/**
 * リフレクション関連ユーティリティ
 */
public class ReflectionUtils {

  private static final Logger log = org.slf4j.LoggerFactory.getLogger(ReflectionUtils.class);

  /**
   * 指定したアノテーションが付与されているフィールドを返します。
   *
   * @param clazz
   * @param annotationType
   * @param <A>
   * @return
   */
  public static <A extends Annotation> Stream<Field> findWithAnnotation(Class<?> clazz,
      Class<A> annotationType) {
    return Optional.ofNullable(clazz.getDeclaredFields()).map(Arrays::stream)
        .orElseGet(Stream::empty)
        .filter(f -> f.isAnnotationPresent(annotationType));
  }

  /**
   * 指定したフィールドの値を返します。
   *
   * @param f
   * @param obj
   * @return
   */
  public static Object getFieldValue(Field f, Object obj) {

    try {
      f.setAccessible(true);
      return f.get(obj);
    } catch (Exception e) {
      // ignore
    }

    return null;
  }
}
