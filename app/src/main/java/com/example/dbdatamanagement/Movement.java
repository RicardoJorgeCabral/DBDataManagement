package com.example.dbdatamanagement;

import java.util.Date;

/**
 * Created by Ricardoc on 20-02-2017.
 */

public class Movement {
  private long id;
  private Date date;
  private String operation;
  private Double value;
  private String obs;

  @Override
  public String toString() {
    return this.id + " " +
           this.date.toString() +  " " +
           this.operation + " " +
           this.value.toString() +  " " +
           this.obs;
  }

  public long getId() {
    return id;
  }

  public void setId(long id) {
    this.id = id;
  }

  public Date getDate() {
    return date;
  }

  public void setDate(Date date) {
    this.date = date;
  }

  public String getOperation() {
    return operation;
  }

  public void setOperation(String operation) {
    this.operation = operation;
  }

  public Double getValue() {
    return value;
  }

  public Double getSignedValue() {
    Double val = this.getValue();
    if (this.getOperation().equals(new String("D"))) {
      return val * -1;
    }
    else {
      return val;
    }
  }

  public void setValue(Double value) {
    this.value = value;
  }

  public String getObs() {
    return obs;
  }

  public void setObs(String obs) {
    this.obs = obs;
  }


}
