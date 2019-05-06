package com.airhacks.readingplan.boundary;

import com.airhacks.readingplan.entity.ReadingPlan;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.json.Json;
import javax.json.JsonArray;
import javax.json.JsonObject;
import javax.json.JsonObjectBuilder;
import javax.json.stream.JsonCollectors;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.Response;

/**
 *
 * @author Olatunji
 */
@Stateless
@Path("readingplan")
public class ReadingResource {
  
  @Inject
  Planner planner;
  
  @GET
  public Response getTodaysPlan() {
    List<ReadingPlan> todaysPlan = planner.todaysPlan();
    JsonArray array = todaysPlan.stream()
            .map(this::toJson)
            .collect(JsonCollectors.toJsonArray());
    return Response.ok().entity(array).build();
  }
  
  @GET
  @Path("/all")
  public Response all() {
    List<ReadingPlan> allPlan = planner.allPlan();
    JsonArray array = allPlan.stream()
            .map(this::toJson)
            .collect(JsonCollectors.toJsonArray());
    return Response.ok().entity(array).build();
  }
  
  @GET
  @Path("/{date}")
  public Response particularPlan(@PathParam("date") String param) {
    try {
      Date date = new SimpleDateFormat("yyyy-MM-dd").parse(param);
      List<ReadingPlan> particularPlan = planner.particularDate(date);
      JsonArray array = particularPlan.stream()
              .map(this::toJson)
              .collect(JsonCollectors.toJsonArray());
      return Response.ok().entity(array).build();
    } catch (ParseException ex) {
      throw new WebApplicationException("error parsing date, use format yyyy-MM-dd", ex);
    }
  }
  
  public JsonObject toJson(ReadingPlan input) {
    JsonObjectBuilder builder = Json.createObjectBuilder();
    builder.add("verse", input.getPortion())
            .add("session", input.getSession().name())
            .add("date", input.getDate().toString());
    return builder.build();
  }
  
}
