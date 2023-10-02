package fr.istic.tp3spring.rest;

import fr.istic.tp3spring.domain.Patient;
import fr.istic.tp3spring.dao.PatientDAO;
import fr.istic.tp3spring.dto.PatientDTO;
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
@RequestMapping("patient")
public class PatientController {

    private final PatientDAO patientDAO;

    public PatientController(PatientDAO patientService){
        this.patientDAO = patientService;
    }

    /**
     * get a Patient by ID
     * @param id
     * @return
     */
    @Operation(summary = "Get a Patient By ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Found the book",
                    content = { @Content(mediaType = "application/json",
                            schema = @Schema(implementation = PatientDTO.class)) }),
            @ApiResponse(responseCode = "400", description = "Invalid id supplied",
                    content = @Content),
            @ApiResponse(responseCode = "404", description = "Patient not found",
                    content = @Content) })
    @GetMapping("get-by-id/{id}")
    @ResponseBody
    public PatientDTO getById(@PathVariable("id")Long id){
        PatientDTO pDTO = null;
        try{
            Patient p = this.patientDAO.getById(id);
            pDTO = MapStructMapper.INSTANCE.patientToPatientDTO(p);
        }catch (Exception ex) {
            Logger.getGlobal().warning(ex.toString());
        }
        return pDTO;
    }


    /**
     * get a Patient by mail
     * @param mail
     * @return
     */
    @GetMapping("get-by-email/{email}")
    @ResponseBody
    public PatientDTO getByEmail(@PathVariable("email") String mail) {
        PatientDTO pDTO = null;
        try {
            Patient p = this.patientDAO.getByMail(mail);
            pDTO = MapStructMapper.INSTANCE.patientToPatientDTO(p);
        }
        catch (Exception ex) {
            Logger.getGlobal().warning(ex.toString());
        }
        return pDTO;
    }

    /**
     * get all patient
     * @return
     */
    @GetMapping("get-all-patient")
    @ResponseBody
    public List<PatientDTO> getAllPatient(){
        List<PatientDTO> pDTO = new ArrayList<>();
        try {
            for(Patient p : this.patientDAO.findAll()){
                pDTO.add(MapStructMapper.INSTANCE.patientToPatientDTO(p));
            }
            return pDTO;
        } catch (Exception ex) {
            Logger.getGlobal().warning(ex.toString());
            return null;
        }
    }

    /**
     * Create a patient DTO convert it to Patient and save it
     * @param patientDTO
     * @return
     */
    @PostMapping("create")
    public ResponseEntity<PatientDTO> createPatient(@RequestBody PatientDTO patientDTO){
        try {
            if(patientDAO.getByMail(patientDTO.getMail()) == null){
                Patient patient = MapStructMapper.INSTANCE.patientDTOToPatient(patientDTO);
                patientDAO.saveAndFlush(patient);
                return new ResponseEntity<>(patientDTO,HttpStatus.CREATED);
            }else{
                return new ResponseEntity<>(patientDTO,HttpStatus.FOUND);
            }

        }catch (Exception e){
            Logger.getGlobal().warning(e.toString());
            return new ResponseEntity<>(patientDTO,HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    /**
     * Delete a patient by ID
     * @param id
     * @return
     */
    @DeleteMapping("/delete/{id}")
    public String deletePatientById(@PathVariable("id") long id){
        try{
            if(patientDAO.getById(id) != null){
                patientDAO.deleteById(id);
                return "Patient deleted";
            }else{
                return "Patient do not exist";
            }

        }catch(Exception e){
            Logger.getGlobal().warning(e.toString());
            return "Error with patient deletion";
        }
    }

    /**
     * Update a Patinet by his Id
     * @param id
     * @param patientDTO
     * @return
     */
    @PostMapping("update/{id}")
    public ResponseEntity<String> updatePatient(@PathVariable("id")long id,@RequestBody PatientDTO patientDTO){
        try{
            if(patientDTO == null){
                return new ResponseEntity<>("Patient deleted",HttpStatus.NO_CONTENT);
            }

            Patient patientToUpdate = MapStructMapper.INSTANCE.patientDTOToPatient(patientDTO);
            patientToUpdate.setId(id);
            patientDAO.save(patientToUpdate);

            return new ResponseEntity<>("Patient deleted",HttpStatus.OK);
        }catch(Exception e){
            return new ResponseEntity<>("Patient deleted",HttpStatus.INTERNAL_SERVER_ERROR);
        }

    }


}
