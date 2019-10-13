package io.kid19999.backstage;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@MapperScan("io.kid19999.backstage.mapper")
@SpringBootApplication
public class BackstageApplication {

  public static void main(String[] args) {
    SpringApplication.run(BackstageApplication.class, args);
  }

}
