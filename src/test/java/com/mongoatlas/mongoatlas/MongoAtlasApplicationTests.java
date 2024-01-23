package com.mongoatlas.mongoatlas;

import com.mongoatlas.mongoatlas.Controller.CyclicBarrierExample;
import com.mongoatlas.mongoatlas.Service.CandidateService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class MongoAtlasApplicationTests {

	@Autowired
	private final CandidateService candidateService;

	MongoAtlasApplicationTests(CandidateService candidateService) {
		this.candidateService = candidateService;
	}

	@Test
	void myTest() throws InterruptedException {
		CyclicBarrierExample cbe = new CyclicBarrierExample(candidateService);
		cbe.start();
	}


}
