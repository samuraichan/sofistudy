package com.sofi.study.db;

import io.dropwizard.hibernate.AbstractDAO;

import java.util.Optional;

import org.hibernate.SessionFactory;

import com.sofi.study.api.PhoneNumber;

public class PhoneNumberDAO extends AbstractDAO<PhoneNumber>{

  public PhoneNumberDAO(SessionFactory factory) {
    super(factory);
  }
  
  public Optional<PhoneNumber> findById(Long id) {
    return Optional.ofNullable(get(id));
  }

}
