package fr.istic.tp3spring.rest;


import fr.istic.tp3spring.dao.ProfessionalDAO;
import fr.istic.tp3spring.domain.Professional;
import fr.istic.tp3spring.dto.ProfessionalDTO;
import fr.istic.tp3spring.dto.mapper.MapStructMapper;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("professional")
public class ProfessionalController {

    private final ProfessionalDAO proDAO;


    public ProfessionalController(ProfessionalDAO proDAO){
        this.proDAO = proDAO;
    }

    @RequestMapping("get-by-id/{id}")
    @ResponseBody
    public ProfessionalDTO getById(@PathVariable("id")Long id){
        ProfessionalDTO pDTO = null;
        try{
            Professional p = this.proDAO.getById(id);
            pDTO = MapStructMapper.INSTANCE.professionalToProfessionalDTO(p);
        }catch (Exception ex) {
            System.out.println(ex);
        }
        return pDTO;
    }


    @RequestMapping("get-by-email/{email}")
    @ResponseBody
    public ProfessionalDTO getByEmail(@PathVariable("email") String mail) {
        ProfessionalDTO pDTO = null;
        try {
            Professional p = this.proDAO.getByMail(mail);
            pDTO = MapStructMapper.INSTANCE.professionalToProfessionalDTO(p);
        }
        catch (Exception ex) {
            System.out.println(ex);
        }
        return pDTO;
    }

    @RequestMapping("get-all-professional")
    @ResponseBody
    public List<ProfessionalDTO> getAllProfessional(){
        List<ProfessionalDTO> pDTO = new ArrayList<>();
        try {
            for(Professional p : this.proDAO.findAll()){
                pDTO.add(MapStructMapper.INSTANCE.professionalToProfessionalDTO(p));
            }
            return pDTO;
        } catch (Exception ex) {
            System.out.println(ex);
            return null;
        }
    }

    @PostMapping("create")
    public Professional createProfessional(@RequestBody Professional pro){
        proDAO.save(pro);
        return pro;
    }

    @DeleteMapping("delete/{id}")
    public String deleteProfessional(@PathVariable("id") long id){
        proDAO.deleteById(id);
        return "proffessional deleted";
    }


}
