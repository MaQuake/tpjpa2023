package fr.istic.tp3spring.rest;

import fr.istic.tp3spring.dao.AppointmentDAO;
import fr.istic.tp3spring.dao.PatientDAO;
import fr.istic.tp3spring.dao.ProfessionalDAO;
import fr.istic.tp3spring.domain.Appointment;
import fr.istic.tp3spring.domain.Patient;
import fr.istic.tp3spring.domain.Professional;
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
    private final ProfessionalDAO professionalDAO;
    private final PatientDAO patientDAO;

    public AppointmentController(AppointmentDAO appointmentService, ProfessionalDAO professionalDAO, PatientDAO patientDAO){
        this.appointmentDAO = appointmentService;
        this.professionalDAO = professionalDAO;
        this.patientDAO = patientDAO;
    }

    /**
     * Get all the appointment in the database
     * @return
     */
    @GetMapping("get-all-appointment")
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

    /**
     * Get all professional appointment for a specific professional ID
     * @param id
     * @return
     */
    @GetMapping("get-all-pro-appointment/{id}")
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

    /**
     * Get all professional appointment for a specific patient ID
     * @param id
     * @return
     */
    @GetMapping("get-all-patient-appointment/{id}")
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

    /**
     * Create an appointment
     * @param appointmentDTO
     * @return
     */
    @PostMapping("create")
    @ResponseBody
    public ResponseEntity<AppointmentDTO> createAppointment(@RequestBody AppointmentDTO appointmentDTO){
        try{
            Professional concernedPro = professionalDAO.getById(appointmentDTO.getProId());
            Patient concernedPatient = patientDAO.getById(appointmentDTO.getPatientId());
            Appointment app = MapStructMapper.INSTANCE.appointmentDTOToAppointment(appointmentDTO);

            app.setPro(concernedPro);
            app.setPatient(concernedPatient);

            concernedPro.getAppointmentList().add(app);
            concernedPatient.getAppointmentList().add(app);

            appointmentDAO.saveAndFlush(app);
            professionalDAO.saveAndFlush(concernedPro);
            patientDAO.saveAndFlush(concernedPatient);

            return new ResponseEntity<>(appointmentDTO, HttpStatus.CREATED);
        }catch(Exception ex){
            Logger.getGlobal().warning(ex.toString());
            return new ResponseEntity<>(appointmentDTO,HttpStatus.BAD_REQUEST);
        }
    }

    @DeleteMapping("delete/{id}")
    public ResponseEntity<String> deleteAppointment(@PathVariable("id") long id){
        try{
            if(appointmentDAO.getById(id) != null){
                appointmentDAO.deleteById(id);
                return new ResponseEntity<>("Appointment deleted", HttpStatus.ACCEPTED);
            }else{
                return new ResponseEntity<>("Professional does not exist", HttpStatus.NOT_FOUND);
            }
        }catch (Exception e){
            Logger.getGlobal().warning(e.toString());
            return new ResponseEntity<>("Error", HttpStatus.INTERNAL_SERVER_ERROR);
        }

    }

}
