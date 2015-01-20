package utils.v2;

import javax.ws.rs.GET;
import javax.ws.rs.NotFoundException;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.apache.logging.log4j.LogManager;

@Path("/ordersV2")
public class OrderResource {

    @Path("/{id}")
    @GET
    @Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    public Response readOne(@PathParam("id") int id) {
        Order order = new Order(id, "Desc" + id, new Customer());
        if (id == 0) {
            throw new NotFoundException();
        } else {
            LogManager.getLogger(OrderResource.class).info("GET/ orders(id):" + order);
            return Response.ok(order).build(); // return order;
        }
    }

}
