package de.neuefische.backend.controller;

import de.neuefische.backend.model.CounselingCenter;
import de.neuefische.backend.service.CounselingCenterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("api/counseling")
public class CounselingCenterController {

    private final CounselingCenterService service;

    @Autowired
    public CounselingCenterController(CounselingCenterService service) {
        this.service = service;
    }

    @GetMapping
    public List<CounselingCenter> listAllCounselingCenters(){

        return service.listAllCounselingCenters();

    }

    @GetMapping("/{id}")
    public CounselingCenter getCounselingCenterById(@PathVariable String id){

        Optional<CounselingCenter> optionalCounselingCenter = service.getCounselingCenterById(id);

        if(optionalCounselingCenter.isEmpty()){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "id does not exist");
        }

        return optionalCounselingCenter.get();

    }
}
