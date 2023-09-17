package fr.istic.tp3spring.web;

import fr.istic.tp3spring.domain.Patient;
import fr.istic.tp3spring.service.PatientDAO;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class PatientController {


    @RequestMapping("/get-by-email/{email}")
    @ResponseBody
    public String getByEmail(@PathVariable("email") String email) {
        String userId = "";
        try {
            PatientDAO p = new PatientDAO();
            Patient user = (Patient) p.findOne(1);
            userId = String.valueOf(user.getId());
        }
        catch (Exception ex) {
            return "User not found";
        }
        return "The user id is: " + userId;
    }
}
