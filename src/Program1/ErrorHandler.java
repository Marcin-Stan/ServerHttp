package Program1;

import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;

import java.io.IOException;
import java.io.OutputStream;

public class ErrorHandler implements HttpHandler {
    @Override
    public void handle(HttpExchange exchange) throws IOException {
        String error = "Magazyn nie istnieje lub wpisales blednie magazyn! Wpisz np. http://localhost:8080/1";
        exchange.sendResponseHeaders(200, error.length());
        OutputStream os = exchange.getResponseBody();
        os.write(error.getBytes());
        os.close();
    }
}
