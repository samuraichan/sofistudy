package com.sofi.study.resources;

import static org.assertj.core.api.Assertions.assertThat;

import javax.ws.rs.client.Entity;
import javax.ws.rs.core.MediaType;

import org.eclipse.jetty.http.HttpStatus;
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
  
  @Test
  public void testGetResourceWithMsgStatus() {
    Person actual = new Person("elise", "mecpurpleelephant@gmail.com");
    
    assertThat(resources.target("/user/person")
      .request()
      .post(Entity.entity(actual, MediaType.APPLICATION_JSON)).getStatus())
      .isEqualTo(HttpStatus.OK_200);
    
    assertThat(resources.target("/user/person")
      .request()
      .post(Entity.json(actual)).getStatus())
      .isEqualTo(HttpStatus.OK_200);
  }
  
  @Test
  public void testGetResourceWithMsgStatus422() {
    Person actual = new Person("", "mecpurpleelephant@gmail.com");
    
    assertThat(resources.target("/user/person")
      .request()
      .post(Entity.entity(actual, MediaType.APPLICATION_JSON)).getStatus())
      .isEqualTo(HttpStatus.UNPROCESSABLE_ENTITY_422);
    
    assertThat(resources.target("/user/person")
      .request()
      .post(Entity.json(actual)).getStatus())
      .isEqualTo(HttpStatus.UNPROCESSABLE_ENTITY_422);
  }
  
  @Test
  public void testSSNFormat() {
    Person p = new Person("raul", "dkd@gmail.com", "12");
    assertThat(resources.target("/user/person")
        .request()
        .post(Entity.entity(p, MediaType.APPLICATION_JSON)).getStatus())
        .isEqualTo(HttpStatus.UNPROCESSABLE_ENTITY_422);
    
    p = new Person("raul", "dkd@gmail.com", "111-11-1111a");
    assertThat(resources.target("/user/person")
        .request()
        .post(Entity.entity(p, MediaType.APPLICATION_JSON)).getStatus())
        .isEqualTo(HttpStatus.UNPROCESSABLE_ENTITY_422);
    
    p = new Person("raul", "dkd@gmail.com", "aa-aa-aaaa");
    assertThat(resources.target("/user/person")
        .request()
        .post(Entity.entity(p, MediaType.APPLICATION_JSON)).getStatus())
        .isEqualTo(HttpStatus.UNPROCESSABLE_ENTITY_422);
    
    p = new Person("raul", "dkd@gmail.com", "111-11-012a");
    assertThat(resources.target("/user/person")
        .request()
        .post(Entity.entity(p, MediaType.APPLICATION_JSON)).getStatus())
        .isEqualTo(HttpStatus.UNPROCESSABLE_ENTITY_422);
    
    p = new Person("raul", "dkd@gmail.com", "111-11-1111 ");
    assertThat(resources.target("/user/person")
        .request()
        .post(Entity.entity(p, MediaType.APPLICATION_JSON)).getStatus())
        .isEqualTo(HttpStatus.UNPROCESSABLE_ENTITY_422);
    
    p = new Person("raul", "dkd@gmail.com", " 111-11-1111");
    assertThat(resources.target("/user/person")
        .request()
        .post(Entity.entity(p, MediaType.APPLICATION_JSON)).getStatus())
        .isEqualTo(HttpStatus.UNPROCESSABLE_ENTITY_422);
    
    p = new Person("raul", "dkd@gmail.com", " 111- 11-1111");
    assertThat(resources.target("/user/person")
        .request()
        .post(Entity.entity(p, MediaType.APPLICATION_JSON)).getStatus())
        .isEqualTo(HttpStatus.UNPROCESSABLE_ENTITY_422);
    
    p = new Person("raul", "dkd@gmail.com", " 11111-1111");
    assertThat(resources.target("/user/person")
        .request()
        .post(Entity.entity(p, MediaType.APPLICATION_JSON)).getStatus())
        .isEqualTo(HttpStatus.UNPROCESSABLE_ENTITY_422);
    
    p = new Person("raul", "dkd@gmail.com", " 111 11 1111");
    assertThat(resources.target("/user/person")
        .request()
        .post(Entity.entity(p, MediaType.APPLICATION_JSON)).getStatus())
        .isEqualTo(HttpStatus.UNPROCESSABLE_ENTITY_422);
    
    p = new Person("raul", "dkd@gmail.com", "111-11-1111");
    assertThat(resources.target("/user/person")
        .request()
        .post(Entity.entity(p, MediaType.APPLICATION_JSON)).getStatus())
        .isEqualTo(HttpStatus.OK_200);
    
    p = new Person("raul", "dkd@gmail.com", "111111111");
    assertThat(resources.target("/user/person")
        .request()
        .post(Entity.entity(p, MediaType.APPLICATION_JSON)).getStatus())
        .isEqualTo(HttpStatus.OK_200);
  }
}
