package com.sofi.study.resources;

import io.dropwizard.hibernate.UnitOfWork;

import java.util.Collection;

import javax.ws.rs.GET;
import javax.ws.rs.NotFoundException;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import com.sofi.study.api.User;
import com.sofi.study.db.UserDAO;

@Path("/")
@Produces(MediaType.APPLICATION_JSON)
public class UserResource {
  
  private UserDAO userDAO;
  
  public UserResource(UserDAO dao) {
    this.userDAO = dao;
  }
  
  @GET
  @UnitOfWork
  @Path("/users")
  public Collection<User> findAllUsers() {
    return userDAO.findAll();
  }
    
  @GET
  @UnitOfWork
  @Path("/user/{username}")
  public User findUserByUsername(@PathParam("username") String username) {
    return userDAO.findByUserName(username).orElseThrow(() -> new NotFoundException("No such user."));
  }
  
  @POST
  @UnitOfWork
  @Path("/user")
  public User createuser(User user) {
      return userDAO.create(user);
  }
}
