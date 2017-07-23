package com.sofi.study.resources;

import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import com.sofi.study.api.Person;

@Path("/")
@Produces(MediaType.APPLICATION_JSON)
public class PersonResource {
   
  @POST
  @Path("/user/person")
  public Person savePerson(Person person) {
    return person;
  }
}
