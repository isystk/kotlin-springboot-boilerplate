package com.isystk.sample.domain.entity;

import com.isystk.sample.domain.dto.common.DomaDtoImpl;
import org.seasar.doma.Column;
import org.seasar.doma.Entity;
import org.seasar.doma.Id;
import org.seasar.doma.Table;

/**
 * 自動生成のため原則修正禁止!!
 */
@Entity
@Table(name = "t_post_tag")
public class TPostTag extends DomaDtoImpl {

  /**
   * serialVersionUID
   */
  private static final long serialVersionUID = 1L;


  /**
   * 投稿ID
   */
  @Id
  @Column(name = "POST_ID")
  Integer postId;

  /**
   * 投稿タグID
   */
  @Id
  @Column(name = "POST_TAG_ID")
  Integer postTagId;

  public Integer getPostId() {
    return this.postId;
  }

  public Integer getPostTagId() {
    return this.postTagId;
  }

  public void setPostId(Integer postId) {
    this.postId = postId;
  }

  public void setPostTagId(Integer postTagId) {
    this.postTagId = postTagId;
  }
}