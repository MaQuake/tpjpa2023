package fr.istic.tp3spring.rest;

import fr.istic.tp3spring.domain.Patient;
import fr.istic.tp3spring.dao.PatientDAO;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
public class PatientController {

    private final PatientDAO patientDAO;

    public PatientController(PatientDAO patientService){
        this.patientDAO = patientService;
    }


    @RequestMapping("/get-by-email/{email}")
    @ResponseBody
    public String getByEmail(@PathVariable("email") String mail) {
        String userId = "";
        try {
            Patient p = this.patientDAO.getByMail(mail);
            userId = String.valueOf(p.getId());
        }
        catch (Exception ex) {
            return "User not found";
        }
        return "The user id is: " + userId;
    }

    @RequestMapping("/get-all-patient")
    @ResponseBody
    public List<Patient> getAllPatient(){
        try {
            return (List<Patient>) this.patientDAO.findAll();
        }
        catch (Exception ex) {
            return null;
        }
    }
}
