package de.neuefische.backend.service;

import de.neuefische.backend.model.CounselingCenter;
import de.neuefische.backend.repos.CounselingCenterRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.stream.Collectors;


@Service
public class BookmarkService {
    private final CounselingCenterRepo counselingCenterRepo;


    @Autowired
    public BookmarkService(CounselingCenterRepo counselingCenterRepo, MongoTemplate mongoTemplate) {
        this.counselingCenterRepo = counselingCenterRepo;

    }

    public List<CounselingCenter> listBookmarkedCounselingCenters(String username) {
        return counselingCenterRepo.findByBookmarkedBy(username);
    }

    public CounselingCenter addUserToBookmarkedBy(String counselingCenterId, String username) {
        CounselingCenter counselingCenterToUpdate = counselingCenterRepo.findById(counselingCenterId)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "CounselingCenter not found"));

        if(counselingCenterToUpdate.getBookmarkedBy().contains(username)){
            throw new ResponseStatusException(HttpStatus.CONFLICT, "Already bookmarked");
        }

        counselingCenterToUpdate.getBookmarkedBy().add(username);
        return counselingCenterRepo.save(counselingCenterToUpdate);
    }

    public CounselingCenter deleteUserFromBookmarkedBy(String counselingCenterId, String username) {
        CounselingCenter counselingCenterToUpdate = counselingCenterRepo.findById(counselingCenterId)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "CounselingCenter not found"));

        List<String> bookmarkedBy = counselingCenterToUpdate
                .getBookmarkedBy().stream()
                .filter((el)-> !el.equals(username))
                .collect(Collectors.toList());

        counselingCenterToUpdate.setBookmarkedBy(bookmarkedBy);
        return counselingCenterRepo.save(counselingCenterToUpdate);
    }
}
