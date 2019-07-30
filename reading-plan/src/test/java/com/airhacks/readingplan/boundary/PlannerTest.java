package com.airhacks.readingplan.boundary;

import com.airhacks.readingplan.entity.ReadingPlan;
import static com.airhacks.readingplan.entity.ReadingPlan.ALL_PLAN;
import static com.airhacks.readingplan.entity.Session.EVENING;
import static com.airhacks.readingplan.entity.Session.MORNING;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.TemporalType;
import javax.persistence.TypedQuery;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;
import org.junit.Before;
import org.junit.Test;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

/**
 *
 * @author user
 */
public class PlannerTest {
  
  private Planner planner;
  private TypedQuery query;
  
  @Before
  public void initialize() {
    this.planner = new Planner();
    this.planner.manager = mock(EntityManager.class);
    query = mock(TypedQuery.class);
    when(this.planner.manager.createNamedQuery(ALL_PLAN, ReadingPlan.class)).thenReturn(query);
    when(query.setParameter(ALL_PLAN, new Date(), TemporalType.DATE)).thenReturn(query);
    when(query.getResultList()).thenReturn(plans());
  }
  
  @Test
  public void getTodaysPlan() {
    List<ReadingPlan> plans = this.planner.allPlan();
    verify(query).getResultList();
    ReadingPlan retrieved = plans.get(0);
    assertThat(retrieved.getSession(), is(MORNING));
  }
  
  @Test
  public void getAllPlan() {
    List<ReadingPlan> plans = this.planner.allPlan();
    verify(query).getResultList();
    ReadingPlan retrieved = plans.get(0);
    assertThat(retrieved.getSession(), is(MORNING));
  }
  
  @Test
  public void getParticularPlan() {    
    List<ReadingPlan> plans = this.planner.allPlan();
    verify(query).getResultList();
    ReadingPlan retrieved = plans.get(0);
    assertThat(retrieved.getSession(), is(MORNING));
  }
  
  public List<ReadingPlan> plans() {
      List<ReadingPlan> mockPlans = new ArrayList<>();
      mockPlans.add(new ReadingPlan("Matthew Chapter 1", MORNING, new Date()));
      mockPlans.add(new ReadingPlan("Exodus Chapter 2", EVENING, new Date()));
      return mockPlans;
  }
}
