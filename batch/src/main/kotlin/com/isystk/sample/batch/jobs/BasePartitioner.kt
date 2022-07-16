package com.isystk.sample.batch.jobs

import org.slf4j.LoggerFactory
import org.springframework.batch.core.partition.support.Partitioner
import org.springframework.batch.item.ExecutionContext

abstract class BasePartitioner : Partitioner {
    override fun partition(gridSize: Int): Map<String, ExecutionContext> {
        val partitions: MutableMap<String, ExecutionContext> = HashMap()
        var index = 1
        for (i in 1..gridSize) {
            // パーティション情報を追加する
            addPartitionInfo(partitions, index, gridSize)
            if (index < gridSize) {
                index++
            } else if (index == gridSize) {
                index = 1
            }
        }
        return partitions
    }

    /**
     * パーティションを追加します。
     *
     * @param partitions
     * @param index
     * @param gridSize
     */
    protected fun addPartitionInfo(partitions: MutableMap<String, ExecutionContext>, index: Int,
                                   gridSize: Int) {
        val partitionInfo = createPartitionInfo(PartitionInfo(index, gridSize))
        val context = ExecutionContext()
        context.put(PartitionInfo::class.java.canonicalName, partitionInfo)
        val partitionName = String.format("partition%d", index)
        partitions[partitionName] = context
    }

    /**
     * @param partitionInfo
     * @return
     */
    protected fun createPartitionInfo(partitionInfo: PartitionInfo): PartitionInfo {
        return partitionInfo
    }

    companion object {
        private val log = LoggerFactory.getLogger(BasePartitioner::class.java)
    }
}