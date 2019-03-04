package jpa;

import jpa.auditor.UserAuditorAware;
import jpa.entity.Class;
import jpa.entity.School;
import jpa.entity.Student;
import jpa.entity.Teacher;
import jpa.repo.SchoolRepo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

import java.util.*;

/**
 * @author yaodw
 */
@Slf4j
@EnableJpaAuditing
@SpringBootApplication
public class Application {

    public static void main(String[] args) {
        SpringApplication.run(Application.class);
    }

    @Bean
    public UserAuditorAware setUserAuditorAware(){
        return new UserAuditorAware();
    }

}
