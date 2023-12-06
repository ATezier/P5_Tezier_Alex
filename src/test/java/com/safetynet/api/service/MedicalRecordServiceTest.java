package com.safetynet.api.service;

import com.safetynet.api.model.MedicalRecord;
import com.safetynet.api.repository.MedicalRecordRepository;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.BDDMockito.given;

@SpringBootTest
@AutoConfigureMockMvc
public class MedicalRecordServiceTest {
    private static MedicalRecord medicalRecord;

    @MockBean
    MedicalRecordRepository medicalRecordRepository;
    @Autowired
    MedicalRecordServiceImpl medicalRecordService;

    @BeforeAll
    public static void setup() {
        LocalDate date = LocalDate.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MM/dd/yyyy");
        String birthdate = date.minusYears(10).format(formatter);
        String[] allergies = {"azertyuiop"};
        String[] medications = {"azertyuiop"};
        medicalRecord = new MedicalRecord();
        medicalRecord.setFirstName("Sophie");
        medicalRecord.setLastName("Dupont");
        medicalRecord.setBirthdate(birthdate);
        medicalRecord.setMedications(medications);
        medicalRecord.setAllergies(allergies);
    }

    @Test
    public void getMedicalRecordsTest() {
        List<MedicalRecord> medicalRecords = new ArrayList<>();
        medicalRecords.add(medicalRecord);
        given(medicalRecordRepository.getMedicalRecords()).willReturn(medicalRecords);
        assertFalse(medicalRecordService.getMedicalRecords().isEmpty());
    }

    @Test
    public void getMedicalRecordTest() {
        given(medicalRecordRepository.getMedicalRecord(anyString(), anyString())).willReturn(medicalRecord);
        assertNotNull(medicalRecordService.getMedicalRecord(medicalRecord.getFirstName(), medicalRecord.getLastName()));
    }

    @Test
    public void createMedicalRecordTest() {
        given(medicalRecordRepository.createMedicalRecord(medicalRecord)).willReturn(true);
        assertTrue(medicalRecordService.createMedicalRecord(medicalRecord));
    }

    @Test
    public void updateMedicalRecordTest() {
        given(medicalRecordRepository.updateMedicalRecord(medicalRecord)).willReturn(true);
        assertTrue(medicalRecordService.updateMedicalRecord(medicalRecord));
    }
    @Test
    public void deleteMedicalRecordTest() {
        given(medicalRecordRepository.deleteMedicalRecord(medicalRecord.getFirstName(), medicalRecord.getLastName())).willReturn(true);
        assertTrue(medicalRecordService.deleteMedicalRecord(medicalRecord.getFirstName(), medicalRecord.getLastName()));
    }

    @Test
    public void getAgeFromBirthdate() {
        assertEquals(10, MedicalRecordService.getAgeFromBirthdate(medicalRecord.getBirthdate()));
    }

    @Test
    public void getAgeFromPersonTest() {
        given(medicalRecordRepository.getMedicalRecord(anyString(), anyString())).willReturn(medicalRecord);
        assertEquals(10, medicalRecordService.getAge(medicalRecord.getFirstName(), medicalRecord.getLastName()));
    }
}