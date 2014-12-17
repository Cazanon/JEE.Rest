package utils;

import java.util.List;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Entity;
import javax.ws.rs.client.Invocation;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.GenericType;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;


public class WebService {
    private static final String URI = "http://localhost:8080/TicTacToe/rest";

    private String token;

    public void setToken(String token) {
        this.token = token;
    }

    public Object create(String resource, Object entity) {
        Client client = ClientBuilder.newClient();
        WebTarget webTarget = client.target(URI);
        webTarget = webTarget.path("{resource}").resolveTemplate("resource", resource);

        if (this.token != null) {
            webTarget = webTarget.queryParam("token", this.token);
        }

        Response response = webTarget.request().post(Entity.json(entity));

 
        boolean result = response.getStatus() == 201;

        client.close();
        return result;
    }

    public Object read(String resource, Class<?> ResponseClass) {
        Client client = ClientBuilder.newClient();
        WebTarget webTarget = client.target(URI);
        webTarget = webTarget.path("{resource}").resolveTemplate("resource", resource);

        if (this.token != null) {
            webTarget = webTarget.queryParam("token", this.token);
        }

        Invocation.Builder invocation = webTarget.request(MediaType.APPLICATION_JSON);

        Object result = invocation.get(ResponseClass);


        client.close();
        return result;
    }

    public List<?> readCollection(String resource) {
        Client client = ClientBuilder.newClient();
        WebTarget webTarget = client.target(URI);
        webTarget = webTarget.path("{resource}").resolveTemplate("resource", resource);

        if (this.token != null) {
            webTarget = webTarget.queryParam("token", this.token);
        }

        Invocation.Builder invocation = webTarget.request(MediaType.APPLICATION_JSON);

        List<?> result = invocation.get(new GenericType<List<?>>() {
        });

         client.close();
        return result;
    }

    public void delete(String resource) {
        Client client = ClientBuilder.newClient();
        WebTarget webTarget = client.target(URI);
        webTarget = webTarget.path("{resource}").resolveTemplate("resource", resource);

        if (this.token != null) {
            webTarget = webTarget.queryParam("token", this.token);
        }

        webTarget.request().delete();

    }

    public Boolean update(String resource, Object entity) {
        Client client = ClientBuilder.newClient();
        WebTarget webTarget = client.target(URI);
        webTarget = webTarget.path("{resource}").resolveTemplate("resource", resource);

        if (this.token != null) {
            webTarget = webTarget.queryParam("token", this.token);
        }

        Response response = webTarget.request().put(Entity.json(entity));


        boolean result = response.getStatus() == 201;

        client.close();
        return result;
    }

}
