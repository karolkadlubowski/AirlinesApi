package pl.polsl.airlinesapi.EJB;

import pl.polsl.airlinesapi.airplanes.Airplane;
import pl.polsl.airlinesapi.airplanes.AirplaneService;
import pl.polsl.airlinesapi.airports.Airport;
import pl.polsl.airlinesapi.airports.AirportService;

import javax.ejb.Stateless;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;
import java.util.Arrays;
import java.util.List;

@Stateless
public class DatabaseStartService {


    DatabaseStartService() {
        try {
            Connection connection =
                    DriverManager.getConnection("jdbc:mysql://localhost/?user=root");
            Statement stmt = connection.createStatement();
            stmt.executeUpdate("CREATE DATABASE IF NOT EXISTS " + "airlines");
            AirplaneService airplaneService = new AirplaneService();
            AirportService airportService = new AirportService();
            List<Airplane> airplanes = airplaneService.dao.findAll();
            if (airplanes.size() < 7) {
                airplaneList.forEach((x) -> {
                    airplaneService.dao.create(x);
                });
            }

            List<Airport> airports = airportService.dao.findAll();
            if (airports.size() < 7) {
                airportList.forEach((x) -> {
                    airportService.dao.create(x);
                });
            }

        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    List<Airplane> airplaneList = Arrays.asList(new Airplane("Gulfstream G700", 20), new Airplane("Boeing 737", 200), new Airplane("Boeing 747", 250), new Airplane("Boeing 757", 300), new Airplane("Boeing 767", 350), new Airplane("Boeing 777", 400), new Airplane("Airbus A380", 600));

    List<Airport> airportList = Arrays.asList(
            new Airport("Balice", "Cracow", "Poland"),
            new Airport("Chopin Airport", "Warsaw", "Poland"),
            new Airport("JFK", "Queens", "USA"),
            new Airport("Heathrow", "Longford", "England"),
            new Airport("Beijing Capital Airport", "Beijing", "China"),
            new Airport("El Prat", "Barcelona", "Spain"),
            new Airport("Willy Brandt", "Schönefeld", "Berlin")
    );
}
