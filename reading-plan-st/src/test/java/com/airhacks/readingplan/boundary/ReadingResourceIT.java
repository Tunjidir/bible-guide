
package com.airhacks.readingplan.boundary;

import javax.json.JsonArray;
import javax.ws.rs.WebApplicationException;
import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.MediaType;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import org.junit.Before;
import org.junit.Test;

/**
 *
 * @author olatunji
 */
public class ReadingResourceIT {
  
  private Client client;
  private WebTarget tut;
  private String rut_uri = "http://localhost:8080/reading-plan/resources/readingplan";
  
  @Before
  public void init() {
    client = ClientBuilder.newClient();
    tut = client.target(rut_uri);
  }
  
  @Test
  public void getTodaysPlan() {
    JsonArray response = this.tut.request(MediaType.APPLICATION_JSON).get(JsonArray.class);
    assertNotNull(response);
  }
  
  @Test
  public void getAllPlan() {
    String uri = rut_uri + "/all";
    tut = client.target(uri);
    JsonArray response = this.tut.request(MediaType.APPLICATION_JSON).get(JsonArray.class);
    assertNotNull(response);
  }
  
  @Test
  public void getParticularPlan() {
    String uri = rut_uri +"/2019-01-01";
    tut = client.target(uri);
    JsonArray response = this.tut.request(MediaType.APPLICATION_JSON).get(JsonArray.class);
    assertNotNull(response);
  }
  
  @Test(expected = WebApplicationException.class)
  public void getPlanInvalidDate() {
    String uri = rut_uri +"/20190101";
    tut = client.target(uri);
    JsonArray response = this.tut.request(MediaType.APPLICATION_JSON).get(JsonArray.class);
    assertNull(response);
  }
}
