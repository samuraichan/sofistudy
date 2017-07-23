package com.sofi.study.resources;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;

import com.sofi.study.api.Saying;

@Path("/")
@Produces(MediaType.APPLICATION_JSON)
public class HelloResource {
   
  @GET
  @Path("/hello")
  public Saying sayHello() {
    return new Saying("hello world");
  }
  
  @GET
  @Path("/hellomsg")
  public Saying sayHelloMessage(@QueryParam("msg") String msg) {
    return new Saying(msg);
  }
}
