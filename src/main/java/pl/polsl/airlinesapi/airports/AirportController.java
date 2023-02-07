package pl.polsl.airlinesapi.airports;

import com.fasterxml.jackson.core.JsonProcessingException;
import pl.polsl.airlinesapi.EJB.MapperService;

import javax.ejb.EJB;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Path("/airports")
@Consumes({"application/json"})
@Produces({"application/json"})
public class AirportController {
    @EJB
    AirportService airportService;

    @EJB
    MapperService mapperService;

    @GET
    @Path("/getAll")
    public Response getAirports() throws JsonProcessingException {
        return Response.ok(mapperService.objectMapper.writeValueAsString(airportService.dao.findAll()),
                MediaType.APPLICATION_JSON).build();
    }
}
