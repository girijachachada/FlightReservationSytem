import java.util.List;

class Reservation {
    private final String reservationId;
    private final Flight flight;
    private final List<Passenger> passengers;
    private final PaymentInfo paymentInfo;

    public Reservation(String reservationId, Flight flight, List<Passenger> passengers, PaymentInfo paymentInfo) {
        this.reservationId = reservationId;
        this.flight = flight;
        this.passengers = passengers;
        this.paymentInfo = paymentInfo;
    }

    public String getReservationId() {
        return reservationId;
    }

    public Flight getFlight() {
        return flight;
    }

    public List<Passenger> getPassengers() {
        return passengers;
    }

    public PaymentInfo getPaymentInfo() {
        return paymentInfo;
    }
}
