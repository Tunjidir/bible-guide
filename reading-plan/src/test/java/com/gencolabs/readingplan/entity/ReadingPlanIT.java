package com.gencolabs.readingplan.entity;

import java.time.LocalDate;
import java.util.Date;

import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;

import org.junit.ClassRule;
import org.junit.Test;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.testcontainers.containers.PostgreSQLContainer;
import org.testcontainers.junit.jupiter.Testcontainers;

import com.gencolabs.readingplan.boundary.Planner;
import io.quarkus.test.junit.QuarkusTest;

/**
 *
 * @author Olatunji oniyide
 */
@Testcontainers
@QuarkusTest
public class ReadingPlanIT {

  @ClassRule
  public static PostgreSQLContainer postgres = new PostgreSQLContainer();
  
  @BeforeAll
  public void init() {
    System.setProperty("quarkus.datasource.url", postgres.getJdbcUrl());
    System.setProperty("quarkus.datasource.username", postgres.getUsername());
    System.setProperty("quarkus.datasource.driver", postgres.getDriverClassName());
    System.setProperty("quarkus.datasource.password", postgres.getPassword());
  }

  @Test
  public void validateInsert() {
    final var plan = new Plan();
    plan.setPortion("Matthew Chapter 2");
    plan.setDosage(Session.MORNING);
    plan.setDate(LocalDate.now());

    plan.persist();

    final long found = Plan.count();
    Assertions.assertEquals(1L, found, "only one plan in the database");
  } 
}
