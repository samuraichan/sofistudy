package com.sofi.java.study;

import java.util.ArrayList;
import java.util.Collection;

import com.sofi.java.types.NationalityType;

public class Artist {

  private String name;
  
  private NationalityType nationality;
  
  private Collection<Address> addresses = new ArrayList<>();
  
  public Artist(){}
  
  public Artist(String name, NationalityType nationality) {
    this.name = name;
    this.nationality = nationality;
  }
  
  public Artist(String name, NationalityType nationality, Collection<Address> addresses) {
    this.name = name;
    this.nationality = nationality;
    addAddresses(addresses);
  }

  public String getName() {
    return name;
  }

  public NationalityType getNationality() {
    return nationality;
  }
  
  public Collection<Address> getAddresses() {
    return addresses;
  }
  
  public void addAddress(Address address) {
    addresses.add(address);
  }
  
  public void addAddresses(Collection<Address> address) {
    addresses.addAll(address);
  }

  @Override
  public int hashCode() {
    final int prime = 31;
    int result = 1;
    result = prime * result + ((addresses == null) ? 0 : addresses.hashCode());
    result = prime * result + ((name == null) ? 0 : name.hashCode());
    result = prime * result
        + ((nationality == null) ? 0 : nationality.hashCode());
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
    Artist other = (Artist) obj;
    if (addresses == null) {
      if (other.addresses != null)
        return false;
    } else if (!addresses.equals(other.addresses))
      return false;
    if (name == null) {
      if (other.name != null)
        return false;
    } else if (!name.equals(other.name))
      return false;
    if (nationality != other.nationality)
      return false;
    return true;
  }

  
}
