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
    public void getOrders() {
        Client client = ClientBuilder.newClient();
        WebTarget webTarget = client.target("http://localhost:8080/rest");
        webTarget = webTarget.path("orders");
        webTarget = webTarget.queryParam("start", 2).queryParam("size", 5);

        Invocation.Builder invocation = webTarget.request(MediaType.APPLICATION_XML);

        List<Order> list = invocation.get(new GenericType<List<Order>>() {
        });
        IO.getIO().println("GET/ group:" + list);
    }

    public void search() {
        Client client = ClientBuilder.newClient();
        WebTarget webTarget = client.target("http://localhost:8080/rest");
        webTarget = webTarget.path("orders/search");
        webTarget = webTarget.queryParam("description", "una");

        Invocation.Builder invocation = webTarget.request(MediaType.APPLICATION_XML);

        List<Order> list = invocation.get(new GenericType<List<Order>>() {
        });
        IO.getIO().println("GET/ search:" + list);
    }

    public void tiposMime() {
        String[] mediaTypes = {MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON};
        Class<?>[] clazz = {String.class, Order.class};

        Client client = ClientBuilder.newClient();
        WebTarget webTarget = client.target("http://localhost:8080/rest");
        webTarget = webTarget.path("orders").path("666");

        Invocation.Builder invocation = webTarget.request((String) IO.getIO().select(mediaTypes));
        Response result = invocation.get();

        IO.getIO().println("GET/ " + result.readEntity((Class<?>) IO.getIO().select(clazz)));
    }

    public void create() {
        Client client = ClientBuilder.newClient();
        Order order = new Order("demonio");
        Response response = client.target("http://localhost:8080/rest").path("orders").request()
                .post(Entity.xml(order));
        if (response.getStatus() != 201) {
            IO.getIO().println("Error...");
        } else {
            IO.getIO().println("Location: " + response.getLocation().toString());
        }
    }

    public static void main(String[] args) {
        IO.getIO().addview(new RestClient());
    }
}
