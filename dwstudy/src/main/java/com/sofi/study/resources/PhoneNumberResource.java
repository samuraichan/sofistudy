package com.sofi.study.resources;

import io.dropwizard.hibernate.UnitOfWork;
import io.dropwizard.jersey.params.LongParam;

import javax.validation.Valid;
import javax.ws.rs.GET;
import javax.ws.rs.NotFoundException;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import com.sofi.study.api.PhoneNumber;
import com.sofi.study.db.PhoneNumberDAO;

@Path("/")
@Produces(MediaType.APPLICATION_JSON)
public class PhoneNumberResource {
  
  private PhoneNumberDAO phoneNumberDAO;
  
  public PhoneNumberResource(PhoneNumberDAO dao) {
    this.phoneNumberDAO = dao;
  }
   
  @POST
  @UnitOfWork
  @Path("/phoneNumber")
  public PhoneNumber savePhoneNumber(@Valid PhoneNumber phoneNumber) {
    return phoneNumber;
  }
  
  @GET
  @UnitOfWork
  @Path("/phoneNumber/{id}")
  public PhoneNumber findPhoneNumber(@PathParam("id") LongParam id) {
    return phoneNumberDAO.findById(id.get()).orElseThrow(() -> new NotFoundException("No such user."));
  }
}
