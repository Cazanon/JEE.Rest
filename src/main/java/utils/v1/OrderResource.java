package utils.v1;

import javax.ws.rs.DefaultValue;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;

import org.apache.logging.log4j.LogManager;

@Path("/orders")
public class OrderResource {

    @Path("/{id}")
    @GET
    @Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    public Order readOne(@PathParam("id") int id) {
        Order order = new Order(id, "Desc" + id);
        LogManager.getLogger(OrderResource.class).info("GET/ orders(id):" + order);
        return order;
    }

    @GET
    @Produces(MediaType.TEXT_PLAIN)
    public String readGroup(@DefaultValue("0") @QueryParam("start") int start,
            @DefaultValue("10") @QueryParam("size") int size) {
        String response = "De " + start + " to " + (start + size) + "...";
        LogManager.getLogger(OrderResource.class).info("GET/ orders:" + response);
        return response;
    }
}
