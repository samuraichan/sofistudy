package com.sofi.study.api;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Version;

import org.hibernate.annotations.Type;

@MappedSuperclass
public class BaseEntity {

  @Id
  @GeneratedValue(strategy = GenerationType.SEQUENCE)
  protected Integer id;
  
  @Column(name="active_flag")
  @Type(type="yes_no")
  private boolean activeFlag;
  
  @Column(name="created_date")
  @Temporal(TemporalType.TIMESTAMP)
  private Date createdDate;
  
  @Column(name="updated_date")
  @Temporal(TemporalType.TIMESTAMP)
  private Date updatedDate;
  
  @Version
  @Column(name="version_number")
  private Integer version;
  
  public Integer getId() {
    return id;
  }

  public void setId(Integer id) {
    this.id = id;
  }

  public boolean isNew() {
    return this.id == null;
  }

  public Integer getVersion() {
    return version;
  }

  public void setVersion(Integer version) {
    this.version = version;
  }
  
  public boolean getActiveFlag() {
    return activeFlag;
  }

  public void setActiveFlag(boolean activeFlag) {
    this.activeFlag = activeFlag;
  }

  public Date getCreatedDate() {
    return createdDate;
  }

  public void setCreatedDate(Date createdDate) {
    this.createdDate = createdDate;
  }

  public Date getUpdatedDate() {
    return updatedDate;
  }

  public void setUpdatedDate(Date updatedDate) {
    this.updatedDate = updatedDate;
  }
}
