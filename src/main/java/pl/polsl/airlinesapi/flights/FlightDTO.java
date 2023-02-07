package pl.polsl.airlinesapi.flights;

import pl.polsl.airlinesapi.airplanes.Airplane;
import pl.polsl.airlinesapi.airports.Airport;
import pl.polsl.airlinesapi.passengers.Passenger;

import java.sql.Date;
import java.util.List;

public class FlightDTO {
    public int flightId;
    public Date departureTime;
    public Date arrivalTime;
    public int airplaneId;
    public int departureAirportId;
    public int arrivalAirportId;
    public List<Passenger> passengers;

//    public FlightDTO(int flightId, Date departureTime, Date arrivalTime, int airplane, int departureAirportId, int arrivalAirportId, List<Passenger> passengers) {
//        this.flightId = flightId;
//        this.departureTime = departureTime;
//        this.arrivalTime = arrivalTime;
//        this.airplaneId = airplane;
//        this.departureAirportId = departureAirportId;
//        this.arrivalAirportId = arrivalAirportId;
//        this.passengers = passengers;
//    }
//
//    public FlightDTO() {
//    }
}
