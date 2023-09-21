package fr.istic.tp3spring.rest;

import fr.istic.tp3spring.dao.AppointmentDAO;
import fr.istic.tp3spring.domain.Appointment;
import fr.istic.tp3spring.dto.AppointmentDTO;
import fr.istic.tp3spring.dto.mapper.MapStructMapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

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
            Logger.getGlobal().warning(ex.toString());
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
            Logger.getGlobal().warning(ex.toString());
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
            Logger.getGlobal().warning(ex.toString());
            return null;
        }
    }

    @PostMapping("create")
    public ResponseEntity<AppointmentDTO> createAppointment(@RequestBody AppointmentDTO appointmentDTO){
        try{
            Appointment app = MapStructMapper.INSTANCE.appointmentDTOToAppointment(appointmentDTO);
            appointmentDAO.saveAndFlush(app);
            return new ResponseEntity<>(appointmentDTO, HttpStatus.CREATED);
        }catch(Exception ex){
            Logger.getGlobal().warning(ex.toString());
            return new ResponseEntity<>(appointmentDTO,HttpStatus.BAD_REQUEST);
        }
    }

}
