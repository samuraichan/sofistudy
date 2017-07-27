package com.sofi.java.study;

public class Person {

  private String fName;
  
  private String lName;
  
  private Integer age;
  
  public Person (String fName, String lName, Integer age) {
    this.fName = fName;
    this.lName = lName;
    this.age = age;
  }

  public String getfName() {
    return fName;
  }

  public String getlName() {
    return lName;
  }

  public Integer getAge() {
    return age;
  }

  @Override
  public int hashCode() {
    final int prime = 31;
    int result = 1;
    result = prime * result + ((age == null) ? 0 : age.hashCode());
    result = prime * result + ((fName == null) ? 0 : fName.hashCode());
    result = prime * result + ((lName == null) ? 0 : lName.hashCode());
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
    if (age == null) {
      if (other.age != null)
        return false;
    } else if (!age.equals(other.age))
      return false;
    if (fName == null) {
      if (other.fName != null)
        return false;
    } else if (!fName.equals(other.fName))
      return false;
    if (lName == null) {
      if (other.lName != null)
        return false;
    } else if (!lName.equals(other.lName))
      return false;
    return true;
  }

  @Override
  public String toString() {
    return lName;
  }
}
