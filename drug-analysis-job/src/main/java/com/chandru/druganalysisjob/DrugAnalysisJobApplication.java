package com.chandru.druganalysisjob;

import com.chandru.druganalysisjob.model.Patient;
import com.chandru.druganalysisjob.service.DrugAnalysisService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.List;

@SpringBootApplication
public class DrugAnalysisJobApplication implements CommandLineRunner {

    @Autowired
    private DrugAnalysisService drugAnalysisService;

    public static void main(String[] args) {
        SpringApplication.run(DrugAnalysisJobApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
        //1. read xmls
        final List<Patient> patients = drugAnalysisService.readXmls();

        //2. Generate report
        drugAnalysisService.generateReport(patients);
    }
}
