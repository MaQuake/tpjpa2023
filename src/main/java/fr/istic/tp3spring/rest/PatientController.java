package fr.istic.tp3spring.rest;

import fr.istic.tp3spring.domain.Patient;
import fr.istic.tp3spring.dao.PatientDAO;
import fr.istic.tp3spring.dto.PatientDTO;
import fr.istic.tp3spring.dto.mapper.MapStructMapper;
import fr.istic.tp3spring.dto.mapper.MapStructMapperImpl;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("patient")
public class PatientController {

    private final PatientDAO patientDAO;
    private final MapStructMapper mapper = new MapStructMapperImpl();

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
            System.out.println(ex);
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
            System.out.println(ex);
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
            System.out.println(ex);
            return null;
        }
    }

    @PostMapping("create")
    public Patient createPatient(@RequestBody Patient patient){
        patientDAO.save(patient);
        return patient;
    }

    @DeleteMapping("/delete/{id}")
    public String deletePatientById(@PathVariable("id") long id){
        try{
            patientDAO.deleteById(id);
            return "Patient deleted";
        }catch(Exception e){
            return "Error with patient deletion";
        }

    }
}
