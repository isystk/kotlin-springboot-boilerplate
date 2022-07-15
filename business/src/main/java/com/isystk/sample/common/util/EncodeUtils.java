package com.isystk.sample.common.util;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import org.slf4j.Logger;

/**
 * エンコードユーティリティ
 */
public class EncodeUtils {

  private static final Logger log = org.slf4j.LoggerFactory.getLogger(EncodeUtils.class);

  /**
   * UTF-8でエンコードした文字列を返します。
   *
   * @param filename
   * @return
   */
  public static String encodeUtf8(String filename) {
    String encoded = null;

    try {
      encoded = URLEncoder.encode(filename, "UTF-8");
    } catch (UnsupportedEncodingException ignore) {
      // should never happens
    }

    return encoded;
  }
}
