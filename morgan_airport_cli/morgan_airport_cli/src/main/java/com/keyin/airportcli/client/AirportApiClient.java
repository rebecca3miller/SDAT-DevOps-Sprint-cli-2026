package com.keyin.airportcli.client;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;

public class AirportApiClient {
    private final String baseUrl;
    private final HttpTransport transport;
    private final ObjectMapper objectMapper;

    public AirportApiClient(String baseUrl, HttpTransport transport) {
        this(baseUrl, transport, new ObjectMapper());
    }

    public AirportApiClient(String baseUrl, HttpTransport transport,
                            ObjectMapper objectMapper) {
        this.baseUrl = removeTrailingSlashes(baseUrl);
        this.transport = transport;
        this.objectMapper = objectMapper;
    }

    // Question 1: What airports are in each city?
    public JsonNode getAirports() throws IOException, InterruptedException {
        return get("/airports");
    }

    // Question 2: What aircraft has a passenger flown on?
    public JsonNode getAircraftForPassenger(long passengerId)
            throws IOException, InterruptedException {
        validateId(passengerId, "Passenger");
        return get("/passengers/" + passengerId + "/aircraft");
    }

    // Question 3: Which airports does an aircraft operate from?
    public JsonNode getAirportsForAircraft(long aircraftId)
            throws IOException, InterruptedException {
        validateId(aircraftId, "Aircraft");
        return get("/aircraft/" + aircraftId + "/airports");
    }

    // Question 4: Which airports have passengers used overall?
    // The supplied controller defines /passengers/airports with no passenger ID.
    public JsonNode getAirportsUsedByPassengers()
            throws IOException, InterruptedException {
        return get("/passengers/airports");
    }

    private JsonNode get(String path) throws IOException, InterruptedException {
        HttpResult response = transport.get(baseUrl + path);

        if (response.statusCode() == 404) {
            throw new ApiException("The requested resource was not found (HTTP 404).");
        }
        if (response.statusCode() < 200 || response.statusCode() >= 300) {
            throw new ApiException("The API returned HTTP " + response.statusCode() + ".");
        }

        try {
            return objectMapper.readTree(response.body());
        } catch (Exception exception) {
            throw new ApiException("The API response was not valid JSON.");
        }
    }

    private void validateId(long id, String type) {
        if (id <= 0) {
            throw new IllegalArgumentException(type + " ID must be a positive number.");
        }
    }

    private String removeTrailingSlashes(String value) {
        if (value == null || value.isBlank()) {
            throw new IllegalArgumentException("API base URL cannot be blank.");
        }
        return value.replaceAll("/+$", "");
    }
}
