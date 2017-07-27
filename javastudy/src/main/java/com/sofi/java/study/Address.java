package com.sofi.java.study;

import com.sofi.java.types.AddressType;

public class Address {

  private String street;
  
  private String postal;
  
  private AddressType type;
  
  public Address() {}
  
  public Address(String street, String postal, AddressType type) {
    this.street = street;
    this.postal = postal;
    this.type = type;
  }
  
  public String getStreet() {
    return street;
  }

  public String getPostal() {
    return postal;
  }

  public AddressType getType() {
    return type;
  }

  @Override
  public int hashCode() {
    final int prime = 31;
    int result = 1;
    result = prime * result + ((postal == null) ? 0 : postal.hashCode());
    result = prime * result + ((street == null) ? 0 : street.hashCode());
    result = prime * result + ((type == null) ? 0 : type.hashCode());
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
    Address other = (Address) obj;
    if (postal == null) {
      if (other.postal != null)
        return false;
    } else if (!postal.equals(other.postal))
      return false;
    if (street == null) {
      if (other.street != null)
        return false;
    } else if (!street.equals(other.street))
      return false;
    if (type != other.type)
      return false;
    return true;
  }
  
  
}
