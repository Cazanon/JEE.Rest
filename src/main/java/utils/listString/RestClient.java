package utils.listString;

import java.util.List;

import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Invocation;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.Response;

import upm.jbb.IO;

public class RestClient {
    private WebTarget getWebTarget() {
        return ClientBuilder.newClient().target("http://localhost:8080/Rest").path("resources");
    }

    public void hello() {
        WebTarget webTarget = this.getWebTarget().path("hello");
        Invocation.Builder invocation = webTarget.request();
        Response response = invocation.get();
        IO.getIO().println("GET/ hello: " + response.toString());
    }

    public void getListString() {
        WebTarget webTarget = this.getWebTarget().path("listString");
        Invocation.Builder invocation = webTarget.request();
        Response response = invocation.get();
        List<String> list = response.readEntity(ListStringWrapper.class).getListString();
        IO.getIO().println("GET/ listString: " + list);
    }

    public static void main(String[] args) {
        IO.getIO().addview(new RestClient());
    }
}
