package com.chandru.druganalysisjob.service;

import com.chandru.druganalysisjob.model.Patient;

import javax.xml.bind.JAXBException;
import java.io.IOException;
import java.util.List;

public interface DrugAnalysisService {
    List<Patient> readXmls() throws JAXBException;
    boolean generateReport(List<Patient> patients) throws IOException;
}
