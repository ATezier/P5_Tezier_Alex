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
public class SummaryServiceTest {
    private static Person person;

    @Autowired
    SummaryService summaryService;

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
    public void getSummaryByNameTest() {
        assertFalse(summaryService.getSummaryByName(person.getFirstName(), person.getLastName()).equals(null));
    }

    @Test
    public void getSummaryByNameTestWrongName() {
        assertTrue(summaryService.getSummaryByName("nobody", "nobody") == null);
    }

    @Test
    public void getSummariesByAddressTest() {
        assertFalse(summaryService.getSummariesByAddress(person.getAddress()).isEmpty());
    }

    @Test
    public void getSummariesByAddressTestWrongAddress() {
        assertTrue(summaryService.getSummariesByAddress("trickedAddress").isEmpty());
    }

    @Test
    public void getSummariesByFireStationNumberTest() {
        assertFalse(summaryService.getSummariesByFireStationNumber(1).isEmpty());
    }

    @Test
    public void getSummariesByFireStationNumberTestWrongStation() {
        assertTrue(summaryService.getSummariesByFireStationNumber(-1).isEmpty());
    }

    @Test
    public void getSummariesByStationNumberList() {
        List<Integer> list = new ArrayList<>();
        list.add(1);
        list.add(2);
        assertFalse(summaryService.getSummariesByStationNumberList(list).isEmpty());
    }

    @Test
    public void getSummariesByNameTest() {
        assertFalse(summaryService.getSummariesByName(person.getLastName(), person.getFirstName()).isEmpty());
    }
}