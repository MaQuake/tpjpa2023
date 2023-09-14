package rest;

import dao.ProfessionalDAO;
import domain.Patient;
import domain.Professional;
import io.swagger.v3.oas.annotations.Parameter;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

import java.util.List;

@Path("professional")
@Produces({"application/json", "application/xml"})
public class ProfessionalResource {

    ProfessionalDAO pa = new ProfessionalDAO();
    @GET
    @Path("/{professionalId}")
    public Professional getPatientById(@PathParam("professionalId") Long professionalId)  {
        // return professional
        return (Professional) pa.findOne(professionalId);
    }

    @GET
    @Path("/")
    public List<Professional> getProfessional()  {
        return pa.findAll();
    }

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    public Response addProfessional(
            @Parameter(description = "Appointment to generate", required = true) Professional professional) {
        // add professional
        try{
            pa.save(professional);
        }catch (Exception e){
            return Response.serverError().build();
        }
        return Response.ok().entity("SUCCESS").build();
    }
}
