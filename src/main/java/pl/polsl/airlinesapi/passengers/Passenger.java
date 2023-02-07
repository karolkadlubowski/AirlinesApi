package pl.polsl.airlinesapi.passengers;

import javax.persistence.*;
import java.io.Serializable;

@Entity
public class Passenger implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.TABLE)
    public int passengerId;
    public String name;

    public Passenger(){}
    public Passenger(String name){
        this.name=name;
    }
}

