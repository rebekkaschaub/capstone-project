package de.neuefische.backend.controller;

import de.neuefische.backend.model.CounselingCenter;
import de.neuefische.backend.service.CounselingCenterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
}
