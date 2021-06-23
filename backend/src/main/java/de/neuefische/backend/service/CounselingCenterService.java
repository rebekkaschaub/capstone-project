package de.neuefische.backend.service;

import de.neuefische.backend.dto.CounselingCenterQueryDto;
import de.neuefische.backend.model.CounselingCenter;
import de.neuefische.backend.model.Specialization;
import de.neuefische.backend.repos.CounselingCenterRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CounselingCenterService {

    private final CounselingCenterRepo repo;
    private final MongoTemplate mongoTemplate;

    @Autowired
    public CounselingCenterService(CounselingCenterRepo repo, MongoTemplate mongoTemplate) {
        this.repo = repo;
        this.mongoTemplate = mongoTemplate;
    }

    public List<CounselingCenter> listAllCounselingCenters() {
        return repo.findAll();
    }

    public Optional<CounselingCenter> getCounselingCenterById(String id) {
        return repo.findById(id);
    }

    public List<CounselingCenter> filterCounselingCenter(CounselingCenterQueryDto queryDto) {
        Query query = new Query();

        if(queryDto.getCity() != null){
            query.addCriteria(Criteria.where("address.city").is(queryDto.getCity()));
        }
       if(queryDto.getPostalCode() != null){
            query.addCriteria(Criteria.where("address.postalCode").is(queryDto.getPostalCode()));
        }
        if(queryDto.getSpecialization() != null && queryDto.getSpecialization() != Specialization.ALL){
            query.addCriteria(Criteria.where("specializations").is(queryDto.getSpecialization()));
        }
        if(!queryDto.getTargetGroup().isEmpty() && queryDto.getTargetGroup() != null){
            query.addCriteria(Criteria.where("targetGroup").in(queryDto.getTargetGroup()));
        }
        if(!queryDto.getCounselingSetting().isEmpty() && queryDto.getCounselingSetting() != null){
            query.addCriteria(Criteria.where("counselingSetting").in(queryDto.getCounselingSetting()));
        }


        return mongoTemplate.find(query, CounselingCenter.class);

        }


}
