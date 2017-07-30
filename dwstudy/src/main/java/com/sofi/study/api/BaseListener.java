package com.sofi.study.api;

import java.util.Date;

import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;


public class BaseListener {

  @PrePersist
  public void setCreatedDate(BaseEntity baseEntity) {
    baseEntity.setCreatedDate(new Date());
  }
  
  @PreUpdate
  public void setUpdatedDate(BaseEntity baseEntity) {
    baseEntity.setUpdatedDate(new Date());
  }
}
