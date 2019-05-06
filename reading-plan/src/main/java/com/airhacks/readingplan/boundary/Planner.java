package com.airhacks.readingplan.boundary;

import com.airhacks.readingplan.entity.ReadingPlan;
import static com.airhacks.readingplan.entity.ReadingPlan.ALL_PLAN;
import static com.airhacks.readingplan.entity.ReadingPlan.BY_DATE;
import java.util.Date;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TemporalType;

/**
 *
 * @author Olatunji
 */
@Stateless
public class Planner {
  
  @PersistenceContext
  EntityManager manager;
  
  public List<ReadingPlan> todaysPlan() {
    Date today = new Date();
    return particularDate(today);
  }
  
  public List<ReadingPlan> allPlan() {
     return manager.createNamedQuery(ALL_PLAN, ReadingPlan.class)
            .getResultList();
  }
  
  public List<ReadingPlan> particularDate(Date date) {
     return manager.createNamedQuery(BY_DATE, ReadingPlan.class)
            .setParameter("date", date, TemporalType.DATE)
            .getResultList();
  }
  
}
