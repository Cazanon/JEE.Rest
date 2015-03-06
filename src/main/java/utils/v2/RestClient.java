package utils.v2;

import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Invocation;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import upm.jbb.IO;

public class RestClient {
    private WebTarget getWebTarget(){
        return ClientBuilder.newClient().target("http://localhost:8080/Rest").path("ordersV2");
    }

    public void getOrders666() {
        WebTarget webTarget = this.getWebTarget().path("666");
        Invocation.Builder invocation = webTarget.request(MediaType.APPLICATION_XML);
        Response response = invocation.get();
        Order order = response.readEntity(Order.class); // response.close()

        IO.getIO().println(
                "GET/ Status: " + response.getStatusInfo() + ":" + response.getStatus()
                        + "\n entity:" + order);
    }
    
    public void getOrders666Client() {
        WebTarget webTarget = this.getWebTarget().path("666").path("client");
        Invocation.Builder invocation = webTarget.request(MediaType.APPLICATION_XML);
        Response response = invocation.get();
        Customer customer = response.readEntity(Customer.class); // response.close()

        IO.getIO().println(
                "GET/ Status: " + response.getStatusInfo() + ":" + response.getStatus()
                        + "\n entity:" + customer);
    }

    public static void main(String[] args) {
        IO.getIO().addview(new RestClient());
    }
}
