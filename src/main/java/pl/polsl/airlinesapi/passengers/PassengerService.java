package pl.polsl.airlinesapi.passengers;

import javax.ejb.Stateless;

@Stateless
public class PassengerService {
    public PassengerDao dao;

    public PassengerService() {
        dao = new PassengerDao();
        dao.setClazz(Passenger.class);
    }
}
