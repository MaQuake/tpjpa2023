package rest;

import dao.AppointmentDAO;
import domain.Appointment;
import io.swagger.v3.oas.annotations.Parameter;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

import java.util.List;

@Path("appointment")
@Produces({"application/json", "application/xml"})
public class AppointmentResource {

    AppointmentDAO ap = new AppointmentDAO();
    @GET
    @Path("/{appointmentId}")
    public Appointment getAppointmentById(@PathParam("appointmentId") Long appointmentId)  {
        return (Appointment) ap.findOne(appointmentId);
    }

    @GET
    @Path("/")
    public List<Appointment> getAppointment()  {
        return ap.findAll();
    }


    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    public Response addAppointment(
            @Parameter(description = "Appointment to generate", required = true) Appointment appointment) {
        // add appointment
        try{
            ap.save(appointment);
        }catch (Exception e){
            return Response.serverError().build();
        }
        return Response.ok().entity("SUCCESS").build();
    }


}
