package com.sofi.study;

import io.dropwizard.Application;
import io.dropwizard.db.DataSourceFactory;
import io.dropwizard.hibernate.HibernateBundle;
import io.dropwizard.setup.Bootstrap;
import io.dropwizard.setup.Environment;

import com.sofi.study.api.PhoneNumber;
import com.sofi.study.db.PhoneNumberDAO;
import com.sofi.study.resources.HelloResource;
import com.sofi.study.resources.PersonResource;
import com.sofi.study.resources.PhoneNumberResource;

public class DwstudyApplication extends Application<DwstudyConfiguration> {
  
  private final HibernateBundle<DwstudyConfiguration> hibernate = new HibernateBundle<DwstudyConfiguration>(PhoneNumber.class) {
    @Override
    public DataSourceFactory getDataSourceFactory(DwstudyConfiguration configuration) {
      return configuration.getDataSourceFactory();
    }
  };

  public static void main(final String[] args) throws Exception {
    new DwstudyApplication().run(args);
  }

  @Override
  public String getName() {
    return "dwstudy";
  }

  @Override
  public void initialize(final Bootstrap<DwstudyConfiguration> bootstrap) {
    bootstrap.addBundle(hibernate);
  }

  @Override
  public void run(final DwstudyConfiguration configuration, final Environment environment) {
    environment.jersey().register(new HelloResource());
    environment.jersey().register(new PersonResource());
    environment.jersey().register(new PhoneNumberResource(new PhoneNumberDAO(hibernate.getSessionFactory())));
  }
}
