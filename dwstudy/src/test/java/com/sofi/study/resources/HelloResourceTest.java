package com.sofi.study.resources;

import io.dropwizard.testing.junit.ResourceTestRule;

import org.junit.ClassRule;
import org.junit.Test;
import static org.assertj.core.api.Assertions.assertThat;

import com.sofi.study.api.Saying;

public class HelloResourceTest {

  @ClassRule
  public static final ResourceTestRule resources = ResourceTestRule.builder()
    .addResource(new HelloResource())
    .build();
  
  @Test
  public void testGetResource() {
    String expected = "hello world";
    assertThat(resources.target("/hello").request().get(Saying.class).getContent()).isEqualTo(expected);
  }
  
  @Test
  public void testGetResourceWithMsg() {
    String expected = "&nbsp;";
    assertThat(resources.target("/hellomsg").queryParam("msg", "&nbsp;").request().get(Saying.class).getContent()).isEqualTo(expected);
  }
}
