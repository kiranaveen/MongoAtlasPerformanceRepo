package com.mongoatlas.mongoatlas.Controller;


import com.mongoatlas.mongoatlas.Service.CandidateService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.concurrent.CyclicBarrier;


public class CyclicBarrierExample {
    long st=0;
    private volatile boolean opsComplete = false;

    private List<List<Map>> mongResponses = Collections.synchronizedList(new ArrayList<>());
    List<Worker> workerList = new ArrayList<>();

    CyclicBarrier cb= new CyclicBarrier(10,()->{
        this.workerList.forEach(worker -> this.mongResponses.add(worker.getResp()));
        long et = System.currentTimeMillis();
        System.out.println("Total time taken=="+ (et-this.st) + "ms");
        this.opsComplete = true;
    });


    private  CandidateService candidateService;

    public CyclicBarrierExample(CandidateService candidateService) {
        this.candidateService = candidateService;
    }

    public void start() throws InterruptedException {
        this.st = System.currentTimeMillis();
        System.out.println("------------------Running Workers------------------");
        String[] tokens = {"ja","a","Engi", "Mum", "No","c","e","tes","Ban","Che","a","a","a","a","a","a","a","a","a","a","a","a"};
        for(int ii=1;ii<=22;ii++) {

            Worker w = new Worker(this.cb,tokens[ii-1], this.candidateService);
            this.workerList.add(w);
            w.start();
        }

        Thread.sleep(1000);
    }

    public boolean isOpsComplete() {
        return this.opsComplete;
    }

    public List<List<Map>> getMongResponses(){
        return this.mongResponses;
    }

}
