package com.sofi.study.api;

import org.hibernate.validator.constraints.Length;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * A 'representation class' or simple pojo 
 */
public class Saying {
  
  @Length(max = 3)
  private String content;

  public Saying() {
    // Jackson deserialization
  }

  public Saying(String content) {
    this.content = content;
  }

  @JsonProperty(value="msg") // rename serialized property into 'msg' instead of 'content'
  public String getContent() {
    return content;
  }
}
