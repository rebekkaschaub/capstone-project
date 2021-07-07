package de.neuefische.backend.service;

import de.neuefische.backend.model.CounselingCenter;
import de.neuefische.backend.repos.CounselingCenterRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;



@Service
public class BookmarkService {
    private final CounselingCenterRepo counselingCenterRepo;


    @Autowired
    public BookmarkService(CounselingCenterRepo counselingCenterRepo) {
        this.counselingCenterRepo = counselingCenterRepo;

    }

    public List<CounselingCenter> listBookmarkedCounselingCenters(String username) {
        return counselingCenterRepo.findByBookmarkedBy(username);
    }


    public CounselingCenter updateBookMarkedBy(String counselingCenterId, String username) {
        CounselingCenter counselingCenterToUpdate = counselingCenterRepo.findById(counselingCenterId)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "CounselingCenter not found"));

        if(counselingCenterToUpdate.getBookmarkedBy().contains(username)){
            counselingCenterToUpdate.getBookmarkedBy().remove(username);
        }else{
            counselingCenterToUpdate.getBookmarkedBy().add(username);
        }

        return counselingCenterRepo.save(counselingCenterToUpdate);
    }


}
