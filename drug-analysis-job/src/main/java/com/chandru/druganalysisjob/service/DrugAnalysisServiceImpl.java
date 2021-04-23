package com.chandru.druganalysisjob.service;

import com.chandru.druganalysisjob.model.Patient;
import org.apache.commons.io.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.List;

@Service
public class DrugAnalysisServiceImpl implements DrugAnalysisService {

    @Value("${xml.path}")
    private String xmlPath;

    @Value("${pdf.report}")
    private String pdfPath;

    @Autowired
    private GeneratePDFReport generatePDFReport;

    @Override
    public List<Patient> readXmls() throws JAXBException {
        ClassLoader classLoader = getClass().getClassLoader();
        File file = new File(classLoader.getResource(xmlPath).getFile());
        List<Patient> patients = new ArrayList<>();

        if (file.isDirectory()) {
            for (File xml : file.listFiles()) {
                JAXBContext jaxbContext = JAXBContext.newInstance(Patient.class);
                Unmarshaller jaxbUnmarshaller = jaxbContext.createUnmarshaller();
                Patient patient = (Patient) jaxbUnmarshaller.unmarshal(xml);
                patients.add(patient);
            }
        }
        return patients;
    }


    public boolean generateReport(List<Patient> patients) throws IOException {
        InputStream byteArrayInputStream = generatePDFReport.citiesReport(patients);
        File f = new File(pdfPath);
        OutputStream out = new FileOutputStream(f);
        IOUtils.copy(byteArrayInputStream, out);
        out.close();
        return true;
    }
}
