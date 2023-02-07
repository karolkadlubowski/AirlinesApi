package pl.polsl.airlinesapi.airports;

import pl.polsl.airlinesapi.dao.GenericJpaDao;
import pl.polsl.airlinesapi.dao.GenericDao;

import javax.ejb.Stateless;

@Stateless
public class AirportService {
    public GenericDao<Airport> dao;

    public AirportService() {
        dao = new GenericJpaDao<>();
        dao.setClazz(Airport.class);
    }
}

