package com.sofi.study.db;

import io.dropwizard.hibernate.AbstractDAO;

import java.util.List;
import java.util.Optional;

import org.hibernate.SessionFactory;

import com.sofi.study.api.PhoneNumber;

public class PhoneNumberDAO extends AbstractDAO<PhoneNumber> {

  public PhoneNumberDAO(SessionFactory factory) {
    super(factory);
  }

  public Optional<PhoneNumber> findById(Long id) {
    return Optional.ofNullable(get(id));
  }

  public void delete(PhoneNumber person) {
    currentSession().delete(person);
  }

  public void update(PhoneNumber person) {
    currentSession().saveOrUpdate(person);
  }

  public PhoneNumber insert(PhoneNumber person) {
    return persist(person);
  }
  
  public List<PhoneNumber> getAll() {
    return (List<PhoneNumber>) currentSession().createCriteria(PhoneNumber.class).list();
  }

}
