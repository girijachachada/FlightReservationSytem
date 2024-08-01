import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

public class FlightReservationSystem {
    private List<Flight> flights = new ArrayList<>();
    private List<Reservation> reservations = new ArrayList<>();

    public FlightReservationSystem() {
        // Initialize with some mock data
        initializeMockData();
    }

    private void initializeMockData() {
        // Adding some mock flights
        flights.add(new Flight("FL123", "JFK", "LAX",
            LocalDateTime.of(2024, 8, 1, 10, 0),
            LocalDateTime.of(2024, 8, 1, 13, 0),
            "Delta Airlines",
            new HashMap<>(Map.of("Economy", 100, "Business", 10))));

        flights.add(new Flight("FL456", "LAX", "SFO",
            LocalDateTime.of(2024, 8, 2, 15, 0),
            LocalDateTime.of(2024, 8, 2, 16, 30),
            "United Airlines",
            new HashMap<>(Map.of("Economy", 80, "Business", 20))));
    }

    public List<Flight> searchFlights(String origin, String destination, String departureDate,
                                      String returnDate, int passengers, String travelClass) {
        List<Flight> availableFlights = new ArrayList<>();
        for (Flight flight : flights) {
            if (flight.getDepartureAirportCode().equalsIgnoreCase(origin)
                && flight.getArrivalAirportCode().equalsIgnoreCase(destination)
                && flight.getDepartureDateTime().toLocalDate().toString().equals(departureDate)
                && flight.getAvailableSeatsByClass().getOrDefault(travelClass, 0) >= passengers) {
                availableFlights.add(flight);
            }
        }
        return availableFlights;
    }

    public Reservation makeReservation(String flightId, List<Passenger> passengers, PaymentInfo paymentInfo) {
        for (Flight flight : flights) {
            if (flight.getFlightId().equals(flightId)) {
                String reservationId = "RES" + (reservations.size() + 1);
                Reservation reservation = new Reservation(reservationId, flight, passengers, paymentInfo);
                reservations.add(reservation);

                Map<String, Integer> seats = flight.getAvailableSeatsByClass();
                int currentSeats = seats.get("Economy") - passengers.size();
                seats.put("Economy", currentSeats);

                return reservation;
            }
        }
        return null;
    }

    public static void main(String[] args) {
        FlightReservationSystem system = new FlightReservationSystem();
        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.println("\n--- Flight Reservation System ---");
            System.out.println("1. Search for Flights");
            System.out.println("2. Make a Reservation");
            System.out.println("3. Exit");
            System.out.print("Choose an option: ");
            int choice = scanner.nextInt();
            scanner.nextLine();  // Consume newline

            switch (choice) {
                case 1:
                    // Search for flights
                    System.out.print("Enter origin: ");
                    String origin = scanner.nextLine();
                    System.out.print("Enter destination: ");
                    String destination = scanner.nextLine();
                    System.out.print("Enter departure date (YYYY-MM-DD): ");
                    String departureDate = scanner.nextLine();
                    System.out.print("Enter number of passengers: ");
                    int passengers = scanner.nextInt();
                    scanner.nextLine();  // Consume newline
                    System.out.print("Enter travel class (Economy/Business): ");
                    String travelClass = scanner.nextLine();

                    List<Flight> availableFlights = system.searchFlights(origin, destination, departureDate, null, passengers, travelClass);
                    if (availableFlights.isEmpty()) {
                        System.out.println("No flights found matching your criteria.");
                    } else {
                        System.out.println("Available flights:");
                        for (int i = 0; i < availableFlights.size(); i++) {
                            Flight flight = availableFlights.get(i);
                            System.out.println((i + 1) + ". Flight ID: " + flight.getFlightId() + ", From: " +
                                flight.getDepartureAirportCode() + ", To: " + flight.getArrivalAirportCode() +
                                ", Departure: " + flight.getDepartureDateTime() + ", Arrival: " +
                                flight.getArrivalDateTime() + ", Airline: " + flight.getAirline());
                        }
                    }
                    break;

                case 2:
                    // Make a reservation
                    System.out.print("Enter the flight ID: ");
                    String flightId = scanner.nextLine();
                    List<Passenger> passengerList = new ArrayList<>();

                    System.out.print("Enter the number of passengers: ");
                    int numPassengers = scanner.nextInt();
                    scanner.nextLine();  // Consume newline

                    for (int i = 0; i < numPassengers; i++) {
                        System.out.print("Enter name of passenger " + (i + 1) + ": ");
                        String passengerName = scanner.nextLine();
                        passengerList.add(new Passenger(passengerName));
                    }

                    System.out.print("Enter payment information (credit card number): ");
                    String creditCardNumber = scanner.nextLine();
                    PaymentInfo paymentInfo = new PaymentInfo(creditCardNumber);

                    Reservation reservation = system.makeReservation(flightId, passengerList, paymentInfo);
                    if (reservation != null) {
                        System.out.println("Reservation successful! Reservation ID: " + reservation.getReservationId());
                    } else {
                        System.out.println("Reservation failed. Please check the flight ID and try again.");
                    }
                    break;

                case 3:
                    System.out.println("Exiting...");
                    scanner.close();
                    System.exit(0);
                    break;

                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        }
    }
}
