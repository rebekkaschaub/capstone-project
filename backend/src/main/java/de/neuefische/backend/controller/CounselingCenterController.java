package de.neuefische.backend.controller;


import de.neuefische.backend.model.CounselingCenter;
import de.neuefische.backend.service.CounselingCenterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;


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
    return service.getCounselingCenterById(id).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "id does not exist"));
    }

    @GetMapping("/search")
    public List<CounselingCenter> searchCounselingCenterByQuery(@RequestParam MultiValueMap<String,String> params) {
        return service.filterCounselingCenter(params);
    }
}
