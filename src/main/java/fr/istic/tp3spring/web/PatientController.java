package fr.istic.tp3spring.web;

import fr.istic.tp3spring.domain.Patient;
import fr.istic.tp3spring.service.PatientDAO;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class PatientController {

    private final PatientDAO patientDAO;

    public PatientController(PatientDAO patientService){
        this.patientDAO = patientService;
    }
    @RequestMapping("/get-by-email/{email}")
    @ResponseBody
    public String getByEmail(@PathVariable("email") String email) {
        String userId = "";
        try {
            Patient p = this.patientDAO.findByMail(email);
            userId = String.valueOf(p.getId());
        }
        catch (Exception ex) {
            return "User not found";
        }
        return "The user id is: " + userId;
    }
}
