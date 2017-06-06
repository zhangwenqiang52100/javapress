package com.zwq.resource;

import com.fasterxml.jackson.jaxrs.json.JacksonJsonProvider;
import java.util.HashSet;
import java.util.Set;
import javax.ws.rs.ApplicationPath;
import javax.ws.rs.core.Application;
import org.glassfish.jersey.server.ResourceConfig;

/**
 * Created by Lancer on 2017/4/21.
 */
@ApplicationPath("/rest/")
public class RestfulApplication extends ResourceConfig {

  public RestfulApplication() {
    packages("com.zwq.resource.api");
    register(JacksonJsonProvider.class);
  }
}
