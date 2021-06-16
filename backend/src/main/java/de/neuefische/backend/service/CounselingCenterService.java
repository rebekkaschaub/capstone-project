package de.neuefische.backend.service;

import de.neuefische.backend.model.CounselingCenter;
import de.neuefische.backend.repos.CounselingCenterRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CounselingCenterService {

    private final CounselingCenterRepo repo;

    @Autowired
    public CounselingCenterService(CounselingCenterRepo repo) {
        this.repo = repo;
    }

    public List<CounselingCenter> listAllCounselingCenters() {
        return repo.findAll();
    }

    public Optional<CounselingCenter> getCounselingCenterById(String id) {
        return repo.findById(id);
    }
}
