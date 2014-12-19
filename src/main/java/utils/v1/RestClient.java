package utils.v1;

import java.util.List;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Entity;
import javax.ws.rs.client.Invocation;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.GenericType;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import upm.jbb.IO;

public class RestClient {
    public void delete666() {
        Client client = ClientBuilder.newClient();
        WebTarget webTarget = client.target("http://localhost:8080/rest");
        webTarget = webTarget.path("orders");
        webTarget = webTarget.path("666");
        Invocation.Builder invocation = webTarget.request();
        Response response = invocation.delete();

        IO.getIO().println(
                "DELETE/ Status: " + response.getStatusInfo() + ":" + response.getStatus()
                        + "\n order: 666");
        response.close();
    }

    public void get666() {
        Client client = ClientBuilder.newClient();
        WebTarget webTarget = client.target("http://localhost:8080/rest");
        webTarget = webTarget.path("orders");
        webTarget = webTarget.path("666");
        Invocation.Builder invocation = webTarget.request(MediaType.APPLICATION_XML);
        Response response = invocation.get();
        Order order = response.readEntity(Order.class); // response.close()

        IO.getIO().println(
                "GET/ Status: " + response.getStatusInfo() + ":" + response.getStatus()
                        + "\n entity:" + order);
    }

    public void get0() {
        Client client = ClientBuilder.newClient();
        WebTarget webTarget = client.target("http://localhost:8080/rest");
        webTarget = webTarget.path("orders");
        webTarget = webTarget.path("0");
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
        Client client = ClientBuilder.newClient(); // .property("connection.timeout",
                                                   // 100)
        // .sslContext(sslContext)
        // .register(JacksonJsonProvider.class)
        // .build();
        WebTarget webTarget = client.target("http://localhost:8080/rest");
        webTarget = webTarget.path("orders");
        webTarget = webTarget.queryParam("start", 2).queryParam("size", 5);
        Invocation.Builder invocation = webTarget.request(MediaType.APPLICATION_XML);
        Response response = invocation.get();

        List<Order> list = response.readEntity(new GenericType<List<Order>>() {
        });

        IO.getIO().println(
                "GET/ Status: " + response.getStatusInfo() + ":" + response.getStatus()
                        + "\n group:" + list);
    }

    public void search() {
        Client client = ClientBuilder.newClient();
        WebTarget webTarget = client.target("http://localhost:8080/rest");
        webTarget = webTarget.path("orders/search");
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

        Client client = ClientBuilder.newClient();
        WebTarget webTarget = client.target("http://localhost:8080/rest");
        webTarget = webTarget.path("orders").path("666");
        Invocation.Builder invocation = webTarget.request((String) IO.getIO().select(mediaTypes));
        Response response = invocation.get();

        IO.getIO().println(
                "GET/ Status: " + response.getStatusInfo() + ":" + response.getStatus()
                        + "\n get: " + response.readEntity((Class<?>) IO.getIO().select(clazz)));
    }

    public void create() {
        Client client = ClientBuilder.newClient();
        Order order = new Order("demonio");
        Response response = client.target("http://localhost:8080/rest").path("orders").request()
                .post(Entity.xml(order));
        IO.getIO().println(
                "POST/ Status: " + response.getStatusInfo() + ":" + response.getStatus()
                        + "\n Location: " + response.getLocation().toString() + "\n order: "
                        + response.readEntity(Order.class));
    }

    public void update() {
        Client client = ClientBuilder.newClient();
        Order order = new Order("demonio");
        Response response = client.target("http://localhost:8080/rest").path("orders").path("666")
                .request().put(Entity.xml(order));
        IO.getIO().println(
                "PUT/ Status: " + response.getStatusInfo() + ":" + response.getStatus()
                        + "\n order: " + response.readEntity(Order.class));
    }

    public static void main(String[] args) {
        IO.getIO().addview(new RestClient());
    }
}
