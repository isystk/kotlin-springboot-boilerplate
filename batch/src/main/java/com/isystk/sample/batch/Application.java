package com.isystk.sample.batch;

import com.isystk.sample.ComponentScanBasePackage;
import org.slf4j.Logger;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.WebApplicationType;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

@SpringBootApplication(scanBasePackageClasses = ComponentScanBasePackage.class)
public class Application {

  private static final Logger log = org.slf4j.LoggerFactory.getLogger(Application.class);

  public static void main(String[] args) throws Exception {

    try {
      SpringApplication application = new SpringApplication(Application.class);
      application.setWebApplicationType(WebApplicationType.NONE);

      ConfigurableApplicationContext context = application.run(args);
      int exitCode = SpringApplication.exit(context);
      System.exit(exitCode);
    } catch (Throwable t) {
      log.error("failed to run. ", t);
      System.exit(1);
    }
  }
}
