package com.sofi.study.api;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.EntityListeners;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Version;

import org.hibernate.annotations.Type;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;

@MappedSuperclass
@EntityListeners(BaseListener.class)
public class BaseEntity {

  @Id
  @GeneratedValue(strategy = GenerationType.SEQUENCE)
  protected Long id;
  
  @Column(name="active_flag")
  @Type(type="yes_no")
  @JsonIgnore
  private boolean activeFlag;
  
  @Column(name="created_date")
  @Temporal(TemporalType.TIMESTAMP)
  @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "MMM dd yyyy")
  private Date createdDate;
  
  @Column(name="updated_date")
  @Temporal(TemporalType.TIMESTAMP)
  @JsonIgnore
  private Date updatedDate;
  
  @Version
  @Column(name="version_number")
  @JsonIgnore
  private Integer version;
  
  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
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

  @Override
  public int hashCode() {
    final int prime = 31;
    int result = 1;
    result = prime * result + (activeFlag ? 1231 : 1237);
    result = prime * result
        + ((createdDate == null) ? 0 : createdDate.hashCode());
    result = prime * result + ((id == null) ? 0 : id.hashCode());
    result = prime * result
        + ((updatedDate == null) ? 0 : updatedDate.hashCode());
    result = prime * result + ((version == null) ? 0 : version.hashCode());
    return result;
  }

  @Override
  public boolean equals(Object obj) {
    if (this == obj)
      return true;
    if (obj == null)
      return false;
    if (getClass() != obj.getClass())
      return false;
    BaseEntity other = (BaseEntity) obj;
    if (activeFlag != other.activeFlag)
      return false;
    if (createdDate == null) {
      if (other.createdDate != null)
        return false;
    } else if (!createdDate.equals(other.createdDate))
      return false;
    if (id == null) {
      if (other.id != null)
        return false;
    } else if (!id.equals(other.id))
      return false;
    if (updatedDate == null) {
      if (other.updatedDate != null)
        return false;
    } else if (!updatedDate.equals(other.updatedDate))
      return false;
    if (version == null) {
      if (other.version != null)
        return false;
    } else if (!version.equals(other.version))
      return false;
    return true;
  }
}
