package pl.polsl.airlinesapi.flights;

import com.fasterxml.jackson.core.JsonProcessingException;
import pl.polsl.airlinesapi.EJB.MapperService;

import javax.ejb.EJB;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.Date;

@Path("/flights")
@Consumes({"application/json"})
@Produces({"application/json"})
public class FlightController {
    @EJB
    FlightService flightService;

    @EJB
    MapperService mapperService;

    @GET
    @Path("/getAll")
    public Response getFlights() throws JsonProcessingException {
        var flightList = flightService.dao.findAll();
        var flightListJson = mapperService.objectMapper.writeValueAsString(flightList);
        return Response.ok(flightListJson,
                MediaType.APPLICATION_JSON)/*)*/.build();
    }

    @GET
    @Path("/getById/{id}")
    public Response getFlightById(@PathParam("id") int id) throws JsonProcessingException {
        var flight = flightService.dao.findOne(id);
        var flightJson = mapperService.objectMapper.writeValueAsString(flight);

        if (flight != null)
            return Response.ok(flightJson, MediaType.APPLICATION_JSON).build();
        else
            return Response.status(Response.Status.BAD_REQUEST).entity("There is no plane with given id in the database").build();
    }

    @POST
    @Path("/post")
    public Response insertFlight(String json) throws Exception {
        var flightDTO = mapperService.objectMapper.readValue(json, FlightDTO.class);
        var flight = new Flight(flightDTO);
        if (flight.departureTime.before(new Date(System.currentTimeMillis())) || flight.arrivalTime.before(flight.departureTime)) {
            return Response.status(Response.Status.BAD_REQUEST).entity("Flight arrival time is not correct").build();
        }

        var insertedFlight = flightService.dao.update(flight);
        var insertedFlightJson = mapperService.objectMapper.writeValueAsString(insertedFlight);
        return Response.ok(insertedFlightJson, MediaType.APPLICATION_JSON)/*)*/.build();
    }

    @DELETE
    @Path("/delete")
    public Response deleteFlight(@QueryParam("flightId") int flightId) {
        flightService.dao.deleteById(flightId);
        return Response.ok("Flight with id " + (flightId) + " has been deleted from the database", MediaType.APPLICATION_JSON)/*)*/.build();

    }
}




