package com.isystk.sample.domain.dao;

import com.isystk.sample.domain.dto.TImageCriteria;
import com.isystk.sample.domain.entity.TImage;
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
public interface TImageDao {

  /**
   * @param entity
   * @return affected rows
   */
  @Insert
  int insert(TImage entity);

  /**
   * @param entity
   * @return affected rows
   */
  @Update
  int update(TImage entity);

  /**
   * @param entity
   * @return affected rows
   */
  @Delete
  int delete(TImage entity);

  /**
   * @param criteria
   * @param options
   * @return
   */
  @Select(strategy = SelectType.COLLECT)
  <R> R findAll(final TImageCriteria criteria, final SelectOptions options,
      final Collector<TImage, ?, R> collector);

  /**
   * @param criteria
   * @return
   */
  @Select
  List<TImage> findAll(TImageCriteria criteria);

  /**
   * @param imageId
   * @return the TImage entity
   */
  @Select
  Optional<TImage> selectById(Integer imageId);


  /**
   * @param criteria
   * @return
   */
  @Select
  Optional<TImage> findOne(TImageCriteria criteria);

}