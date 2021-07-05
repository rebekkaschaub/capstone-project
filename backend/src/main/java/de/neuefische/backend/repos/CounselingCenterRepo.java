package de.neuefische.backend.repos;


import de.neuefische.backend.model.CounselingCenter;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CounselingCenterRepo extends PagingAndSortingRepository<CounselingCenter, String> {

    List<CounselingCenter> findAll();

    List<CounselingCenter> findByBookmarkedBy(String username);

}
