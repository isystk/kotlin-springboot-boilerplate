package com.isystk.sample.common.dto;

import java.util.List;

/**
 * 名称とコードのグループ DTO
 */
public class CodeValueGroupDto implements Dto {

  public CodeValueGroupDto(String groupName, List<CodeValueDto> codeValueDtoList) {
    this.name = groupName;
    this.data = codeValueDtoList;
  }

  String name;

  List<CodeValueDto> data;

  public String getName() {
    return this.name;
  }

  public List<CodeValueDto> getData() {
    return this.data;
  }

  public void setName(String name) {
    this.name = name;
  }

  public void setData(List<CodeValueDto> data) {
    this.data = data;
  }
}
