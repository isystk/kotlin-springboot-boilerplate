package com.isystk.sample.common.helper;

import com.isystk.sample.common.exception.NoDataFoundException;
import com.isystk.sample.common.util.DateUtils;
import com.isystk.sample.domain.dao.AuditInfoHolder;
import com.isystk.sample.domain.dao.TUserDao;
import com.isystk.sample.domain.dto.TUserCriteria;
import com.isystk.sample.domain.entity.TUser;
import java.util.List;
import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * ユーザーヘルパー
 */
@Component
public class UserHelper {

  private static final Logger log = org.slf4j.LoggerFactory.getLogger(UserHelper.class);
  @Autowired
  TUserDao tUserDao;

  /**
   * ユーザーを全件取得します。
   *
   * @return
   */
  public List<TUser> getUserList() {
    TUserCriteria criteria = new TUserCriteria();
    criteria.setDeleteFlgFalse(true);
    return tUserDao.findAll(criteria);
  }

  /**
   * ユーザーを取得します。
   *
   * @return
   */
  public TUser getUser(Integer userId) {
    TUserCriteria criteria = new TUserCriteria();
    criteria.setUserIdEq(userId);
    return tUserDao.findOne(criteria).orElseThrow(
        () -> new NoDataFoundException("userId=" + userId + "のデータが見つかりません。"));
  }

  /**
   * ログインユーザーを取得します。
   *
   * @return
   */
  public Integer getLoginUserId() {
    return getUser().getUserId();
  }

  /**
   * ログインユーザーを取得します。
   *
   * @return
   */
  public TUser getUser() {
    TUserCriteria criteria = new TUserCriteria();
    criteria.setEmailEq(AuditInfoHolder.getAuditUser());
    return tUserDao.findOne(criteria).orElseThrow(
        () -> new NoDataFoundException(
            "email=" + AuditInfoHolder.getAuditUser() + "のデータが見つかりません。"));
  }

  /**
   * 最終ログイン日時を更新します。
   *
   * @return
   */
  public void updateLastLogin() {
    TUser tUser = getUser();
    tUser.setLastLoginTime(DateUtils.getNow());
    tUserDao.update(tUser);
  }

}
