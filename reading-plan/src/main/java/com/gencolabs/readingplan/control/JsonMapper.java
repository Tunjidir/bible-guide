package com.gencolabs.readingplan.control;

import java.util.List;

import javax.json.Json;
import javax.json.JsonArray;
import javax.json.JsonObject;
import javax.json.JsonObjectBuilder;
import javax.json.stream.JsonCollectors;

import com.gencolabs.readingplan.entity.Plan;

/**
 * @author olatunji
 */
public class JsonMapper {

	public JsonArray planToJsonArray(final List<Plan> readingPlans) {
		return readingPlans.stream()
				.map(this::toJson)
				.collect(JsonCollectors.toJsonArray());
	}

	JsonObject toJson(final Plan input) {
		final var builder = Json.createObjectBuilder();
		builder.add("verse", input.getPortion())
				.add("session", input.getSession().name())
				.add("date", input.getDate().toString());
		return builder.build();
	}
}
