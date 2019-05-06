package com.airhacks.readingplan.entity;

import static com.airhacks.readingplan.entity.ReadingPlan.ALL_PLAN;
import static com.airhacks.readingplan.entity.ReadingPlan.BY_DATE;
import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 *
 * @author tunjidir
 */
@Entity
@Table(name = "plan")
@NamedQueries({
  @NamedQuery(name = ALL_PLAN, query = "SELECT r FROM ReadingPlan r"), 
  @NamedQuery(name = BY_DATE, query = "SELECT r FROM ReadingPlan r WHERE r.currentDate = :date")
})
public class ReadingPlan {
  
  public static final String ALL_PLAN = "ReadingPlan.All";
  public static final String BY_DATE = "ReadingPlan.ByDate";

  @Id
  @Column(name = "portion")
  private String portion;
  
  @Enumerated(EnumType.STRING)
  @Column(name = "dosage")
  private Session dosage;
  
  @Temporal(TemporalType.DATE)
  @Column(name = "currentDate")
  private Date currentDate;

  public ReadingPlan(String portion, Session dosage, Date currentDate) {
    this.portion = portion;
    this.dosage = dosage;
    this.currentDate = currentDate;
  }
  
  public ReadingPlan() {
  }

  public String getPortion() {
    return portion;
  }

  public Session getSession() {
    return dosage;
  }

  public Date getDate() {
    return currentDate;
  }
}
