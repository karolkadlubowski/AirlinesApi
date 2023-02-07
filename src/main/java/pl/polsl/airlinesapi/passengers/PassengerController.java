package pl.polsl.airlinesapi.passengers;

import com.fasterxml.jackson.core.JsonProcessingException;
import pl.polsl.airlinesapi.EJB.MapperService;
import pl.polsl.airlinesapi.flights.Flight;
import pl.polsl.airlinesapi.flights.FlightService;

import javax.ejb.EJB;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.io.IOException;

@Path("/passengers")
@Consumes({"application/json"})
@Produces({"application/json"})
public class PassengerController {
    @EJB
    PassengerService passengerService;

    @EJB
    FlightService flightService;

    @EJB
    MapperService mapperService;

    @GET
    @Path("/getAll")
    public Response getPassengers() throws JsonProcessingException {
        return /*responseService.GetCorsResponse(*/Response.ok(mapperService.objectMapper.writeValueAsString(passengerService.dao.findAll()),
                MediaType.APPLICATION_JSON).build();
    }

    @DELETE
    @Path("/delete")
    public Response deletePassengerFromFlight(@QueryParam("passengerId") int passengerId, @QueryParam("flightId") int flightId) throws JsonProcessingException {
        if (passengerId == 0 || flightId == 0) {
            return Response.status(Response.Status.BAD_REQUEST).entity("Empty id").build();
        }

        var passengerFromDatabase = passengerService.dao.findOne(passengerId);

        var flightFromDatabase = flightService.dao.findOne(flightId);

        if (passengerFromDatabase != null && flightFromDatabase != null) {
            flightFromDatabase.removePassenger(passengerFromDatabase);

            flightService.dao.update(flightFromDatabase);

            return Response.ok(mapperService.objectMapper
                            .writeValueAsString("Passenger with id " + (passengerId) + " deleted successfully from flight " + (flightId)),
                    MediaType.APPLICATION_JSON).build();
        } else {
            return Response.status(Response.Status.BAD_REQUEST).entity("Faulty data").build();
        }


    }

    @POST
    @Path("/post")
    public Response insertPassenger(String json) throws IOException {
        var passengerDTO = mapperService.objectMapper.readValue(json, PassengerDTO.class);
        if (passengerDTO.name.isEmpty()) {
            return Response.status(Response.Status.BAD_REQUEST).entity("Wrong name").build();
        }
        var passengerFromDatabase = passengerService.dao.FindPassengerByName(passengerDTO.name);
        var passenger = new Passenger(passengerDTO.name);
        if (passengerFromDatabase != null) {
            passenger.passengerId = passengerFromDatabase.passengerId;
        }

        var flight = flightService.dao.findOne(passengerDTO.flightId);
        if (flight != null) {
            if (flight.airplane.capacity <= flight.passengers.size()) {
                return Response.status(Response.Status.BAD_REQUEST).entity("Unfortunately all of the places have been already booked, try to choose another flight").build();
            }
            if (passengerFromDatabase != null && flight.passengers.stream().anyMatch(p -> p.passengerId == passengerFromDatabase.passengerId)) {
                return Response.status(Response.Status.BAD_REQUEST).entity("This passenger has already booked this flight").build();
            }
            flight.addPassenger(passenger);
            Flight updatedFlight = flightService.dao.update(flight);

            int updatedPassengerId = updatedFlight.passengers.stream().filter(p -> p.name==passengerDTO.name).findFirst().get().passengerId;
            passengerDTO.passengerId=updatedPassengerId;
        } else {
            return Response.status(Response.Status.BAD_REQUEST).entity("Wrong flightId").build();
        }

        return Response.ok(mapperService.objectMapper.writeValueAsString(passengerDTO),
                MediaType.APPLICATION_JSON).build();
    }
}
