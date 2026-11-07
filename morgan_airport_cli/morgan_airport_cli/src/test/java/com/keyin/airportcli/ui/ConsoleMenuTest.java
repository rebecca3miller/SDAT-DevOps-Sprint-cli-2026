package com.keyin.airportcli.ui;

import com.keyin.airportcli.client.AirportApiClient;
import com.keyin.airportcli.client.HttpResult;
import com.keyin.airportcli.client.HttpTransport;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.Scanner;

import static org.junit.jupiter.api.Assertions.*;

class ConsoleMenuTest {
    @Test
    void optionTwoPromptsForPassengerAndDisplaysAircraft() {
        FakeTransport transport = new FakeTransport(
                "[{\"id\":5,\"type\":\"Boeing 737\",\"airlineName\":\"Air Canada\"}]");
        AirportApiClient api = new AirportApiClient("http://localhost:8080", transport);
        String output = runMenu(api, "2\n12\n0\n");

        assertEquals("http://localhost:8080/passengers/12/aircraft", transport.lastUrl);
        assertTrue(output.contains("Boeing 737"));
        assertTrue(output.contains("Air Canada"));
    }

    @Test
    void optionThreePromptsForAircraftAndDisplaysAirports() {
        FakeTransport transport = new FakeTransport(
                "[{\"id\":3,\"name\":\"St. John's International Airport\",\"code\":\"YYT\"}]");
        AirportApiClient api = new AirportApiClient("http://localhost:8080", transport);
        String output = runMenu(api, "3\n8\n0\n");

        assertEquals("http://localhost:8080/aircraft/8/airports", transport.lastUrl);
        assertTrue(output.contains("YYT"));
    }

    @Test
    void optionFourDisplaysAirportsUsedByPassengers() {
        FakeTransport transport = new FakeTransport(
                "[{\"id\":3,\"name\":\"Toronto Pearson\",\"code\":\"YYZ\"}]");
        AirportApiClient api = new AirportApiClient("http://localhost:8080", transport);
        String output = runMenu(api, "4\n0\n");

        assertEquals("http://localhost:8080/passengers/airports", transport.lastUrl);
        assertTrue(output.contains("YYZ"));
    }

    @Test
    void optionOneClearlyReportsThatEndpointIsPending() {
        FakeTransport transport = new FakeTransport("[]");
        AirportApiClient api = new AirportApiClient("http://localhost:8080", transport);
        String output = runMenu(api, "1\n0\n");

        assertTrue(output.contains("not available yet"));
        assertNull(transport.lastUrl);
    }

    private String runMenu(AirportApiClient api, String input) {
        ByteArrayOutputStream output = new ByteArrayOutputStream();
        PrintStream original = System.out;
        try {
            System.setOut(new PrintStream(output));
            new ConsoleMenu(api, new Scanner(input)).run();
        } finally {
            System.setOut(original);
        }
        return output.toString();
    }

    static class FakeTransport implements HttpTransport {
        private final String body;
        String lastUrl;

        FakeTransport(String body) {
            this.body = body;
        }

        @Override
        public HttpResult get(String url) {
            lastUrl = url;
            return new HttpResult(200, body);
        }
    }
}
