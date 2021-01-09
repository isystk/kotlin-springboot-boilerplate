package com.isystk.sample.domain.dao;

import com.isystk.sample.domain.dto.MPostTagCriteria;
import com.isystk.sample.domain.entity.MPostTag;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collector;
import org.seasar.doma.Dao;
import org.seasar.doma.Delete;
import org.seasar.doma.Insert;
import org.seasar.doma.Select;
import org.seasar.doma.SelectType;
import org.seasar.doma.Update;
import org.seasar.doma.boot.ConfigAutowireable;
import org.seasar.doma.jdbc.SelectOptions;

/**
 *
 */
@ConfigAutowireable
@Dao
public interface MPostTagDao {

  /**
   * @param entity
   * @return affected rows
   */
  @Insert
  int insert(MPostTag entity);

  /**
   * @param entity
   * @return affected rows
   */
  @Update
  int update(MPostTag entity);

  /**
   * @param entity
   * @return affected rows
   */
  @Delete
  int delete(MPostTag entity);

  /**
   * @param criteria
   * @param options
   * @return
   */
  @Select(strategy = SelectType.COLLECT)
  <R> R findAll(final MPostTagCriteria criteria, final SelectOptions options,
      final Collector<MPostTag, ?, R> collector);

  /**
   * @param criteria
   * @return
   */
  @Select
  List<MPostTag> findAll(MPostTagCriteria criteria);

  /**
   * @param postTagId
   * @return the MPostTag entity
   */
  @Select
  Optional<MPostTag> selectById(Integer postTagId);

  /**
   * @param postTagId
   * @param version
   * @return the MPostTag entity
   */
  @Select(ensureResult = true)
  Optional<MPostTag> selectByIdAndVersion(Integer postTagId, Long version);


  /**
   * @param criteria
   * @return
   */
  @Select
  Optional<MPostTag> findOne(MPostTagCriteria criteria);

}