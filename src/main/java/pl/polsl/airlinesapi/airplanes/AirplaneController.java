package pl.polsl.airlinesapi.airplanes;

import com.fasterxml.jackson.core.JsonProcessingException;
import pl.polsl.airlinesapi.EJB.MapperService;

import javax.ejb.EJB;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Path("/airplanes")
@Consumes({"application/json"})
@Produces({"application/json"})
public class AirplaneController {
    @EJB
    AirplaneService airplaneService;

    @EJB
    MapperService mapperService;

    @GET
    @Path("/getAll")
    public Response getAirplanes() throws JsonProcessingException {
        return Response.ok(mapperService.objectMapper.writeValueAsString(airplaneService.dao.findAll()),
                MediaType.APPLICATION_JSON).build();
    }
}
