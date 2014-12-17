package utils.v0;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Invocation;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.apache.logging.log4j.LogManager;

public class RestClient {
    public static void main(String[] args) {
        Client client = ClientBuilder.newClient();
        WebTarget webTarget = client.target("http://localhost:8080/rest");
        webTarget = webTarget.path("orders");
        webTarget = webTarget.queryParam("start", 2).queryParam("size", 5);

        Invocation.Builder invocation = webTarget.request(MediaType.TEXT_PLAIN);

        Response result = invocation.get();

        LogManager.getLogger(RestClient.class).info("GET/");
        LogManager.getLogger(RestClient.class).info("-- toString: " + result.toString());
        LogManager.getLogger(RestClient.class).info("-- Headers: " + result.getHeaders());
        LogManager.getLogger(RestClient.class).info("-- Status: " + result.getStatus());
        LogManager.getLogger(RestClient.class).info("-- Entity: " + result.readEntity(String.class));
        LogManager.getLogger(RestClient.class).info("-- Links: " + result.getLinks());
        LogManager.getLogger(RestClient.class).info("-- Location: " + result.getLocation());
    }
}
