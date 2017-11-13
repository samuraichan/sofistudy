package com.sofi.java.study.optional;

import java.util.Optional;

public class Person {
  
  private String firstName;

  private Optional<Car> car;
  
  public Person(String firstName, Car car) {
    this.car = Optional.ofNullable(car);
    this.firstName = firstName;
  }
  
  public Optional<Car> getCar() {
    return car;
  }
  
  public String getFirstName() {
    return firstName;
  }

  @Override
  public int hashCode() {
    final int prime = 31;
    int result = 1;
    result = prime * result + ((car == null) ? 0 : car.hashCode());
    result = prime * result + ((firstName == null) ? 0 : firstName.hashCode());
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
    if (car == null) {
      if (other.car != null)
        return false;
    } else if (!car.equals(other.car))
      return false;
    if (firstName == null) {
      if (other.firstName != null)
        return false;
    } else if (!firstName.equals(other.firstName))
      return false;
    return true;
  }
  
  
}
