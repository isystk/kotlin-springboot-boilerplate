package com.isystk.sample.batch.jobs;

import static com.isystk.sample.batch.BatchConst.MDC_BATCH_ID;

import com.isystk.sample.batch.context.BatchContext;
import com.isystk.sample.batch.context.BatchContextHolder;
import com.isystk.sample.common.util.MDCUtils;
import com.isystk.sample.domain.dao.AuditInfoHolder;
import java.time.LocalDateTime;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;
import lombok.val;
import org.seasar.doma.jdbc.SelectOptions;
import org.springframework.batch.item.database.AbstractPagingItemReader;

/**
 * ページングに対応したItemReaderの基底クラス
 *
 * @param <T>
 */
public abstract class BasePageableItemReader<T> extends AbstractPagingItemReader<T> {

  @Override
  protected T doRead() throws Exception {
    BatchContext context = BatchContextHolder.getContext();
    String batchId = context.getBatchId();

    // ThreadPoolを使用している場合は再設定する
    MDCUtils.putIfAbsent(MDC_BATCH_ID, batchId);

    LocalDateTime startDateTime = context.getStartDateTime();
    AuditInfoHolder.set(batchId, startDateTime);

    return super.doRead();
  }

  @Override
  protected void doReadPage() {
    if (results == null) {
      results = new CopyOnWriteArrayList<>();
    } else {
      results.clear();
    }

    results.addAll(getList());
  }

  @Override
  protected void doJumpToPage(int itemIndex) {
  }

  /**
   * 検索オプションを返します。
   *
   * @return
   */
  protected SelectOptions getSelectOptions() {
    int page = getPage(); // 1ページは0になる
    int perpage = getPageSize();
    int offset = page * perpage;
    return SelectOptions.get().offset(offset).limit(perpage);
  }

  /**
   * ページング処理したリストを返します。
   *
   * @return
   */
  protected abstract List<T> getList();
}
