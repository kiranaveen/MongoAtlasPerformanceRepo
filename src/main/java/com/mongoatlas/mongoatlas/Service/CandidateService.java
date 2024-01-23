package com.mongoatlas.mongoatlas.Service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.stereotype.Service;
import org.springframework.data.mongodb.core.aggregation.Aggregation;
import org.springframework.data.mongodb.core.aggregation.AggregationResults;

import java.util.List;
import java.util.Map;

@Service
public class CandidateService
{
    @Autowired
    private MongoTemplate mongoTemplate;

    public List<Map> executeAggregationQuery(String regexPattern) {
        Criteria regexCriteria = new Criteria().orOperator(
                Criteria.where("name").regex(regexPattern, "i"),
                Criteria.where("about").regex(regexPattern, "i"),
                Criteria.where("work_summary").regex(regexPattern, "i"),
                Criteria.where("work_details").regex(regexPattern, "i"),
                Criteria.where("unique_id").regex(regexPattern, "i"),
                Criteria.where("status").regex(regexPattern, "i"),
                Criteria.where("profile_viewed_status").regex(regexPattern, "i"),
                Criteria.where("previous_company").regex(regexPattern, "i"),
                Criteria.where("preferred_locations").regex(regexPattern, "i"),
                Criteria.where("phone_number").regex(regexPattern, "i"),
                Criteria.where("ctc_in_lacs").regex(regexPattern, "i"),
                Criteria.where("current_company").regex(regexPattern, "i"),
                Criteria.where("current_designation").regex(regexPattern, "i"),
                Criteria.where("e_mail").regex(regexPattern, "i"),
                Criteria.where("key_skills").regex(regexPattern, "i"),
                Criteria.where("other_skills").regex(regexPattern, "i"),
                Criteria.where("education_details").regex(regexPattern, "i"),
                Criteria.where("personal_details").regex(regexPattern, "i")
        );

        Aggregation aggregation = Aggregation.newAggregation(
                Aggregation.match(regexCriteria),
                Aggregation.group().addToSet("preferred_locations").as("all_preferred_locations")
                        .addToSet("current_designation").as("all_current_designation")
                        .addToSet("current_location").as("all_current_location")
                        .addToSet("education_details").as("all_education")
                        .push("$$ROOT").as("docs"),
                Aggregation.project()
                        .andExclude("_id")
                        .and("all_preferred_locations").as("all_preferred_locations")
                        .and("all_current_designation").as("all_current_designation")
                        .and("all_current_location").as("all_current_location")
                        .and("all_education").as("all_education")
                        .and("docs").slice(10)
        );

        AggregationResults<Map> results = mongoTemplate.aggregate(aggregation, "Xpheno_candidate_master", Map.class);
        String resp = String.valueOf(results);
        System.out.println("Size of resp for " + regexPattern + "=" + resp.length());
        return results.getMappedResults();
    }

    public void test(){
        System.out.println("Mongo Service Tested...");
    }
}
