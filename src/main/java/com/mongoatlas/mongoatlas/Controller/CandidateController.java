package com.mongoatlas.mongoatlas.Controller;

import com.mongoatlas.mongoatlas.Service.CandidateService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/candidates")
public class CandidateController
{
    @Autowired
    private CandidateService candidateService;

    @GetMapping("/search")
    public ResponseEntity<List<Map>> searchCandidates(@RequestParam String regexPattern) {
        List<Map> result = candidateService.executeAggregationQuery(regexPattern);
        return new ResponseEntity<>(result, HttpStatus.OK);
    }

}
