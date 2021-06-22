package de.neuefische.backend.repos;


import de.neuefische.backend.model.CounselingCenter;
import de.neuefische.backend.model.CounselingSetting;
import de.neuefische.backend.model.TargetGroup;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CounselingCenterRepo extends PagingAndSortingRepository<CounselingCenter, String> {

    List<CounselingCenter> findAll();

    @Query("{ 'address.city': ?0, 'address.postalCode': ?1, 'specializations': ?2, 'targetGroup': {$in: ?3}, 'counselingSetting':{$in: ?4}}")
    List<CounselingCenter> filterByUserQuery(String city, String postalCode, String specialization, List<TargetGroup> targetGroups, List<CounselingSetting> settings);

}
