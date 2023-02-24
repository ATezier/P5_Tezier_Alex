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
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@AutoConfigureMockMvc
public class AreaPopulationServiceTest {
    private static Person person;

    @Autowired
    AreaPopulationService areaPopulationService;

    @BeforeAll
    public static void setup() {
        person = new Person();
        person.setFirstName("John");
        person.setLastName("Boyd");
        person.setAddress("1509 Culver St");
        person.setCity("Culver");
        person.setZip(97451);
        person.setPhone("841-874-6512");
        person.setEmail("jaboyd@email.com");
    }

    @Test
    public void getAreaPopulationByFireStationNumberTest() {
        assertFalse(areaPopulationService.getAreaPopulationByFireStationNumber(1).equals(null));
    }

    @Test
    public void getAreaPopulationByFireStationNumberTestWrongNumber() {
        assertTrue(areaPopulationService.getAreaPopulationByFireStationNumber(-1) == null);
    }

    @Test
    public void getAreaPopulationByAddressTest() {
        assertFalse(areaPopulationService.getAreaPopulationByAddress(person.getAddress()).equals(null));
    }

    @Test
    public void getAreaPopulationByAddressTestWrongAddress() {
        assertTrue(areaPopulationService.getAreaPopulationByAddress("trickedAddress") == null);
    }

    @Test
    public void childAlertTest() {
        assertFalse(areaPopulationService.childAlert(person.getAddress()).equals(null));
    }

}