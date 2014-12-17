package utils;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import org.apache.logging.log4j.LogManager;

@Path("/hello")
public class HelloRest {
    @GET
    @Produces(MediaType.TEXT_PLAIN)
    public String msg() {
        LogManager.getLogger(HelloRest.class).info("GET/ Hello");
        return ">>>Hola, desde RESTful";
    }
}
