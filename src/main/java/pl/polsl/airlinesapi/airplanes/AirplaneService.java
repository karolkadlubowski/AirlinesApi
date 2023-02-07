package pl.polsl.airlinesapi.airplanes;

import pl.polsl.airlinesapi.dao.GenericJpaDao;
import pl.polsl.airlinesapi.dao.GenericDao;

import javax.ejb.Stateless;

@Stateless
public class AirplaneService {
    public GenericDao<Airplane> dao;

    public AirplaneService(){
        dao = new GenericJpaDao<>();
        dao.setClazz(Airplane.class);
    }
}
