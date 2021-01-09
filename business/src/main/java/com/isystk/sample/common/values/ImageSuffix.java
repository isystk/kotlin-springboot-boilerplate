package com.isystk.sample.common.values;

/**
 * 保存する画像サイズ
 */
public enum ImageSuffix {

  SQUARE("_square", 900, 900),
  SD("_sd", 900, 675),
  ;

  private ImageSuffix(String suffix, int width, int height) {
    this.suffix = suffix;
    this.width = width;
    this.height = height;
  }

  private final String suffix;
  private final int width;
  private final int height;

  public String getSuffix() {
    return this.suffix;
  }

  public int getWidth() {
    return this.width;
  }

  public int getHeight() {
    return this.height;
  }
}