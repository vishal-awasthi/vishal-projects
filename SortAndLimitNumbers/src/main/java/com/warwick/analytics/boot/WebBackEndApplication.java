package com.warwick.analytics.boot;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.PropertySource;

@SpringBootApplication(scanBasePackages = {"com.warwick.analytics"})
@PropertySource(value = {"classpath:config.properties"})
public class WebBackEndApplication
{
    public static void main(String[] args) {
          SpringApplication.run(WebBackEndApplication.class, args);
    }
}

