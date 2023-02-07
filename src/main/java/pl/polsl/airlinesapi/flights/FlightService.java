package pl.polsl.airlinesapi.flights;

import pl.polsl.airlinesapi.dao.GenericJpaDao;
import pl.polsl.airlinesapi.dao.GenericDao;

import javax.ejb.Stateless;

@Stateless
public class FlightService {
    public GenericDao<Flight> dao;

    public FlightService(){
        dao = new GenericJpaDao<Flight>();
        dao.setClazz(Flight.class);
    }
}
