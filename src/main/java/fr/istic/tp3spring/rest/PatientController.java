package fr.istic.tp3spring.rest;

import fr.istic.tp3spring.domain.Patient;
import fr.istic.tp3spring.dao.PatientDAO;
import fr.istic.tp3spring.dto.PatientDTO;
import fr.istic.tp3spring.dto.mapper.MapStructMapper;
import fr.istic.tp3spring.dto.mapper.MapStructMapperImpl;
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

    @RequestMapping("get-by-id/{id}")
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


    @RequestMapping("get-by-email/{email}")
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

    @RequestMapping("get-all-patient")
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
}
