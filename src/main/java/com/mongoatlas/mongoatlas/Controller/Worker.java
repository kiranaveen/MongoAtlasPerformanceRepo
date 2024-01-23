package com.mongoatlas.mongoatlas.Controller;

import com.mongoatlas.mongoatlas.Service.CandidateService;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.CyclicBarrier;

public class Worker extends Thread {
    private final CyclicBarrier cyclicBarrier;
    private String keyword;
    private CandidateService candidateService;
    private List<Map> respList = new ArrayList<>();

    Worker(CyclicBarrier cyclicBarrier, String threadNum, CandidateService candidateService) {
        this.cyclicBarrier = cyclicBarrier;
        this.keyword = threadNum;
        this.candidateService = candidateService;
    }

    @Override
    public void run() {
        try {
            long st = System.currentTimeMillis();
            System.out.println("Running Thread:: "+this.keyword + "-" + this.candidateService);
            // Wait for other threads to reach the barrier

           this.respList = this.candidateService.executeAggregationQuery(this.keyword);

            long et = System.currentTimeMillis();
            System.out.println("Time taken for " + this.keyword + " to Execute = " + (et-st) + " ms");
            cyclicBarrier.await();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public List<Map> getResp(){
        return this.respList;
    }

}
