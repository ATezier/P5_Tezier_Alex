package com.safetynet.api.service;

import com.safetynet.api.dto.Summary;
import com.safetynet.api.model.FireStation;
import com.safetynet.api.model.MedicalRecord;
import com.safetynet.api.model.Person;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.BDDMockito.given;

@SpringBootTest
@AutoConfigureMockMvc
public class SummaryServiceTest {
    private static Person person;
    private static MedicalRecord medicalRecord;
    private static FireStation fireStation;
    private static Summary summary;
    @MockBean
    PersonService personService;
    @MockBean
    MedicalRecordService medicalRecordService;
    @MockBean
    FireStationService fireStationService;
    @Autowired
    SummaryService summaryService;
    @BeforeAll
    private static void setUp() {
        person = new Person();
        person.setFirstName("John");
        person.setLastName("Boyd");
        person.setAddress("1509 Culver St");
        person.setCity("Culver");
        person.setZip(97451);
        person.setPhone("841-874-6512");
        person.setEmail("test@tester.com");
        medicalRecord = new MedicalRecord();
        medicalRecord.setFirstName("John");
        medicalRecord.setLastName("Boyd");
        medicalRecord.setBirthdate("03/06/1984");
        String[] medications = new String[2];
        medications[0] = "aznol:350mg";
        medications[1] = "hydrapermazol:100mg";
        medicalRecord.setMedications(medications);
        String[] allergies = new String[2];
        allergies[0] = "nillacilan";
        allergies[1] = "peanut";
        medicalRecord.setAllergies(allergies);
        fireStation = new FireStation();
        fireStation.setAddress("1509 Culver St");
        fireStation.setStation(3);
        summary = new Summary();
        summary.setFirstName("John");
        summary.setLastName("Boyd");
        summary.setAddress("1509 Culver St");
        summary.setCity("Culver");
        summary.setZip(97451);
        summary.setPhone("841-874-6512");
        summary.setEmail("test@tester.com");
        summary.setAge(37);
        summary.setMedications(medications);
        summary.setAllergies(allergies);
        summary.setStation(3);
    }

    @Test
    public void updateWithPersonTest() {
        Summary summary = new Summary();
        summaryService.updateWithPerson(summary, person);
        assertEquals("John", summary.getFirstName());
        assertEquals("Boyd", summary.getLastName());
        assertEquals("1509 Culver St", summary.getAddress());
        assertEquals("Culver", summary.getCity());
        assertEquals(97451, summary.getZip());
        assertEquals("841-874-6512", summary.getPhone());
        assertEquals("test@tester.com", summary.getEmail());
    }
    @Test
    public void updateWithMedicalRecordTest() {
        Summary summary = new Summary();
        summaryService.updateWithMedicalRecord(summary, medicalRecord);
        assertEquals(37, summary.getAge());
        assertEquals("aznol:350mg", summary.getMedications()[0]);
        assertEquals("hydrapermazol:100mg", summary.getMedications()[1]);
        assertEquals("nillacilan", summary.getAllergies()[0]);
        assertEquals("peanut", summary.getAllergies()[1]);
    }
    @Test
    public void updateWithFireStationTest() {
        Summary summary = new Summary();
        summaryService.updateWithFireStation(summary, fireStation);
        assertEquals(3, summary.getStation());
    }
    @Test
    public void getSummaryByNameTest() {
        given(personService.getPerson(anyString(), anyString())).willReturn(person);
        assertEquals(summary, summaryService.getSummaryByName("John", "Boyd"));
    }
    @Test
    public void getSummaryByPersonTest() {
        given(personService.getPerson(anyString(), anyString())).willReturn(person);
        assertEquals(summary, summaryService.getSummaryByPerson(person));
    }
    @Test
    public void getSummariesByAddressTest() {
        List<Person> persons = new ArrayList<>();
        persons.add(person);
        given(personService.getPersonsByAddress(anyString())).willReturn(persons);
        given(medicalRecordService.getMedicalRecord(anyString(), anyString())).willReturn(medicalRecord);
        given(fireStationService.getFireStation(anyString())).willReturn(fireStation);
        List<Summary> summaries = summaryService.getSummariesByAddress("1509 Culver St");
        assertFalse(summaries.isEmpty());
        assertEquals(summary, summaries.get(0));
    }
    @Test
    public void getSummariesByStationTest() {
        List<Person> persons = new ArrayList<>();
        persons.add(person);
        given(personService.getPersonsByAddress(anyString())).willReturn(persons);
        given(medicalRecordService.getMedicalRecord(anyString(), anyString())).willReturn(medicalRecord);
        given(fireStationService.getFireStation(anyString())).willReturn(fireStation);
        List<Summary> summaries = summaryService.getSummariesByStation(3);
        assertFalse(summaries.isEmpty());
        assertEquals(summary, summaries.get(0));
    }
    @Test
    public void getSummariesByStations() {
        List<Integer> stations = new ArrayList<>();
        stations.add(3);
        stations.add(3);
        List<Person> persons = new ArrayList<>();
        persons.add(person);
        given(personService.getPersonsByAddress(anyString())).willReturn(persons);
        given(medicalRecordService.getMedicalRecord(anyString(), anyString())).willReturn(medicalRecord);
        given(fireStationService.getFireStation(anyString())).willReturn(fireStation);
        List<Summary> summaries = summaryService.getSummariesByStations(stations);
        assertEquals(1, summaries.size());
        assertEquals(summary, summaries.get(0));
    }
    @Test
    public void getSummariesByName() {
        List<Person> persons = new ArrayList<>();
        persons.add(person);
        given(personService.getPersonsByAddress(anyString())).willReturn(persons);
        given(medicalRecordService.getMedicalRecord(anyString(), anyString())).willReturn(medicalRecord);
        given(fireStationService.getFireStation(anyString())).willReturn(fireStation);
        List<Summary> summaries = summaryService.getSummariesByName("John", "Boyd");
        assertFalse(summaries.isEmpty());
        assertEquals(summary, summaries.get(0));
    }
    @Test
    public void getPhonesByFireStation() {
        List<Person> persons = new ArrayList<>();
        persons.add(person);
        given(personService.getPersonsByAddress(anyString())).willReturn(persons);
        given(medicalRecordService.getMedicalRecord(anyString(), anyString())).willReturn(medicalRecord);
        given(fireStationService.getFireStation(anyString())).willReturn(fireStation);
        List<String> phones = summaryService.getPhonesByFireStation(3);
        assertFalse(phones.isEmpty());
        assertEquals("841-874-6512", phones.get(0));
    }
}
