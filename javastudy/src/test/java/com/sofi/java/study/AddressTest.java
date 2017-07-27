package com.sofi.java.study;

import java.util.Collection;
import java.util.Arrays;
import java.util.Map;
import java.util.function.Predicate;
import java.util.stream.Collectors;

import org.junit.Test;

import com.sofi.java.types.AddressType;

import static org.assertj.core.api.Assertions.assertThat;

public class AddressTest {

  @Test
  public void testAddresses() {
    Collection<Address> addresses = Arrays.asList(
      new Address("ABC", "98683", AddressType.SECONDARY),
      new Address("ABC", "98683", AddressType.PRIMARY),
      new Address("ABCD", "98683", AddressType.PRIMARY)
    );
    
    assertThat(addresses).isNotEmpty();
    assertThat(addresses).contains(new Address("ABC", "98683", AddressType.PRIMARY));
    
    // supply a predicate as a lambda to anyMatch
    Predicate<Address> hasPrimary = (a) -> a.getType().equals(AddressType.PRIMARY);
    assertThat(addresses.stream().anyMatch(hasPrimary)).isTrue();
    assertThat(addresses.stream().anyMatch((a) -> a.getType().equals(AddressType.PRIMARY))).isTrue();
    
    // filter a stream but only look at count
    assertThat(addresses.stream().filter((a) -> a.getType().equals(AddressType.PRIMARY)).count()).isEqualTo(2);
    
    // verify that the stream filter above indeed does NOT modify in the underlying collection
    assertThat(addresses.size()).isEqualTo(addresses.size());
    
    // 'collect' the stream as a Map using stream and use a group by address type, counting each type
    Map<AddressType, Long> map = addresses.stream()
      .collect(Collectors.groupingBy(Address::getType, Collectors.counting()));
    assertThat(map.get(AddressType.PRIMARY)).isEqualTo(2);
    assertThat(map.get(AddressType.SECONDARY)).isEqualTo(1);
    assertThat(map.get(AddressType.WORK)).isNull();
  }
}
