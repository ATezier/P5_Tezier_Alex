package com.safetynet.api.repository;

import com.safetynet.api.model.FireStation;
import com.safetynet.api.model.MedicalRecord;
import com.safetynet.api.model.Person;
import com.safetynet.api.repository.DataManager;
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
public class DataManagerTest {
    private static Person person;
    private static MedicalRecord medicalRecord;
    private static FireStation fireStation;

    @Autowired
    DataManager dataManager;

    @BeforeAll
    public static void setup() {
        person = new Person();
        person.setFirstName("Denis");
        person.setLastName("Boyd");
        person.setAddress("1509 Culver St");
        person.setCity("Culver");
        person.setZip(97451);
        person.setPhone("841-874-6512");
        person.setEmail("jaboyd@email.com");
        fireStation = new FireStation();
        fireStation.setStation(99);
        fireStation.setAddress("9999 Culver St");
        medicalRecord = new MedicalRecord();
        String[] allergies = {"azertyuiop"};
        String[] medications = {"poiuytreza"};
        medicalRecord.setAllergies(allergies);
        medicalRecord.setMedications(medications);
        medicalRecord.setFirstName(person.getFirstName());
        medicalRecord.setLastName(person.getLastName());
        medicalRecord.setBirthdate("04/01/1789");
    }

    @Test
    public void saveDataTest() {
        dataManager.createPerson(person);
        dataManager.saveData();
        dataManager.loadData();
        assertFalse(dataManager.getPerson(person.getFirstName(), person.getLastName()) == null);
        dataManager.deletePerson(person.getFirstName(), person.getLastName());
        dataManager.saveData();
    }

    @Test
    public void loadDataTest() {
        dataManager.loadData();
        assertTrue(dataManager.getPerson(person.getFirstName(), person.getLastName()) == null);
    }

    @Test
    public void getPersonsTest() {
        assertFalse(dataManager.getPersons().isEmpty());
    }

    @Test
    public void createUpdateDeletePersonTest() {
        dataManager.createPerson(person);
        assertFalse(dataManager.getPerson(person.getFirstName(), person.getLastName()).equals(null));
        person.setEmail("test@email.com");
        dataManager.updatePerson(person);
        assertEquals("test@email.com", dataManager.getPerson(person.getFirstName(), person.getLastName()).getEmail());
        dataManager.deletePerson(person.getFirstName(), person.getLastName());
        assertTrue(dataManager.getPerson(person.getFirstName(), person.getLastName()) == null);
    }

    @Test
    public void getFireStationsTest() {
        assertFalse(dataManager.getFireStations().isEmpty());
    }

    @Test
    public void getFireStationsByNumber() {
        assertFalse(dataManager.getFireStationsByNumber(1).isEmpty());
    }

    @Test
    public void createUpdateDeleteFireStationTest() {
        dataManager.createFireStation(fireStation);
        assertFalse(dataManager.getFireStationByAddress(fireStation.getAddress()).equals(null));
        fireStation.setStation(98);
        dataManager.updateFireStationNbr(fireStation);
        assertEquals(98, dataManager.getFireStationByAddress(fireStation.getAddress()).getStation());
        dataManager.deleteFireStation(fireStation.getAddress());
        assertTrue(dataManager.getFireStationByAddress(fireStation.getAddress()) == null);
    }

    @Test
    public void getMedicalRecords() {
        assertFalse(dataManager.getMedicalRecords().isEmpty());
    }

    @Test
    public void createUpdateDeleteMedicalRecordTest() {
        dataManager.createMedicalRecord(medicalRecord);
        assertFalse(dataManager.getMedicalRecord(medicalRecord.getFirstName(), medicalRecord.getLastName()).equals(null));
        medicalRecord.setBirthdate("10/12/1789");
        dataManager.updateMedicalRecord(medicalRecord);
        assertEquals("10/12/1789", dataManager.getMedicalRecord(medicalRecord.getFirstName(), medicalRecord.getLastName()).getBirthdate());
        dataManager.deleteMedicalRecord(medicalRecord.getLastName(), medicalRecord.getFirstName());
        assertTrue(dataManager.getMedicalRecord(medicalRecord.getFirstName(), medicalRecord.getLastName()) == null);
    }
}