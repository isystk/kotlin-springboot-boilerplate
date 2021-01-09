package com.isystk.sample.batch.jobs;

import java.util.HashMap;
import java.util.Map;
import org.slf4j.Logger;
import org.springframework.batch.core.partition.support.Partitioner;
import org.springframework.batch.item.ExecutionContext;

public abstract class BasePartitioner implements Partitioner {

  private static final Logger log = org.slf4j.LoggerFactory.getLogger(BasePartitioner.class);

  @Override
  public Map<String, ExecutionContext> partition(int gridSize) {
    Map<String, ExecutionContext> partitions = new HashMap<String, ExecutionContext>();

    int index = 1;
    for (int i = 1; i <= gridSize; i++) {
      // パーティション情報を追加する
      addPartitionInfo(partitions, index, gridSize);

      if (index < gridSize) {
        index++;
      } else if (index == gridSize) {
        index = 1;
      }
    }

    return partitions;
  }

  /**
   * パーティションを追加します。
   *
   * @param partitions
   * @param index
   * @param gridSize
   */
  protected void addPartitionInfo(Map<String, ExecutionContext> partitions, int index,
      int gridSize) {
    PartitionInfo partitionInfo = createPartitionInfo(new PartitionInfo(index, gridSize));
    ExecutionContext context = new ExecutionContext();
    context.put(PartitionInfo.class.getCanonicalName(), partitionInfo);

    String partitionName = String.format("partition%d", index);
    partitions.put(partitionName, context);
  }

  /**
   * @param partitionInfo
   * @return
   */
  protected PartitionInfo createPartitionInfo(PartitionInfo partitionInfo) {
    return partitionInfo;
  }
}
