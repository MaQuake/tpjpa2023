package fr.istic.tp3spring.rest;


import fr.istic.tp3spring.dao.ProfessionalDAO;
import fr.istic.tp3spring.domain.Patient;
import fr.istic.tp3spring.domain.Professional;
import fr.istic.tp3spring.dto.PatientDTO;
import fr.istic.tp3spring.dto.ProfessionalDTO;
import fr.istic.tp3spring.dto.mapper.MapStructMapper;
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
