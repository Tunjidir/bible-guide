package com.gencolabs.readingplan.entity;

import java.time.LocalDate;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;

import io.quarkus.hibernate.orm.panache.PanacheEntity;

/**
 *
 * @author olatunji
 */
@Entity
public class Plan extends PanacheEntity {

  private String portion;
  
  @Enumerated(EnumType.STRING)
  private Session dosage;

  private LocalDate date;

  public Plan() {
    //default JPA constructor
  }

  public String getPortion() {
    return portion;
  }

  public Session getSession() {
    return dosage;
  }

  public LocalDate getDate() {
    return date;
  }

  public void setPortion(String portion) {
    this.portion = portion;
  }

  public void setDosage(Session dosage) {
    this.dosage = dosage;
  }

  public void setDate(LocalDate date) {
    this.date = date;
  }
}
