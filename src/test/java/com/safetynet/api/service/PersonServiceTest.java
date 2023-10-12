package com.safetynet.api.service;
import com.safetynet.api.model.DataCluster;
import com.safetynet.api.model.FireStation;
import com.safetynet.api.model.MedicalRecord;
import com.safetynet.api.model.Person;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.util.ReflectionTestUtils;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

@SpringBootTest
@AutoConfigureMockMvc
public class PersonServiceTest {
    private static PersonService personService;
    private static Person person;

    @BeforeAll
    public static void setup() {
        personService = new PersonService();
        DataCluster dataCluster = new DataCluster();
        dataCluster.setPersons(new ArrayList<>());
        dataCluster.setFirestations(new ArrayList<>());
        dataCluster.setMedicalrecords(new ArrayList<>());
        person = new Person();
        person.setFirstName("Sophie");
        person.setLastName("Dupont");
        person.setAddress("1510 Culver St");
        person.setCity("Culver");
        person.setZip(97451);
        person.setPhone("841-874-6512");
        person.setEmail("sDupont@email.com");
        FireStation fireStation = new FireStation();
        fireStation.setStation(9);
        fireStation.setAddress("1510 Culver St");
        MedicalRecord medicalRecord = new MedicalRecord();
        medicalRecord.setFirstName(person.getFirstName());
        medicalRecord.setLastName(person.getLastName());
        medicalRecord.setBirthdate("03/06/1928");
        medicalRecord.setMedications(new String[]{"aznol:60mg","hydrapermazol:900mg","pharmacol:5000mg","terazine:500mg"});
        medicalRecord.setAllergies(new String[]{"peanut","shellfish","aznol"});
        dataCluster.getPersons().add(person);
        dataCluster.getFirestations().add(fireStation);
        dataCluster.getMedicalrecords().add(medicalRecord);
        ReflectionTestUtils.setField(personService, "dataCluster", dataCluster);
    }

    @Test
    public void getPersonsByAddressTest() {
        assertFalse(personService.getPersonsByAddress(person.getAddress()).isEmpty());
    }

    @Test
    public void getPersonsByAddressTestWrongAddress() {
        String address = "tricked address";
        assertTrue(personService.getPersonsByAddress(address).isEmpty());
    }

    @Test
    public void getEmailsByCityTest() {
        assertFalse(personService.getEmailsByCity(person.getCity()).isEmpty());
    }

    @Test
    public void getEmailsByCityTestWrongCity() {
        String city = "trickedCity";
        assertTrue(personService.getEmailsByCity(city).isEmpty());
    }

    @Test
    public void getPhonesByFireStationTest() {
        int station = 9;
        assertFalse(personService.getPhonesByFireStation(station).isEmpty());
    }

    @Test
    public void getPhonesByFireStationTestWrongStation() {
        int station = -1;
        assertTrue(personService.getPhonesByFireStation(station).isEmpty());
    }

    @Test
    public void getSummaryByName() {
        assertFalse(personService.getSummariesByName(person.getLastName(), person.getFirstName()).isEmpty());
    }

    @Test
    public void getSummaryByPerson() {
        assertFalse(personService.getSummaryByPerson(person) == null);
    }

    @Test
    public void getSummariesByAddress() {
        assertFalse(personService.getSummariesByAddress(person.getAddress()).isEmpty());
    }

    @Test
    public void getSummariesByStationNumberList() {
        List<Integer> input = new ArrayList<>();
        input.add(9);
        assertFalse(personService.getSummariesByStationNumberList(input).isEmpty());
    }

    @Test
    public void getSummariesByName() {
        assertFalse(personService.getSummariesByName(person.getLastName(), person.getFirstName()).isEmpty());
    }

    @Test
    public void getAreaPopulationByFireStationNumber() {
        assertFalse(personService.getAreaPopulationByFireStationNumber(9) == null);
    }

    @Test
    public void getAreaPopulationByAddress() {
        assertFalse(personService.getAreaPopulationByAddress(person.getAddress()) == null);
    }

    @Test
    public void childAlert() {
        assertFalse(personService.childAlert(person.getAddress()) != null);
    }
}