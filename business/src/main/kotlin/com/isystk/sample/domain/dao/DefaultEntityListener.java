package com.isystk.sample.domain.dao;

import static java.util.stream.Collectors.toList;

import com.isystk.sample.common.dto.Dto;
import com.isystk.sample.common.exception.DoubleSubmitErrorException;
import com.isystk.sample.common.util.DateUtils;
import com.isystk.sample.common.util.ReflectionUtils;
import com.isystk.sample.domain.dto.DomaDto;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Objects;
import org.seasar.doma.Id;
import org.seasar.doma.jdbc.entity.EntityListener;
import org.seasar.doma.jdbc.entity.PreDeleteContext;
import org.seasar.doma.jdbc.entity.PreInsertContext;
import org.seasar.doma.jdbc.entity.PreUpdateContext;
import org.slf4j.Logger;

// コンストラクタが必須のため
public class DefaultEntityListener<ENTITY> implements EntityListener<ENTITY> {

  private static final Logger log = org.slf4j.LoggerFactory.getLogger(DefaultEntityListener.class);

  public DefaultEntityListener() {
  }

  /**
   * 新規登録
   */
  @Override
  public void preInsert(ENTITY entity, PreInsertContext<ENTITY> context) {
    // 二重送信防止チェック
    String expected = DoubleSubmitCheckTokenHolder.getExpectedToken();
    String actual = DoubleSubmitCheckTokenHolder.getActualToken();

    if (expected != null && actual != null && !Objects.equals(expected, actual)) {
      throw new DoubleSubmitErrorException();
    }

  }

  /**
   * 更新・論理削除
   */
  @Override
  public void preUpdate(ENTITY entity, PreUpdateContext<ENTITY> context) {
  }

  /**
   * 物理削除
   */
  @Override
  public void preDelete(ENTITY entity, PreDeleteContext<ENTITY> context) {
    if (entity instanceof DomaDto) {
      DomaDto domaDto = (DomaDto) entity;
      LocalDateTime deletedAt = AuditInfoHolder.getAuditDateTime();
      LocalDateTime deletedBy = DateUtils.getNow();
      String name = domaDto.getClass().getName();
      List<Object> ids = getIds(domaDto);

      // 物理削除した場合はログ出力する
      log.info("データを物理削除しました。entity={}, id={}, deletedBy={}, deletedAt={}", name, ids, deletedBy,
          deletedAt);
    }
  }

  /**
   * Idアノテーションが付与されたフィールドの値のリストを返します。
   *
   * @param dto
   * @return
   */
  protected List<Object> getIds(Dto dto) {
    return ReflectionUtils.findWithAnnotation(dto.getClass(), Id.class)
        .map(f -> ReflectionUtils.getFieldValue(f, dto)).collect(toList());
  }
}
