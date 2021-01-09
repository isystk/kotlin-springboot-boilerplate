package com.isystk.sample.common.helper;

import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

/**
 * 環境ヘルパー
 */
@Component("env")
public class EnvHelper {

  private static final Logger log = org.slf4j.LoggerFactory.getLogger(EnvHelper.class);
  @Value("${spring.profiles}") // 設定ファイルに定義されたprofilesを取得する
  String profile;

  /**
   * ローカル環境かどうか
   */
  public boolean isLocal() {
    return "local".equals(profile);
  }

  /**
   * ステージング環境かどうか
   */
  public boolean isStaging() {
    return "staging".equals(profile);
  }

  /**
   * 本番環境かどうか
   */
  public boolean isProduction() {
    return "production".equals(profile);
  }

}
