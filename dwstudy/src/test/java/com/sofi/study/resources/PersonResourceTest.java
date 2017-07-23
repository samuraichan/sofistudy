package com.sofi.study.resources;

import static org.assertj.core.api.Assertions.assertThat;

import javax.ws.rs.client.Entity;
import javax.ws.rs.core.MediaType;

import org.junit.ClassRule;
import org.junit.Test;

import com.sofi.study.api.Person;

import io.dropwizard.testing.junit.ResourceTestRule;

public class PersonResourceTest {

  @ClassRule
  public static final ResourceTestRule resources = ResourceTestRule.builder()
    .addResource(new PersonResource())
    .build();
   
  @Test
  public void testGetResourceWithMsg() {
    Person actual = new Person("elise", "mecpurpleelephant@gmail.com");
    Person expected = new Person("elise", "mecpurpleelephant@gmail.com");
    
    assertThat(resources.target("/user/person")
      .request()
      .post(Entity.entity(actual, MediaType.APPLICATION_JSON), Person.class))
      .isEqualTo(expected);
  }
}
