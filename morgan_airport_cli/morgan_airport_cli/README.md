# Morgan's Airport CLI

A standalone Java command-line client for the three API questions whose endpoints have been confirmed.

## Implemented questions

| Menu option | Assignment question | HTTP request |
|---|---|---|
| 2 | What aircraft has each passenger flown on? | `GET /passengers/{id}/aircraft` |
| 3 | What airports does each aircraft take off from and land at? | `GET /aircraft/{id}/airports` |
| 4 | What airports have passengers used? | `GET /passengers/airports` |

Option 1 is intentionally isolated and not implemented yet because Rebecca's city-to-airports endpoint has not been confirmed. When it is agreed, add one method to `AirportApiClient`, one menu branch, and matching tests.

## Requirements

- Java 21 or newer
- Maven 3.9 or newer

## Run

Start the team's Spring Boot API separately, then run this project:

```bash
mvn clean test
mvn compile exec:java
```

The default API base URL is `http://localhost:8080`.

To use a different URL:

**PowerShell**
```powershell
$env:AIRPORT_API_URL="http://localhost:8080"
mvn compile exec:java
```

**macOS/Linux**
```bash
export AIRPORT_API_URL="http://localhost:8080"
mvn compile exec:java
```

## How the project is tested independently

The tests do not start Spring Boot, connect to a database, or require teammates' code. `AirportApiClient` depends on a small `HttpTransport` interface. Production uses `JavaHttpTransport`, which delegates to Java's built-in `HttpClient`; tests use a handwritten fake transport that returns controlled HTTP status codes and JSON bodies.

Run tests with:

```bash
mvn clean test
```

## API assumptions confirmed from the supplied controllers

- Passenger aircraft: `GET /passengers/{id}/aircraft`
- Aircraft airports: `GET /aircraft/{id}/airports`
- Airports used by passengers: `GET /passengers/airports`

The third endpoint is global: the supplied controller has no passenger ID in its route. Therefore, the CLI presents it as the collection of airports used by passengers overall, not the airports used by one selected passenger.

The JSON responses are read as Jackson `JsonNode` values. This avoids coupling the CLI to entity package names or requiring the CLI to import the server's JPA entities.

## Response display

The CLI recognizes common fields from the supplied entities:
- Aircraft: `id`, `type`, `airlineName`, `numberOfPassengers`
- Airport: `id`, `name`, `code`
- Optional nested relationship fields are displayed when present.

Unknown fields are not required for the CLI to work.
