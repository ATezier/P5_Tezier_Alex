package com.safetynet.api.repository;

import com.safetynet.api.model.MedicalRecord;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@AutoConfigureMockMvc
public class MedicalRecordRepositoryTest {
    private static MedicalRecord medicalRecord;
    @Autowired
    MedicalRecordRepository medicalRecordRepository;
    @BeforeAll
    public static void setup() {
        medicalRecord = new MedicalRecord();
        String[] allergies = {"azertyuiop"};
        String[] medications = {"poiuytreza"};
        medicalRecord.setAllergies(allergies);
        medicalRecord.setMedications(medications);
        medicalRecord.setFirstName("Denis");
        medicalRecord.setLastName("Boyd");
        medicalRecord.setBirthdate("04/01/1789");
    }
    @Test
    public void getMedicalRecords() {
        assertTrue(!medicalRecordRepository.getMedicalRecords().isEmpty());
    }
    @Test
    public void createMedicalRecordTest() {
        assertTrue(medicalRecordRepository.createMedicalRecord(medicalRecord));
        medicalRecordRepository.deleteMedicalRecord(medicalRecord.getLastName(), medicalRecord.getFirstName());
    }
    @Test
    public void updateMedicalRecordTest() {
        medicalRecordRepository.createMedicalRecord(medicalRecord);
        medicalRecord.setBirthdate("10/12/1789");
        assertTrue(medicalRecordRepository.updateMedicalRecord(medicalRecord));
        medicalRecordRepository.deleteMedicalRecord(medicalRecord.getLastName(), medicalRecord.getFirstName());
    }
    @Test
    public void deleteMedicalRecordTest() {
        medicalRecordRepository.createMedicalRecord(medicalRecord);
        assertTrue(medicalRecordRepository.deleteMedicalRecord(medicalRecord.getFirstName(), medicalRecord.getLastName()));
    }
}
