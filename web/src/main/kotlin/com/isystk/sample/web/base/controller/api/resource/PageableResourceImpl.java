package com.isystk.sample.web.base.controller.api.resource;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.isystk.sample.common.dto.Dto;
import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
public class PageableResourceImpl extends ResourceImpl implements PageableResource {

  @JsonProperty("current_page")
  int currentPage = 1;

  int total;

  public PageableResourceImpl() {
  }

  public PageableResourceImpl(List<? extends Dto> data, int currentPage, int total) {
    this.data = data;
    this.currentPage = currentPage;
    this.total = total;
  }

  public int getCurrentPage() {
    return this.currentPage;
  }

  public int getTotal() {
    return this.total;
  }

  @JsonProperty("current_page")
  public void setCurrentPage(int currentPage) {
    this.currentPage = currentPage;
  }

  public void setTotal(int total) {
    this.total = total;
  }

  public boolean equals(final Object o) {
    if (o == this) {
      return true;
    }
    if (!(o instanceof PageableResourceImpl)) {
      return false;
    }
    final PageableResourceImpl other = (PageableResourceImpl) o;
    if (!other.canEqual((Object) this)) {
      return false;
    }
    if (!super.equals(o)) {
      return false;
    }
    if (this.getCurrentPage() != other.getCurrentPage()) {
      return false;
    }
    if (this.getTotal() != other.getTotal()) {
      return false;
    }
    return true;
  }

  protected boolean canEqual(final Object other) {
    return other instanceof PageableResourceImpl;
  }

  public int hashCode() {
    final int PRIME = 59;
    int result = super.hashCode();
    result = result * PRIME + this.getCurrentPage();
    result = result * PRIME + this.getTotal();
    return result;
  }
}
