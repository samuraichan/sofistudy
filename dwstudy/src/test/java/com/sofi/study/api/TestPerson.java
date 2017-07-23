package com.sofi.study.api;

import static io.dropwizard.testing.FixtureHelpers.*;
import static org.assertj.core.api.Assertions.assertThat;
import io.dropwizard.jackson.Jackson;
import org.junit.Test;
import com.fasterxml.jackson.databind.ObjectMapper;

public class TestPerson {

  private static final ObjectMapper MAPPER = Jackson.newObjectMapper();

  @Test
  public void serializesToJSON() throws Exception {
    final Person person = new Person("Luther Blissett", "lb@example.com");

    final String expected = MAPPER.writeValueAsString(MAPPER.readValue(
      fixture("fixtures/person.json"), Person.class));

    assertThat(MAPPER.writeValueAsString(person)).isEqualTo(expected);
  }
  
  @Test
  public void deserializesFromJSON() throws Exception {
      final Person person = new Person("Luther Blissett", "lb@example.com");
      assertThat(MAPPER.readValue(fixture("fixtures/person.json"), Person.class)).isEqualTo(person);
  }
}
