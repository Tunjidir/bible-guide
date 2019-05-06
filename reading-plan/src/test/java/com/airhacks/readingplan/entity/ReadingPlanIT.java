package com.airhacks.readingplan.entity;

import java.util.Date;
import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import org.junit.Before;
import org.junit.Test;


/**
 *
 * @author Olatunji
 */
public class ReadingPlanIT {
  
  EntityManager em;
  EntityTransaction tx;
  
  @Before
  public void init() {
    this.em = Persistence.createEntityManagerFactory("integration-test").createEntityManager();
    this.tx = this.em.getTransaction();
  }

  @Test
  public void validateORMapping() {
    tx.begin();
    this.em.merge(new ReadingPlan("Matthew Chapter 2", Session.MORNING, new Date()));
    tx.commit();
  } 
}
