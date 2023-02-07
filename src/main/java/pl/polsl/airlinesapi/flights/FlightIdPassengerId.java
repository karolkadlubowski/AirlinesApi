package pl.polsl.airlinesapi.flights;


import java.io.Serializable;

public class FlightIdPassengerId implements Serializable {

    public int flightId;
    public int passengerId;

    public FlightIdPassengerId(int flightId, int passengerId) {
        this.flightId = flightId;
        this.passengerId = passengerId;
    }

    public FlightIdPassengerId() {
    }
}
