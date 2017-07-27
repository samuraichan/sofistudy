package com.sofi.java.study;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.function.Predicate;

import org.junit.Before;
import org.junit.Test;

public class PersonTest {
  
  private List<Person> people = Collections.EMPTY_LIST;

  @Before
  public void init() {
    people = Arrays.asList(
      new Person("Kent", "Turner", 32),
      new Person("William", "Mac", 22),
      new Person("Craig", "Smith", 67),
      new Person("Jennifer", "Connley", 12),
      new Person("David", "McKenna", 76),
      new Person("Ed", "Lauder", 25)
    );
  }
  
  @Test
  public void testSortPeopleLambda() {
    Collections.sort(people, (o1, o2) -> o1.getlName().compareTo(o2.getlName()));
    assertThat(people.toString()).isEqualTo("[Connley, Lauder, Mac, McKenna, Smith, Turner]");
  }
  
  @Test
  public void testSortPeopleByAgeLambda() {
    Collections.sort(people, (o1, o2) -> o1.getAge().compareTo(o2.getAge()));
    assertThat(people.toString()).isEqualTo("[Connley, Mac, Lauder, Turner, Smith, McKenna]");
  }
  
  @Test
  public void testPrintAllLambda() {
    assertThat(filter(people, (p) -> true).toString()).isEqualTo("[Turner, Mac, Smith, Connley, McKenna, Lauder]");
  }
  
  @Test
  public void testPrintAllLastNameLambda() {
    assertThat(filter(people, (p) -> p.getlName().startsWith("M")).toString()).isEqualTo("[Mac, McKenna]");
  }
  
  // own custom way of doing something similar to people().stream().filter(...)
  private <T> List<T> filter(List<T> col, Predicate<? super T> predicate) {
    List<T> temp = new ArrayList<>();
    for (T c : col) {
      if (predicate.test(c)) {
        temp.add(c);
      }
    }
    return temp;
  }
  
}
