package com.safetynet.api.repository;

import com.safetynet.api.model.MedicalRecord;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.BDDMockito.given;

@SpringBootTest
@AutoConfigureMockMvc
public class MedicalRecordRepositoryTest {
    private static List<MedicalRecord> medicalRecords;
    private static MedicalRecord medicalRecord;
    @MockBean
    DataPersistent dataPersistent;
    @Autowired
    MedicalRecordRepository medicalRecordRepository;
    @BeforeAll
    public static void setup() {
        medicalRecords = new ArrayList<>();
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
        given(dataPersistent.getMedicalRecords()).willReturn(medicalRecords);
        assertNotNull(medicalRecordRepository.getMedicalRecords());
    }
    @Test
    public void createMedicalRecordTest() {
        given(dataPersistent.getMedicalRecords()).willReturn(medicalRecords);
        assertTrue(medicalRecordRepository.createMedicalRecord(medicalRecord));
        medicalRecordRepository.deleteMedicalRecord(medicalRecord.getLastName(), medicalRecord.getFirstName());
    }
    @Test
    public void updateMedicalRecordTest() {
        given(dataPersistent.getMedicalRecords()).willReturn(medicalRecords);
        MedicalRecord m = new MedicalRecord();
        medicalRecordRepository.createMedicalRecord(medicalRecord);
        m.setFirstName(medicalRecord.getFirstName());
        m.setLastName(medicalRecord.getLastName());
        m.setAllergies(medicalRecord.getAllergies());
        m.setMedications(medicalRecord.getMedications());
        m.setBirthdate("10/12/1789");
        assertTrue(medicalRecordRepository.updateMedicalRecord(m));
        medicalRecordRepository.deleteMedicalRecord(medicalRecord.getLastName(), medicalRecord.getFirstName());
    }
    @Test
    public void deleteMedicalRecordTest() {
        given(dataPersistent.getMedicalRecords()).willReturn(medicalRecords);
        medicalRecordRepository.createMedicalRecord(medicalRecord);
        assertTrue(medicalRecordRepository.deleteMedicalRecord(medicalRecord.getFirstName(), medicalRecord.getLastName()));
    }
}
