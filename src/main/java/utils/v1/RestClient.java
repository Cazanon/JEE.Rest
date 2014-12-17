package utils.v1;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Invocation;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import upm.jbb.IO;

public class RestClient {
    public void getOrdersV0() {
        Client client = ClientBuilder.newClient();
        WebTarget webTarget = client.target("http://localhost:8080/rest");
        webTarget = webTarget.path("orders");
        webTarget = webTarget.queryParam("start", 2).queryParam("size", 5);

        Invocation.Builder invocation = webTarget.request(MediaType.TEXT_PLAIN);

        Response result = invocation.get();

        IO io = IO.getIO();
        io.println("GET/");
        io.println("-- toString: " + result.toString());
        io.println("-- Headers: " + result.getHeaders());
        io.println("-- Status: " + result.getStatus());
        io.println("-- Entity: " + result.readEntity(String.class));
        io.println("-- Links: " + result.getLinks());
        io.println("-- Location: " + result.getLocation());
    }

    public void tiposMime() {
        String[] mediaTypes = {MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON};

        Client client = ClientBuilder.newClient();
        WebTarget webTarget = client.target("http://localhost:8080/rest");
        webTarget = webTarget.path("orders").path(IO.getIO().readString("id"));

        Invocation.Builder invocation = webTarget.request((String) IO.getIO().select(mediaTypes));
        Response result = invocation.get();

        IO.getIO().println("GET/ " + result.readEntity(String.class));
    }

    public static void main(String[] args) {
        IO.getIO().addview(new RestClient());
    }
}
