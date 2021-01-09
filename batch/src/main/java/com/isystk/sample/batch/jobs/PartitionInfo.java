package com.isystk.sample.batch.jobs;

import java.io.Serializable;

public class PartitionInfo implements Serializable {

  private static final long serialVersionUID = 6650718199648553346L;

  int index;

  int gridSize;

  public PartitionInfo(int index, int gridSize) {
    this.index = index;
    this.gridSize = gridSize;
  }

  public int getIndex() {
    return this.index;
  }

  public int getGridSize() {
    return this.gridSize;
  }

  public void setIndex(int index) {
    this.index = index;
  }

  public void setGridSize(int gridSize) {
    this.gridSize = gridSize;
  }
}
