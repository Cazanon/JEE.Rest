package utils.v1;

import java.util.List;

import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Entity;
import javax.ws.rs.client.Invocation;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.GenericType;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import upm.jbb.IO;

public class RestClient {
    private WebTarget getWebTarget() {
        return ClientBuilder.newClient().target("http://localhost:8080/rest").path("ordersV1");
    }

    public void delete666() {
        WebTarget webTarget = this.getWebTarget().path("666");
        Invocation.Builder invocation = webTarget.request();
        Response response = invocation.delete();
        IO.getIO().println(
                "DELETE/ Status: " + response.getStatusInfo() + ":" + response.getStatus()
                        + "\n order: 666");
        response.close();
    }

    public void get666() {
        WebTarget webTarget = this.getWebTarget().path("666");
        Invocation.Builder invocation = webTarget.request(MediaType.APPLICATION_XML);
        Response response = invocation.get();
        Order order = response.readEntity(Order.class); // response.close()

        IO.getIO().println(
                "GET/ Status: " + response.getStatusInfo() + ":" + response.getStatus()
                        + "\n entity:" + order);
    }

    public void get666Description() {
        WebTarget webTarget = this.getWebTarget().path("666").path("description");
        Invocation.Builder invocation = webTarget.request(MediaType.APPLICATION_XML);
        Response response = invocation.get();
        String description = response.readEntity(String.class); // response.close()

        IO.getIO().println(
                "GET/ Status: " + response.getStatusInfo() + ":" + response.getStatus()
                        + "\n entity:" + description);
    }

    public void get666is() {
        WebTarget webTarget = this.getWebTarget().path("666").path("is");
        Invocation.Builder invocation = webTarget.request(MediaType.APPLICATION_XML);
        Response response = invocation.get();
        Boolean is = Boolean.valueOf(response.readEntity(String.class)); // response.close()

        IO.getIO().println(
                "GET/ Status: " + response.getStatusInfo() + ":" + response.getStatus()
                        + "\n entity:" + is);
    }

    public void get0() {
        WebTarget webTarget = this.getWebTarget().path("0");
        Invocation.Builder invocation = webTarget.request(MediaType.APPLICATION_XML);
        Response response = invocation.get();
        Order order = null;
        if (response.getStatusInfo() == Response.Status.OK)
            order = response.readEntity(Order.class);

        IO.getIO().println(
                "GET/ Status: " + response.getStatusInfo() + ":" + response.getStatus()
                        + "\n entity:" + order);
    }

    public void getOrders() {
        WebTarget webTarget = this.getWebTarget().queryParam("start", 2).queryParam("size", 5);
        Invocation.Builder invocation = webTarget.request(MediaType.APPLICATION_XML);
        Response response = invocation.get();

        List<Order> list = response.readEntity(new GenericType<List<Order>>() {
        });

        IO.getIO().println(
                "GET/ Status: " + response.getStatusInfo() + ":" + response.getStatus()
                        + "\n group:" + list);
    }

    public void search() {
        WebTarget webTarget = this.getWebTarget().path("search");
        webTarget = webTarget.matrixParam("description", "una").matrixParam("atributo", "valor");
        Invocation.Builder invocation = webTarget.request(MediaType.APPLICATION_XML);
        Response response = invocation.get();
        List<Order> list = response.readEntity(new GenericType<List<Order>>() {
        });

        IO.getIO().println(
                "GET/ Status: " + response.getStatusInfo() + ":" + response.getStatus()
                        + "\n search:" + list);
    }

    public void tiposMime() {
        String[] mediaTypes = {MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON};
        Class<?>[] clazz = {String.class, Order.class};

        WebTarget webTarget = this.getWebTarget().path("666");
        Invocation.Builder invocation = webTarget.request((String) IO.getIO().select(mediaTypes));
        Response response = invocation.get();

        IO.getIO().println(
                "GET/ Status: " + response.getStatusInfo() + ":" + response.getStatus()
                        + "\n get: " + response.readEntity((Class<?>) IO.getIO().select(clazz)));
    }

    public void create() {
        Order order = new Order("demonio");
        Response response = this.getWebTarget().request().post(Entity.xml(order));
        IO.getIO().println(
                "POST/ Status: " + response.getStatusInfo() + ":" + response.getStatus()
                        + "\n Location: " + response.getLocation().toString() + "\n order: "
                        + response.readEntity(Order.class));
    }

    public void createSin() {
        Response response = this.getWebTarget().path("sin").request().post(null);
        IO.getIO().println(
                "POST/ Status: " + response.getStatusInfo() + ":" + response.getStatus()
                        + "\n Location: " + response.getLocation().toString() + "\n order: "
                        + response.readEntity(Order.class));
    }

    public void update() {
        Order order = new Order("demonio");
        Response response = this.getWebTarget().path("666").request().put(Entity.xml(order));
        IO.getIO().println(
                "PUT/ Status: " + response.getStatusInfo() + ":" + response.getStatus()
                        + "\n order: " + response.readEntity(Order.class));
    }

    public static void main(String[] args) {
        IO.getIO().addview(new RestClient());
    }
}
