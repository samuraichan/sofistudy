package com.sofi.study;

import io.dropwizard.Application;
import io.dropwizard.setup.Bootstrap;
import io.dropwizard.setup.Environment;

import com.sofi.study.resources.HelloResource;
import com.sofi.study.resources.PersonResource;

public class DwstudyApplication extends Application<DwstudyConfiguration> {

  public static void main(final String[] args) throws Exception {
    new DwstudyApplication().run(args);
  }

  @Override
  public String getName() {
    return "dwstudy";
  }

  @Override
  public void initialize(final Bootstrap<DwstudyConfiguration> bootstrap) {
    // TODO: application initialization
  }

  @Override
  public void run(final DwstudyConfiguration configuration, final Environment environment) {
    environment.jersey().register(new HelloResource());
    environment.jersey().register(new PersonResource());
  }
}
