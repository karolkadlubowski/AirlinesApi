package pl.polsl.airlinesapi.airplanes;

import javax.persistence.*;
import java.io.Serializable;

@Entity
public class Airplane implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.TABLE)
    public int airplaneId;
    public String name;
    public int capacity;

    public Airplane(int airplaneId, String name, int capacity) {
        this.airplaneId=airplaneId;
        this.name=name;
        this.capacity=capacity;
    }

    public Airplane( String name, int capacity) {
        this.name=name;
        this.capacity=capacity;
    }

    public Airplane() {
    }
}
