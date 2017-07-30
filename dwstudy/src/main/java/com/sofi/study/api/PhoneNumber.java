package com.sofi.study.api;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import org.hibernate.annotations.Where;

@Entity
@Table(name="phone_number")
@Where(clause="active_flag = 'Y'")
public class PhoneNumber extends BaseEntity {
  
  @Column(name="phone")
  private String number;
  
  public String getNumber() {
    return number;
  }

  public void setNumber(String number) {
    this.number = number;
  }
  
  
}
