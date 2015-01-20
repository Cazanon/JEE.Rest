package utils.v2;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.apache.logging.log4j.LogManager;

@Path("/ordersV2/{id}/client")
public class OrderClientResource extends OrderResource {

    @GET
    @Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    public Response readOne(@PathParam("id") int id) {
        Customer customer = new Customer(1, "Order:" + id);
        LogManager.getLogger(OrderClientResource.class).info("GET/ orders(id):" + customer);
        return Response.ok(customer).build(); // return order;
    }

}
