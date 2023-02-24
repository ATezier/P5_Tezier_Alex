package com.safetynet.api.service;

import com.safetynet.api.model.FireStation;
import com.safetynet.api.model.MedicalRecord;
import com.safetynet.api.model.Person;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@AutoConfigureMockMvc
public class MedicalRecordServiceTest {
    private static Person person;
    private static MedicalRecord medicalRecord;

    @Autowired
    MedicalRecordService medicalRecordService;

    @BeforeAll
    public static void setup() {
        LocalDate date = LocalDate.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MM/dd/yyyy");
        String birthdate = date.minusYears(10).format(formatter);

        String[] allergies = {"azertyuiop"};
        String[] medications = {"poiuytreza"};
        person = new Person();
        medicalRecord = new MedicalRecord();
        person.setFirstName("John");
        person.setLastName("Doe");
        person.setAddress("1509 Culver St");
        person.setCity("Culver");
        person.setZip(97451);
        person.setPhone("841-874-6512");
        person.setEmail("jaboyd@email.com");
        medicalRecord.setFirstName(person.getFirstName());
        medicalRecord.setLastName(person.getLastName());
        medicalRecord.setBirthdate(birthdate);
        medicalRecord.setMedications(medications);
        medicalRecord.setAllergies(allergies);

    }

    @Test
    public void getAgeFromBirthdate() {
        assertEquals(10, MedicalRecordService.getAgeFromBirthdate(medicalRecord.getBirthdate()));
    }

    @Test
    public void getAgeFromPersonTest() {
        medicalRecordService.createPerson(person);
        medicalRecordService.createMedicalRecord(medicalRecord);
        assertEquals(10, medicalRecordService.getAgeFromPerson(person));
        medicalRecordService.deleteMedicalRecord(person.getLastName(), person.getFirstName());
        medicalRecordService.deletePerson(person.getFirstName(), person.getLastName());
    }
}