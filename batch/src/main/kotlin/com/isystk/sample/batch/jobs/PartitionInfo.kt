package com.isystk.sample.batch.jobs

import java.io.Serializable

class PartitionInfo(var index: Int, var gridSize: Int) : Serializable {

    companion object {
        private const val serialVersionUID = 6650718199648553346L
    }
}