import java.time.LocalDateTime;
import java.util.Map;

class Flight {
    private final String flightId;
    private final String departureAirportCode;
    private final String arrivalAirportCode;
    private final LocalDateTime departureDateTime;
    private final LocalDateTime arrivalDateTime;
    private final String airline;
    private final Map<String, Integer> availableSeatsByClass;

    public Flight(String flightId, String departureAirportCode, String arrivalAirportCode, 
                  LocalDateTime departureDateTime, LocalDateTime arrivalDateTime, 
                  String airline, Map<String, Integer> availableSeatsByClass) {
        this.flightId = flightId;
        this.departureAirportCode = departureAirportCode;
        this.arrivalAirportCode = arrivalAirportCode;
        this.departureDateTime = departureDateTime;
        this.arrivalDateTime = arrivalDateTime;
        this.airline = airline;
        this.availableSeatsByClass = availableSeatsByClass;
    }

    public String getFlightId() {
        return flightId;
    }

    public String getDepartureAirportCode() {
        return departureAirportCode;
    }

    public String getArrivalAirportCode() {
        return arrivalAirportCode;
    }

    public LocalDateTime getDepartureDateTime() {
        return departureDateTime;
    }

    public LocalDateTime getArrivalDateTime() {
        return arrivalDateTime;
    }

    public String getAirline() {
        return airline;
    }

    public Map<String, Integer> getAvailableSeatsByClass() {
        return availableSeatsByClass;
    }
}
