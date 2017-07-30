package com.sofi.study.db;

import io.dropwizard.hibernate.AbstractDAO;

import java.util.List;
import java.util.Optional;

import org.hibernate.SessionFactory;

import com.sofi.study.api.User;

public class UserDAO extends AbstractDAO<User> {

  public UserDAO(SessionFactory factory) {
    super(factory);
  }
  
  public User create(User user) {
    return persist(user);
  }
  
  public List<User> findAll() {
    return list(namedQuery("com.sofi.study.api.User.findAll"));
  }
  
  public Optional<User> findByUserName(String username) {
    return Optional.ofNullable(uniqueResult(namedQuery("com.sofi.study.api.User.findByUserName").setParameter("username", username)));
  }
  
}
