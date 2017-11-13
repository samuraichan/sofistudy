package com.sofi.java.study.optional;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.Test;

public class PersonTest {

  @Test
  public void testPerson() {
    Person p = new Person("Paul", new Car(new InsurancePolicy("110x-678")));
    assertThat(p.getCar().flatMap(Car::getPolicy).map(InsurancePolicy::getName).get()).isEqualTo("110x-678");
  }
  
  @Test
  public void testPersonNoCar() {
    Person p = new Person("Paul", null);
    assertThat(p.getCar().flatMap(Car::getPolicy).map(InsurancePolicy::getName).orElse("none")).isEqualTo("none");
  }
  
  @Test
  public void testPersonNoCarIsPresent() {
    Person p = new Person("Paul", null);
    assertThat(p.getCar().flatMap(Car::getPolicy).map(InsurancePolicy::getName).isPresent()).isFalse();
  }
}
