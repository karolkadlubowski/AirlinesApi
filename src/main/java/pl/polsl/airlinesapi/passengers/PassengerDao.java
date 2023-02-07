package pl.polsl.airlinesapi.passengers;

import pl.polsl.airlinesapi.dao.AbstractJpaDao;

import javax.persistence.Query;
import java.util.List;


public class PassengerDao extends AbstractJpaDao<Passenger> {

    public Passenger FindPassengerByName(final String name){
        Query query = entityManager.createQuery("SELECT p from Passenger p WHERE p.name = :passengerName");
        query.setParameter("passengerName",name);
        List<Passenger> resultList = query.getResultList();
        if(resultList.isEmpty())
            return null;
        else
            return resultList.get(0);
    }

}
