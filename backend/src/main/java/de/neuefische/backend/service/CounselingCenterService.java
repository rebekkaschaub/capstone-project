package de.neuefische.backend.service;

import de.neuefische.backend.model.CounselingCenter;
import de.neuefische.backend.model.CounselingSetting;
import de.neuefische.backend.model.Specialization;
import de.neuefische.backend.model.TargetGroup;
import de.neuefische.backend.repos.CounselingCenterRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.util.MultiValueMap;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

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

    public List<CounselingCenter> filterCounselingCenter(MultiValueMap<String,String> params){
        Query query = new Query();
        try{
            addParamsToQuery(params, query);
        }catch (IllegalArgumentException i){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
        }
            return mongoTemplate.find(query, CounselingCenter.class);
    }

    public void addParamsToQuery(MultiValueMap<String, String> params, Query query) {
        if(params.containsKey("city")){
        query.addCriteria(Criteria.where("address.city").is(params.get("city").get(0)));
        }
        if(params.containsKey("postalCode")){
            query.addCriteria(Criteria.where("address.postalCode").is(params.get("postalCode").get(0)));
        }
        if(params.containsKey("specialization")){
            List<Specialization> specialization = params.get("specialization").stream().map(Specialization::valueOf).collect(Collectors.toList());
            if(!specialization.contains(Specialization.ALL)){
                query.addCriteria(Criteria.where("specializations").in(specialization));}
        }
        if(params.containsKey("targetGroup")){
            List<TargetGroup> targetGroups = params.get("targetGroup").stream().map(TargetGroup::valueOf).collect(Collectors.toList());
            query.addCriteria(Criteria.where("targetGroup").in(targetGroups));
        }

        if(params.containsKey("counselingSetting")){
            List<CounselingSetting> counselingSettings = params.get("counselingSetting").stream().map(CounselingSetting::valueOf).collect(Collectors.toList());
            query.addCriteria(Criteria.where("counselingSetting").in(counselingSettings));
        }

    }
}
