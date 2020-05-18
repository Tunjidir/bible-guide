package com.gencolabs.readingplan.boundary;

import com.gencolabs.readingplan.control.JsonMapper;
import com.gencolabs.readingplan.entity.Plan;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.List;

import javax.inject.Inject;
import javax.json.JsonArray;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.MediaType;

/**
 * Resource class for fetching bible plans for either the current day, a particular day
 * or the entire year
 * @author Olatunji oniyide
 */
@Produces({ MediaType.APPLICATION_JSON })
@Path("readingplan")
public class PlanResource {

	@Inject
	Planner planner;

	@Inject
	JsonMapper mapper;

	@GET
	public JsonArray getTodaysPlan() {
		final var todaysPlan = planner.todaysPlan();
		return mapper.planToJsonArray(todaysPlan);
	}

	@GET
	@Path("/year")
	public JsonArray all() {
		final var allPlan = planner.allPlan();
		return mapper.planToJsonArray(allPlan);
	}

	@GET
	public JsonArray particularPlan(@QueryParam("date") final String param) {
		try {
			final var date = LocalDate.parse(param, DateTimeFormatter.ofPattern("yyyy-MM-dd"));
			final var particularPlan = planner.searchDate(date);
			return mapper.planToJsonArray(particularPlan);
		} catch (final DateTimeParseException ex) {
			throw new WebApplicationException("error parsing date, use format yyyy-MM-dd", ex);
		}
	}
}
