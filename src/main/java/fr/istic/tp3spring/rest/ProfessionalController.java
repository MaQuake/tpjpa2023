package fr.istic.tp3spring.rest;


import fr.istic.tp3spring.dao.ProfessionalDAO;
import fr.istic.tp3spring.domain.Patient;
import fr.istic.tp3spring.domain.Professional;
import fr.istic.tp3spring.dto.PatientDTO;
import fr.istic.tp3spring.dto.ProfessionalDTO;
import fr.istic.tp3spring.dto.mapper.MapStructMapper;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

@RestController
@RequestMapping("professional")
public class ProfessionalController {

    private final ProfessionalDAO proDAO;


    public ProfessionalController(ProfessionalDAO proDAO){
        this.proDAO = proDAO;
    }

    /**
     * get a professionnal by Id
     * @param id
     * @return
     */
    @Operation(summary = "Get a Professional By ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Found the Professional",
                    content = { @Content(mediaType = "application/json",
                            schema = @Schema(implementation = ProfessionalDTO.class)) }),
            @ApiResponse(responseCode = "400", description = "Invalid id supplied",
                    content = @Content),
            @ApiResponse(responseCode = "404", description = "Professional not found",
                    content = @Content) })
    @GetMapping("get-by-id/{id}")
    @ResponseBody
    public ResponseEntity<ProfessionalDTO> getById(@PathVariable("id")Long id){
        ProfessionalDTO pDTO = null;
        try{
            Professional p = this.proDAO.getById(id);
            pDTO = MapStructMapper.INSTANCE.professionalToProfessionalDTO(p);
        }catch (Exception ex) {
            System.out.println(ex);
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(pDTO,HttpStatus.FOUND);
    }


    /**
     * get a professionnal by email
     * @param mail
     * @return
     */
    @Operation(summary = "Get a Professional By mail")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Found the Professional",
                    content = { @Content(mediaType = "application/json",
                            schema = @Schema(implementation = ProfessionalDTO.class)) }),
            @ApiResponse(responseCode = "400", description = "Invalid id supplied",
                    content = @Content),
            @ApiResponse(responseCode = "404", description = "Professional not found",
                    content = @Content) })
    @GetMapping("get-by-email/{email}")
    @ResponseBody
    public ResponseEntity<ProfessionalDTO> getByEmail(@PathVariable("email") String mail) {
        ProfessionalDTO pDTO = null;
        try {
            Professional p = this.proDAO.getByMail(mail);
            pDTO = MapStructMapper.INSTANCE.professionalToProfessionalDTO(p);
        }
        catch (Exception ex) {
            System.out.println(ex);
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(pDTO,HttpStatus.FOUND);
    }

    /**
     * get all the professional
     * @return
     */
    @Operation(summary = "Get all Professional")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Found Professionals",
                    content = { @Content(mediaType = "application/json",
                            schema = @Schema(implementation = ProfessionalDTO.class)) }),
            @ApiResponse(responseCode = "404", description = "Professional not found",
                    content = @Content) })
    @GetMapping("get-all-professional")
    @ResponseBody
    public ResponseEntity<List<ProfessionalDTO>> getAllProfessional(){
        List<ProfessionalDTO> pDTO = new ArrayList<>();
        try {
            for(Professional p : this.proDAO.findAll()){
                pDTO.add(MapStructMapper.INSTANCE.professionalToProfessionalDTO(p));
            }
            return new ResponseEntity<>(pDTO,HttpStatus.FOUND);
        } catch (Exception ex) {
            System.out.println(ex);
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    /**
     * Create a professional
     * @param proDTO
     * @return
     */
    @Operation(summary = "Create a professional")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Professional created",
                    content = { @Content(mediaType = "application/json",
                            schema = @Schema(implementation = ProfessionalDTO.class)) }),
            @ApiResponse(responseCode = "500", description = "Internal error",
                    content = @Content) })
    @PostMapping("create")
    public ResponseEntity<ProfessionalDTO> createProfessional(@RequestBody ProfessionalDTO proDTO){
        try{
            Professional pro = MapStructMapper.INSTANCE.professionalDTOToProfessional(proDTO);
            proDAO.saveAndFlush(pro);
        }catch (Exception ex){
            Logger.getGlobal().warning(ex.toString());
            return new ResponseEntity<>(proDTO,HttpStatus.INTERNAL_SERVER_ERROR);
        }

        return new ResponseEntity<>(proDTO,HttpStatus.CREATED);
    }

    /**
     * Delete a professional
     * @param id
     * @return
     */
    @Operation(summary = "Delete a professional")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "202", description = "Professional deleted",
                    content = { @Content(mediaType = "application/json",
                            schema = @Schema(implementation = String.class)) }),
            @ApiResponse(responseCode = "404", description = "Professional not found",
                    content = @Content),
            @ApiResponse(responseCode = "500", description = "Internal error",
                    content = @Content),
    })
    @DeleteMapping("delete/{id}")
    public ResponseEntity<String> deleteProfessional(@PathVariable("id") long id){
        try{
            if(proDAO.getById(id) != null){
                proDAO.deleteById(id);
                return new ResponseEntity<>("Patiend deleted", HttpStatus.ACCEPTED);
            }else{
                return new ResponseEntity<>("Professional does not exist", HttpStatus.NOT_FOUND);
            }
        }catch (Exception e){
            Logger.getGlobal().warning(e.toString());
            return new ResponseEntity<>("Error", HttpStatus.INTERNAL_SERVER_ERROR);
        }

    }

    /**
     * Update an existing Patient
     * @param id
     * @param proDTO
     * @return
     */
    @Operation(summary = "Update a professional")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Professional updated",
                    content = { @Content(mediaType = "application/json",
                            schema = @Schema(implementation = String.class)) }),
            @ApiResponse(responseCode = "204", description = "Professional do not exist or is deleted",
                    content = @Content),
            @ApiResponse(responseCode = "500", description = "Internal error",
                    content = @Content),
    })
    @PostMapping("update/{id}")
    public ResponseEntity<String> updateProfessional(@PathVariable("id")long id,@RequestBody ProfessionalDTO proDTO){
        try{
            if(proDTO == null){
                return new ResponseEntity<>("Patient deleted",HttpStatus.NO_CONTENT);
            }
            Professional proToUpdate = MapStructMapper.INSTANCE.professionalDTOToProfessional(proDTO);
            proToUpdate.setId(id);
            proDAO.save(proToUpdate);

            return new ResponseEntity<>("Professional deleted",HttpStatus.OK);
        }catch(Exception e){
            return new ResponseEntity<>("Professional deleted",HttpStatus.INTERNAL_SERVER_ERROR);
        }

    }
}
