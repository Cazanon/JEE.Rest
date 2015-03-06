package utils.v1;

import java.net.URI;
import java.util.ArrayList;
import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.DefaultValue;
import javax.ws.rs.GET;
import javax.ws.rs.MatrixParam;
import javax.ws.rs.NotFoundException;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
//import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.apache.logging.log4j.LogManager;

@Path("/ordersV1")
public class OrderResource {

    @Path("/{id}")
    @GET
    @Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    public Response readOne(@PathParam("id") int id) {
        Order order = new Order(id, "Desc" + id);
        if (id != 0) {
            LogManager.getLogger(OrderResource.class).info("GET/ orders(id):" + order);
            return Response.ok(order).build();
            // return order;
        } else {
            throw new NotFoundException();
            // throw new WebApplicationException(Response.Status.NOT_FOUND);
            // return Response.status(Response.Status.NOT_FOUND).build();
        }
    }

    @Path("/{id}/description")
    @GET
    @Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    public String readProperty(@PathParam("id") int id) {
        Order order = new Order(id, "Desc" + id);
        LogManager.getLogger(OrderResource.class).info(
                "GET/ orders(id)/description:" + order.getDescription());
        return order.getDescription();
    }

    @Path("/{id}/boolean")
    @GET
    @Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    public String readPropertyBoolean(@PathParam("id") int id) {
        Boolean result = true;
        LogManager.getLogger(OrderResource.class).info("GET/ orders(id)/is:" + result);
        return Boolean.toString(result);
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
    public List<Order> search(@MatrixParam("description") String description,
            @MatrixParam("atributo") String valor) {
        List<Order> list = new ArrayList<>();
        list.add(new Order(345, description));
        list.add(new Order(123, valor));
        LogManager.getLogger(OrderResource.class).info("GET/ search:" + list);
        return list;
    }

    @POST
    @Consumes(MediaType.APPLICATION_XML)
    public Response create(Order order) {
        order.setId(274932659);
        LogManager.getLogger(OrderResource.class).info("POST/ order:" + order);
        return Response.created(URI.create("orders/" + order.getId())).entity(order).build();
    }

    @POST
    @Path("sin")
    public Response createSin() {
        Order order = new Order("sin datos");
        order.setId(666);
        LogManager.getLogger(OrderResource.class).info("POST/ order:" + order);
        return Response.created(URI.create("orders/" + order.getId())).entity(order).build();
    }

    @PUT
    @Path("{id}")
    @Consumes("application/xml")
    public Order update(@PathParam("id") int id, Order order) {
        order.setDescription("actualizado");
        order.setId(id);
        LogManager.getLogger(OrderResource.class).info("PUT/ order:" + order);
        return order;
    }

    @DELETE
    @Path("{id}")
    @Consumes(MediaType.APPLICATION_XML)
    public void delete(@PathParam("id") int id) {
        LogManager.getLogger(OrderResource.class).info("DELETE/ id: " + id);
    }

}
