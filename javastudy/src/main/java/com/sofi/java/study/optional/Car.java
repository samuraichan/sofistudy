package com.sofi.java.study.optional;

import java.util.Optional;

public class Car {

  private Optional<InsurancePolicy> policy;
  
  public Car(InsurancePolicy policy) {
    this.policy = Optional.ofNullable(policy);
  }

  public Optional<InsurancePolicy> getPolicy() {
    return policy;
  }

  @Override
  public int hashCode() {
    final int prime = 31;
    int result = 1;
    result = prime * result + ((policy == null) ? 0 : policy.hashCode());
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
    Car other = (Car) obj;
    if (policy == null) {
      if (other.policy != null)
        return false;
    } else if (!policy.equals(other.policy))
      return false;
    return true;
  }
}
