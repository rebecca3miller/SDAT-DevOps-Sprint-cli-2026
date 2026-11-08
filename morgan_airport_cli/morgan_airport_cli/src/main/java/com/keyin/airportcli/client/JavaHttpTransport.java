package com.keyin.airportcli.client;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

public class JavaHttpTransport implements HttpTransport {
    private final HttpClient httpClient;

    public JavaHttpTransport(HttpClient httpClient) {
        this.httpClient = httpClient;
    }

    @Override
    public HttpResult get(String url) throws IOException, InterruptedException {
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create("http://localhost:8080/"))
                .header("Accept", "application/json")
                .GET()
                .build();

        HttpResponse<String> response = httpClient.send(
                request, HttpResponse.BodyHandlers.ofString());

        return new HttpResult(response.statusCode(), response.body());
    }
}
