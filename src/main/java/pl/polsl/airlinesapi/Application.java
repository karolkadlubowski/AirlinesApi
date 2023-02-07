package pl.polsl.airlinesapi;


import pl.polsl.airlinesapi.EJB.DatabaseStartService;

import javax.ejb.EJB;
import javax.ws.rs.ApplicationPath;


@ApplicationPath("/api")
public class Application extends javax.ws.rs.core.Application {
    @EJB
    DatabaseStartService databaseStartService;
}