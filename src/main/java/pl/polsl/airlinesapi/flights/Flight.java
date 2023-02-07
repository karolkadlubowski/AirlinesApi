package pl.polsl.airlinesapi.flights;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import pl.polsl.airlinesapi.airplanes.Airplane;
import pl.polsl.airlinesapi.airports.Airport;
import pl.polsl.airlinesapi.passengers.Passenger;

import javax.persistence.*;
import java.io.Serializable;
import java.sql.Date;
import java.util.List;

@Entity
public class Flight implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.TABLE)
    public int flightId;
    public Date departureTime;
    public Date arrivalTime;

    @ManyToOne(cascade = CascadeType.REFRESH, fetch = FetchType.EAGER)
    @JoinColumn(name = "airplaneId")
    @JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
    public Airplane airplane;

    @ManyToOne(cascade = CascadeType.REFRESH, fetch = FetchType.EAGER)
    @JoinColumn(name = "departureAirportId")
    @JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
    public Airport departureAirport;

    @JoinColumn(name = "arrivalAirportId")
    @JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
    @ManyToOne(cascade = CascadeType.REFRESH, fetch = FetchType.EAGER)
    public Airport arrivalAirport;

    @ManyToMany(cascade = CascadeType.MERGE, fetch = FetchType.LAZY)
    @JoinTable(
            name = "Flight_Passenger",
            joinColumns = {@JoinColumn(name = "flightId")},
            inverseJoinColumns = {@JoinColumn(name = "passengerId")}
    )
    @JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
    public List<Passenger> passengers;

    public void addPassenger(Passenger passenger) {
        this.passengers.add(passenger);
    }

    public void removePassenger(Passenger passenger) {
        this.passengers.removeIf(p -> p.passengerId == passenger.passengerId);
    }

    public Flight(FlightDTO flightDTO) {
        this.flightId = flightDTO.flightId;
        this.departureTime = flightDTO.departureTime;
        this.arrivalTime = flightDTO.arrivalTime;
        this.airplane = new Airplane();
        this.departureAirport = new Airport();
        this.arrivalAirport = new Airport();
        this.passengers = flightDTO.passengers;
        airplane.airplaneId = flightDTO.airplaneId;
        departureAirport.airportId = flightDTO.departureAirportId;
        arrivalAirport.airportId = flightDTO.arrivalAirportId;
    }

    public Flight() {
    }
}

