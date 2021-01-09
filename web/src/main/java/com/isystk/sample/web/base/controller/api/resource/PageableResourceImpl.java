package com.isystk.sample.web.base.controller.api.resource;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.isystk.sample.common.dto.Dto;
import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
public class PageableResourceImpl extends ResourceImpl implements PageableResource {

  int page = 1;

  int totalPages;

  public PageableResourceImpl() {
  }

  public PageableResourceImpl(List<? extends Dto> data, int page, int totalPages) {
    this.data = data;
    this.page = page;
    this.totalPages = totalPages;
  }

  public int getPage() {
    return this.page;
  }

  public int getTotalPages() {
    return this.totalPages;
  }

  public void setPage(int page) {
    this.page = page;
  }

  public void setTotalPages(int totalPages) {
    this.totalPages = totalPages;
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
    if (this.getPage() != other.getPage()) {
      return false;
    }
    if (this.getTotalPages() != other.getTotalPages()) {
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
    result = result * PRIME + this.getPage();
    result = result * PRIME + this.getTotalPages();
    return result;
  }
}
