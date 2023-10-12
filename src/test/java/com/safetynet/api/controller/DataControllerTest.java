package com.safetynet.api.controller;

import com.safetynet.api.model.FireStation;
import com.safetynet.api.model.MedicalRecord;
import com.safetynet.api.model.Person;
import org.junit.Before;
import org.junit.Test;
import org.junit.jupiter.api.BeforeAll;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;

import java.time.Period;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
public class DataControllerTest {
    private Person person;
    private MedicalRecord medicalRecord;
    private FireStation fireStation;

    @Autowired
    public DataController dataController;

    @Autowired
    MockMvc mockMvc;

    @BeforeAll
    public void setUp() {
        String[] allergies = {"azertyuiop"};
        String[] medications = {"poiuytreza"};
        person = new Person();
        medicalRecord = new MedicalRecord();
        fireStation = new FireStation();
        person.setFirstName("John");
        person.setLastName("Doe");
        person.setAddress("123 Main St");
        person.setCity("Anytown");
        person.setZip(12345);
        person.setPhone("0123456789");
        person.setEmail("email");
        medicalRecord.setFirstName(person.getFirstName());
        medicalRecord.setLastName(person.getLastName());
        medicalRecord.setBirthdate("04/01/1789");
        medicalRecord.setMedications(medications);
        medicalRecord.setAllergies(allergies);
        fireStation.setStation(42);
        fireStation.setAddress("3214 Main St");
    }

    @Test
    public void createPersonTest() throws Exception{
        mockMvc.perform(post("/person")
            .contentType("application/json")
            .content(person.toString()))
            .andExpect(status().isOk());
    }

}
