package com.keyin.airportcli.ui;

import java.io.IOException;
import java.util.Scanner;

import com.fasterxml.jackson.databind.JsonNode;
import com.keyin.airportcli.client.AirportApiClient;
import com.keyin.airportcli.client.ApiException;

public class ConsoleMenu {
    private final AirportApiClient api;
    private final Scanner scanner;

    public ConsoleMenu(AirportApiClient api) {
        this(api, new Scanner(System.in));
    }

    public ConsoleMenu(AirportApiClient api, Scanner scanner) {
        this.api = api;
        this.scanner = scanner;
    }

    public void run() {
        System.out.println("\n=== AIRPORT INFORMATION CLI ===");
        boolean running = true;

        while (running) {
            printMenu();
            String choice = scanner.nextLine().trim();

            try {
                switch (choice) {
                    case "1" -> {
                        display("Airports in each city", 
                            api.getAirports());
                    }
                    case "2" -> {
                        long id = readPositiveId("Passenger ID: ");
                        display("Aircraft flown on by passenger " + id,
                                api.getAircraftForPassenger(id));
                    }
                    case "3" -> {
                        long id = readPositiveId("Aircraft ID: ");
                        display("Airports used by aircraft " + id,
                                api.getAirportsForAircraft(id));
                    }
                    case "4" -> display("Airports used by passengers",
                            api.getAirportsUsedByPassengers());
                    case "0" -> {
                        running = false;
                        System.out.println("Goodbye!");
                    }
                    default -> System.out.println("Please choose 0, 1, 2, 3, or 4.");
                }
            } catch (IllegalArgumentException exception) {
                System.out.println("Input error: " + exception.getMessage());
            } catch (ApiException exception) {
                System.out.println("API error: " + exception.getMessage());
            } catch (InterruptedException exception) {
                Thread.currentThread().interrupt();
                System.out.println("The request was interrupted. Please try again.");
            } catch (IOException exception) {
                System.out.println("Could not connect to the API. Check that the server "
                        + "is running and the API URL is correct.");
            }
        }
    }

    private void printMenu() {
        System.out.println("\nChoose a question:");
        System.out.println("1. What airports are in each city?");
        System.out.println("2. What aircraft has each passenger flown on?");
        System.out.println("3. What airports does each aircraft take off from and land at?");
        System.out.println("4. What airports have passengers used?");
        System.out.println("0. Exit");
        System.out.print("> ");
    }

    private long readPositiveId(String prompt) {
        System.out.print(prompt);
        long id = Long.parseLong(scanner.nextLine().trim());
        if (id <= 0) {
            throw new IllegalArgumentException("ID must be greater than zero.");
        }
        return id;
    }

    private void display(String heading, JsonNode data) {
        System.out.println("\n--- " + heading + " ---");

        if (data == null || data.isNull()
                || (data.isArray() && data.isEmpty())) {
            System.out.println("No results found.");
            return;
        }

        if (!data.isArray()) {
            System.out.println(data.toPrettyString());
            return;
        }

        for (JsonNode item : data) {
            if (item.has("type")) {
                displayAircraft(item);
            } else if (item.has("code") || item.has("name")) {
                displayAirport(item);
            } else {
                System.out.println(item.toPrettyString());
            }
        }
    }

    private void displayAircraft(JsonNode item) {
        String type = text(item, "type", "Aircraft");
        String airline = text(item, "airlineName", "Unknown airline");
        System.out.println("- " + type + " — " + airline
                + " (ID: " + text(item, "id", "N/A") + ")");
        if (item.has("numberOfPassengers")) {
            System.out.println("  Passenger capacity: " + item.get("numberOfPassengers").asText());
        }
    }

    private void displayAirport(JsonNode item) {
        String name = text(item, "name", "Unnamed airport");
        String code = text(item, "code", "No airport code");
        System.out.println("- " + name + " (" + code + ")"
                + " (ID: " + text(item, "id", "N/A") + ")");
    }

    private String text(JsonNode item, String field, String fallback) {
        return item.hasNonNull(field) ? item.get(field).asText() : fallback;
    }
}
