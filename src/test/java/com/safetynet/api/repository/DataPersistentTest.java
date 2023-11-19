package com.safetynet.api.repository;

import com.safetynet.api.model.FireStation;
import com.safetynet.api.model.MedicalRecord;
import com.safetynet.api.model.Person;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

@SpringBootTest
@AutoConfigureMockMvc
public class DataPersistentTest {
    private static Person person;
    @Autowired
    DataPersistent dataPersistent;
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
    }
    @Test
    public void saveDataTest() {
        List<Person> persons = dataPersistent.getPersons();
        persons.add(person);
        dataPersistent.savePersons(persons);
        persons = dataPersistent.getPersons();
        assertTrue(persons.contains(person));
        persons.remove(person);
        dataPersistent.savePersons(persons);
    }

    @Test
    public void loadDataTest() {
        assertFalse(dataPersistent.loadData().getPersons().isEmpty());
    }
}
