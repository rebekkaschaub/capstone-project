package de.neuefische.backend.controller;

import de.neuefische.backend.dto.CounselingCenterDto;
import de.neuefische.backend.model.CounselingCenter;
import de.neuefische.backend.service.BookmarkService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.List;

@RestController
@RequestMapping("/api/bookmark")
public class BookmarkController {

    private final BookmarkService service;

    @Autowired
    public BookmarkController(BookmarkService service) {
        this.service = service;
    }

    @GetMapping
    public List<CounselingCenter> listBookmarkedCounselingCenters(Principal principal){
        return service.listBookmarkedCounselingCenters(principal.getName());
    }
    
    @PostMapping
    public CounselingCenter addUserToBookmarkedBy(@RequestBody CounselingCenterDto counselingCenterDto, Principal principal){
        return service.addUserToBookmarkedBy(counselingCenterDto.getId(), principal.getName());
    }
    
    @DeleteMapping
    public CounselingCenter deleteUserFromBookmarkedBy(@RequestBody CounselingCenterDto counselingCenterDto, Principal principal){
        return service.deleteUserFromBookmarkedBy(counselingCenterDto.getId(), principal.getName());
    }
}
