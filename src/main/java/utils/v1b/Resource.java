package utils.v1b;

import java.util.Arrays;
import java.util.List;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.GenericEntity;
import javax.ws.rs.core.MediaType;

import org.apache.logging.log4j.LogManager;

import utils.HelloRest;

@Path("/resources")
public class Resource {

    @Path("/hello")
    @GET
    @Produces(MediaType.TEXT_PLAIN)
    public String msg() {
        LogManager.getLogger(HelloRest.class).info("GET/ Hello");
        return ">>>Hola, desde RESTful";
    }

    @Path("/listString")
    @GET
    @Produces({MediaType.APPLICATION_XML})
    public GenericEntity<List<String>>  readListString() {
        List<String> list = Arrays.asList("test", "as");

        return new GenericEntity<List<String>>(list) {};
    }


}
