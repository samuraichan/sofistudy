package com.sofi.study.api;

import javax.validation.constraints.Pattern;

import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.NotBlank;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Person {
  @NotBlank(message="must be provided")
  private String name;
  
  @Email
  private String email;
  
  @Pattern(message="is invalid", regexp="^(?!000)(?!666)(?!9)(\\d{3}|\\*{3})[-.,]?(?!00)(\\d{2}|\\*{2})[-.,]?(?!0000)(\\d{4}|\\*{4})$")
  private String ssn;
  
  private Long number;

  private Person() {
    // Jackson deserialization
  }

  public Person(String name, String email) {
    this.name = name;
    this.email = email;
  }
  
  public Person(String name, String email, String ssn) {
    this.name = name;
    this.email = email;
    this.ssn = ssn;
  }

  @JsonProperty(value="alias")
  public String getName() {
    return name;
  }

  @JsonProperty
  public void setName(String name) {
    this.name = name;
  }

  @JsonProperty
  public String getEmail() {
    return email;
  }

  @JsonProperty
  public void setEmail(String email) {
    this.email = email;
  }
  
  public String getSsn() {
    return ssn;
  }

  public void setSsn(String ssn) {
    this.ssn = ssn;
  }
  
  public Long getNumber() {
    return number;
  }

  public void setNumber(Long number) {
    this.number = number;
  }

  @Override
  public int hashCode() {
    final int prime = 31;
    int result = 1;
    result = prime * result + ((email == null) ? 0 : email.hashCode());
    result = prime * result + ((name == null) ? 0 : name.hashCode());
    result = prime * result + ((number == null) ? 0 : number.hashCode());
    result = prime * result + ((ssn == null) ? 0 : ssn.hashCode());
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
    Person other = (Person) obj;
    if (email == null) {
      if (other.email != null)
        return false;
    } else if (!email.equals(other.email))
      return false;
    if (name == null) {
      if (other.name != null)
        return false;
    } else if (!name.equals(other.name))
      return false;
    if (number == null) {
      if (other.number != null)
        return false;
    } else if (!number.equals(other.number))
      return false;
    if (ssn == null) {
      if (other.ssn != null)
        return false;
    } else if (!ssn.equals(other.ssn))
      return false;
    return true;
  }
}
