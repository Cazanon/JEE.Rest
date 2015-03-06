package container;

import java.net.URI;

import javax.ws.rs.core.UriBuilder;

import org.glassfish.jersey.jdkhttp.JdkHttpServerFactory;
import org.glassfish.jersey.server.ResourceConfig;

import utils.HelloRest;

import com.sun.net.httpserver.HttpServer;

@SuppressWarnings("restriction")
public class JdkHttpServer {
    public static void main(String[] args) {
        URI baseUri = UriBuilder.fromUri("http://localhost/").port(9998).build();
        ResourceConfig config = new ResourceConfig(HelloRest.class);
        HttpServer server = JdkHttpServerFactory.createHttpServer(baseUri, config);
        server.start();
    }
}
