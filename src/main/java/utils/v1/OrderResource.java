package utils.v1;

import java.net.URI;
import java.util.ArrayList;
import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.DefaultValue;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

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
    @Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    public List<Order> readGroup(@DefaultValue("0") @QueryParam("start") int start,
            @DefaultValue("10") @QueryParam("size") int size) {
        List<Order> list = new ArrayList<>();
        for (int i = 0; i < size; i++) {
            list.add(new Order(start + i, "group"));
        }
        LogManager.getLogger(OrderResource.class).info("GET/ orders:" + list);
        return list;
    }

    @Path("/search")
    @GET
    @Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    public List<Order> search(@QueryParam("description") String description) {
        List<Order> list = new ArrayList<>();
        list.add(new Order(345, description));
        list.add(new Order(123, description));
        return list;
    }

    @POST
    @Consumes(MediaType.APPLICATION_XML)
    public Response create(Order order) {
        order.setId(274932659);
        LogManager.getLogger(OrderResource.class).info("POST/ order:" + order);
        return Response.created(URI.create("/orders/" + order.getId())).build();
    }

}
