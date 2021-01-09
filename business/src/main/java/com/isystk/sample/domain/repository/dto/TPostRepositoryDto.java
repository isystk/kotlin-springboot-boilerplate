package com.isystk.sample.domain.repository.dto;

import com.isystk.sample.domain.entity.TPost;
import com.isystk.sample.domain.entity.TPostImage;
import com.isystk.sample.domain.entity.TPostTag;
import com.isystk.sample.domain.entity.TUser;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import org.apache.commons.compress.utils.Lists;

public class TPostRepositoryDto extends TPost {

  TUser tUser;

  List<TPostImage> tPostImageList;

  List<TPostTag> tPostTagList;

  public List<Integer> getPostTagIdList() {
    return Optional.of(tPostTagList.stream().map((e) ->
          e.getPostTagId()
        ).collect(Collectors.toList())).orElse(Lists.newArrayList());
  }

  public TUser getTUser() {
    return this.tUser;
  }

  public List<TPostImage> getTPostImageList() {
    return this.tPostImageList;
  }

  public List<TPostTag> getTPostTagList() {
    return this.tPostTagList;
  }

  public void setTUser(TUser tUser) {
    this.tUser = tUser;
  }

  public void setTPostImageList(List<TPostImage> tPostImageList) {
    this.tPostImageList = tPostImageList;
  }

  public void setTPostTagList(List<TPostTag> tPostTagList) {
    this.tPostTagList = tPostTagList;
  }
}