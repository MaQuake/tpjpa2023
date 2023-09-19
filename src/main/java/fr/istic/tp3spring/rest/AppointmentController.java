package fr.istic.tp3spring.rest;

import fr.istic.tp3spring.dao.AppointmentDAO;
import fr.istic.tp3spring.dao.PatientDAO;
import fr.istic.tp3spring.domain.Appointment;
import fr.istic.tp3spring.domain.Patient;
import fr.istic.tp3spring.dto.AppointmentDTO;
import fr.istic.tp3spring.dto.PatientDTO;
import fr.istic.tp3spring.dto.mapper.MapStructMapper;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("appointment")
public class AppointmentController {

    private final AppointmentDAO appointmentDAO;

    public AppointmentController(AppointmentDAO appointmentService){
        this.appointmentDAO = appointmentService;
    }

    @RequestMapping("get-all-appointment")
    @ResponseBody
    public List<AppointmentDTO> getAllAppointment(){
        List<AppointmentDTO> pDTO = new ArrayList<>();
        try {
            for(Appointment a : this.appointmentDAO.findAll()){
                pDTO.add(MapStructMapper.INSTANCE.appointmentToAppointmentDTO(a));
            }
            return pDTO;
        } catch (Exception ex) {
            System.out.println(ex);
            return null;
        }
    }

    @RequestMapping("get-all-pro-appointment/{id}")
    @ResponseBody
    public List<AppointmentDTO> getAllProAppointment(@PathVariable("id") long id){
        List<AppointmentDTO> pDTO = new ArrayList<>();
        try {
            for(Appointment a : this.appointmentDAO.getByProId(id)){
                pDTO.add(MapStructMapper.INSTANCE.appointmentToAppointmentDTO(a));
            }
            return pDTO;
        } catch (Exception ex) {
            System.out.println(ex);
            return null;
        }
    }

    @RequestMapping("get-all-patient-appointment/{id}")
    @ResponseBody
    public List<AppointmentDTO> getAllPatientAppointment(@PathVariable("id") long id){
        List<AppointmentDTO> pDTO = new ArrayList<>();
        try {
            for(Appointment a : this.appointmentDAO.getByPatientId(id)){
                pDTO.add(MapStructMapper.INSTANCE.appointmentToAppointmentDTO(a));
            }
            return pDTO;
        } catch (Exception ex) {
            System.out.println(ex);
            return null;
        }
    }

}
