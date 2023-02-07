package pl.polsl.airlinesapi.airports;

import javax.persistence.*;
import java.io.Serializable;

@Entity
public class Airport implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.TABLE)
    public int airportId;
    public String name;
    public String city;
    public String country;

    public Airport(){

    }

    public Airport(String name, String city, String country){
        this.name=name;
        this.city=city;
        this.country=country;
    }
}
