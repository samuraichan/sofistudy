package com.sofi.java.study;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import java.util.NoSuchElementException;
import java.util.Optional;

import org.junit.Test;

import com.sofi.java.types.AddressType;

public class OptionTest {
  
  private Optional<Address> intanceOptional;
  
  
  @Test
  public void testInitOptional() {
    Optional<Address> address = Optional.empty();
    assertThat(address.isPresent()).isFalse();
  }
  
  @Test
  public void testInitOptionalOf() {
    Optional<Address> address = Optional.of(new Address());
    assertThat(address.isPresent()).isTrue();
    
    address = Optional.ofNullable(new Address());
    assertThat(address.isPresent()).isTrue();
  }
  
  @Test
  public void testInitOptionalOfNull() {
    assertThatThrownBy(() -> {Optional.of(null);}).isInstanceOf(NullPointerException.class);
  }
  
  @Test
  public void testInitOptionalOfNullInstance() {
    assertThatThrownBy(() -> {intanceOptional.isPresent();}).isInstanceOf(NullPointerException.class);
  }
  
  @Test
  public void testInitOptionalOfNullable() {
    Optional<Address> address = Optional.ofNullable(new Address());
    assertThat(address.isPresent()).isTrue();
    
    address = Optional.ofNullable(null);
    assertThat(address.isPresent()).isFalse();
  }
  
  @Test
  public void testOptionalMap() {
    Optional<Address> address = Optional.of(new Address("2604 SE 168th Place", "98683", AddressType.PRIMARY));
    assertThat(address.map(Address::getPostal).get()).isEqualTo("98683");
    
    
  }
  
  @Test
  public void testOptionalMapIfNull() {
    assertThatThrownBy(
      () -> {Optional.of(new Address("2604 SE 168th Place", null, AddressType.PRIMARY))
      .map(Address::getPostal).get();})
        .isInstanceOf(NoSuchElementException.class);
  }
  
  @Test
  public void testOptionalMapIfNullNoPresent() {
    Optional<Address> address = Optional.of(new Address("2604 SE 168th Place", null, AddressType.PRIMARY));
    assertThat(address.map(Address::getPostal).isPresent()).isFalse();
  }
  
  @Test
  public void testOptionalMapIfEmptyPresent() {
    Optional<Address> address = Optional.of(new Address("2604 SE 168th Place", "", AddressType.PRIMARY));
    assertThat(address.map(Address::getPostal).isPresent()).isTrue();
  }
  
  @Test
  public void testOptionalMapOrElse() {
    Optional<Address> address = Optional.of(new Address("2604 SE 168th Place", null, AddressType.PRIMARY));
    assertThat(address.map(Address::getPostal).orElse("none")).isEqualTo("none");
  }
  
  @Test
  public void testOptionalMapOrElseException() {
    assertThatThrownBy(
      () -> {Optional.of(new Address("2604 SE 168th Place", null, AddressType.PRIMARY))
      .map(Address::getPostal).orElseThrow(() -> new NoSuchFieldException());})
      .isInstanceOf(NoSuchFieldException.class);
  }
  
  
}
