package com.mongoatlas.mongoatlas.Controller;

import com.mongoatlas.mongoatlas.Service.CandidateService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.*;

@RestController
public class CyclicBarrierController {
    @Autowired
    private CandidateService candidateService;

    @GetMapping("/concurrent-execution")
    public ResponseEntity<List<List<Map>>> concurrentExecution(@RequestParam String keyword) {

        CyclicBarrierExample cbe = new CyclicBarrierExample(this.candidateService);
        try {

            cbe.start();

            while (!cbe.isOpsComplete()) {
                Thread.sleep(100);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }


        System.out.println("======>>>> REturn Here");

        // Return the organized report
        return ResponseEntity.ok(cbe.getMongResponses());
    }

//    private ApiResult performApiCall(String keyword, CyclicBarrier cyclicBarrier) {
//        long startTime = System.currentTimeMillis();
//        try {
//            // Your Spring Boot method invocation goes here
//            // Pass the 'keyword' to your Spring Boot method
//            // Example: YourApiResult result = yourSpringBootService.yourMethod(keyword);
//
//            // Simulate API execution time
//            List<Map> result = candidateService.executeAggregationQuery(keyword);
//
//            Thread.sleep(1000);
//
//            cyclicBarrier.await(); // Wait for all threads to reach this point
//
//            long endTime = System.currentTimeMillis();
//            long executionTime = endTime - startTime;
//
//            return new ApiResult(result, executionTime);
//        } catch (InterruptedException | BrokenBarrierException e) {
//            e.printStackTrace();
//            return new ApiResult(null, -1); // Indicates failure
//        }
//    }
//
//    // Class to hold API result and execution time
//    private static class ApiResult {
//        private final List<Map> result;
//        private final long executionTime;
//
//        public ApiResult(List<Map> result, long executionTime) {
//            this.result = result;
//            this.executionTime = executionTime;
//        }
//
//        public List<Map> getResult() {
//            return result;
//        }
//
//        public long getExecutionTime() {
//            return executionTime;
//        }
//    }
//
//    // Class to represent the final API report
//    private static class ApiReport {
//        private final String keyword;
//        private final long executionTime;
//        private final List<Map> result;
//
//        public ApiReport(String keyword, long executionTime, List<Map> result) {
//            this.keyword = keyword;
//            this.executionTime = executionTime;
//            this.result = result;
//        }
//
//        public String getKeyword() {
//            return keyword;
//        }
//
//        public long getExecutionTime() {
//            return executionTime;
//        }
//
//        public List<Map> getResult() {
//            return result;
//        }
//    }result
}
