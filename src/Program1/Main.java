package Program1;

import com.sun.net.httpserver.HttpServer;

import java.io.IOException;
import java.net.InetSocketAddress;

public class Main {

    public static void main(String[] args) throws IOException {
        int port = 8080;
        HttpServer server = HttpServer.create(new InetSocketAddress(port), 0);
        System.out.println("Listening...");

        server.createContext("/1" , new RootHandler("1.txt"));
        server.createContext("/2", new RootHandler("2.txt"));
        server.createContext("/3", new RootHandler("3.txt"));
        server.createContext("/4", new RootHandler("4.txt"));
        server.createContext("/5", new RootHandler("5.txt"));
        server.createContext("/",new ErrorHandler());
        server.setExecutor(null);
        server.start();
    }
}
