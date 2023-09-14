package rest;


import dao.PatientDAO;
import domain.Appointment;
import domain.Patient;
import io.swagger.v3.oas.annotations.Parameter;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

import java.util.List;
@Path("patient")
@Produces({"application/json", "application/xml"})
public class PatientResource {
    PatientDAO pa = new PatientDAO();
    @GET
    @Path("/{patientId}")
    ///@Produces(MediaType.APPLICATION_JSON)
    public Patient getPatientById(@PathParam("patientId") Long patientId)  {
        // return patient
        return (Patient) pa.findOne(patientId);
    }

    @GET
    @Path("/")
    //@Produces(MediaType.APPLICATION_JSON)
    public List<Patient> getPatient()  {
        return (List<Patient>) pa.findAll();
    }


    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    public Response addPatient(
            @Parameter(description = "Appointment to generate", required = true) Patient patient) {
        // add appointment
        try{
            pa.save(patient);
        }catch(Exception e){
            return Response.serverError().build();
        }
        return Response.ok().entity("SUCCESS").build();
    }
}
