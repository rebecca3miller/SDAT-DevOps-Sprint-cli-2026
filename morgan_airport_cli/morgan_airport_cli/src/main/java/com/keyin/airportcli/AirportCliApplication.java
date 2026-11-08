package com.keyin.airportcli;

import com.keyin.airportcli.client.AirportApiClient;
import com.keyin.airportcli.client.JavaHttpTransport;
import com.keyin.airportcli.ui.ConsoleMenu;

import java.net.http.HttpClient;

public class AirportCliApplication {
    public static void main(String[] args) {
        String baseUrl = System.getenv().getOrDefault(
                "AIRPORT_API_URL", "http://localhost:8080");

        AirportApiClient api = new AirportApiClient(
                baseUrl,
                new JavaHttpTransport(HttpClient.newHttpClient())
        );

        new ConsoleMenu(api).run();
    }
}
