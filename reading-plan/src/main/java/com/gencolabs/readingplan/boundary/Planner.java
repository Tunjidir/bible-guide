package com.gencolabs.readingplan.boundary;

import com.gencolabs.readingplan.entity.Plan;
import io.quarkus.panache.common.Parameters;

import java.time.LocalDate;
import java.util.List;

import javax.enterprise.context.RequestScoped;

/**
 * @author Olatunji oniyide
 */
@RequestScoped
public class Planner {

	public List<Plan> todaysPlan() {
		final var today = LocalDate.now();
		return searchDate(today);
	}

	public List<Plan> allPlan() {
		return Plan.listAll();
	}

	List<Plan> searchDate(final LocalDate date) {
		return Plan.find("date = :date", Parameters.with("date", date)).list();
	}

}
