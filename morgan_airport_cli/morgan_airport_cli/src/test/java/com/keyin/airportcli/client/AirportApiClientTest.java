package com.keyin.airportcli.client;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class AirportApiClientTest {
    private FakeTransport transport;
    private AirportApiClient api;

    @BeforeEach
    void setUp() {
        transport = new FakeTransport();
        api = new AirportApiClient("http://localhost:8080/", transport);
    }

    @Test
    void questionTwoRequestsAircraftForPassengerAndParsesJson() throws Exception {
        transport.result = new HttpResult(200,
                "[{\"id\":5,\"type\":\"Boeing 737\",\"airlineName\":\"Air Canada\",\"numberOfPassengers\":189}]");

        JsonNode result = api.getAircraftForPassenger(12);

        assertEquals("http://localhost:8080/passengers/12/aircraft", transport.lastUrl);
        assertEquals("Boeing 737", result.get(0).get("type").asText());
        assertEquals("Air Canada", result.get(0).get("airlineName").asText());
    }

    @Test
    void questionThreeRequestsAirportsForAircraftAndParsesJson() throws Exception {
        transport.result = new HttpResult(200,
                "[{\"id\":3,\"name\":\"St. John's International Airport\",\"code\":\"YYT\"}]");

        JsonNode result = api.getAirportsForAircraft(8);

        assertEquals("http://localhost:8080/aircraft/8/airports", transport.lastUrl);
        assertEquals("YYT", result.get(0).get("code").asText());
    }

    @Test
    void questionFourRequestsGlobalPassengerAirportList() throws Exception {
        transport.result = new HttpResult(200,
                "[{\"id\":3,\"name\":\"St. John's International Airport\",\"code\":\"YYT\"}]");

        JsonNode result = api.getAirportsUsedByPassengers();

        assertEquals("http://localhost:8080/passengers/airports", transport.lastUrl);
        assertEquals(1, result.size());
    }

    @Test
    void emptyArrayIsAValidNoResultsResponse() throws Exception {
        transport.result = new HttpResult(200, "[]");

        assertTrue(api.getAirportsUsedByPassengers().isEmpty());
    }

    @Test
    void http404BecomesUsefulApiException() {
        transport.result = new HttpResult(404, "{\"error\":\"not found\"}");

        ApiException exception = assertThrows(ApiException.class,
                () -> api.getAirportsForAircraft(999));

        assertTrue(exception.getMessage().contains("404"));
    }

    @Test
    void otherNonSuccessStatusBecomesApiException() {
        transport.result = new HttpResult(500, "server error");

        ApiException exception = assertThrows(ApiException.class,
                () -> api.getAirportsUsedByPassengers());

        assertTrue(exception.getMessage().contains("500"));
    }

    @Test
    void invalidJsonBecomesApiException() {
        transport.result = new HttpResult(200, "this is not json");

        assertThrows(ApiException.class, () -> api.getAirportsUsedByPassengers());
    }

    @Test
    void rejectsZeroOrNegativePassengerIdBeforeMakingRequest() {
        assertThrows(IllegalArgumentException.class,
                () -> api.getAircraftForPassenger(0));
        assertNull(transport.lastUrl);
    }

    @Test
    void rejectsNegativeAircraftIdBeforeMakingRequest() {
        assertThrows(IllegalArgumentException.class,
                () -> api.getAirportsForAircraft(-2));
        assertNull(transport.lastUrl);
    }

    static class FakeTransport implements HttpTransport {
        HttpResult result = new HttpResult(200, "[]");
        String lastUrl;
        List<String> requestedUrls = new ArrayList<>();

        @Override
        public HttpResult get(String url) throws IOException {
            lastUrl = url;
            requestedUrls.add(url);
            return result;
        }
    }
}
