package com.safetynet.api.repository;

import com.safetynet.api.model.Person;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@AutoConfigureMockMvc
public class PersonRepositoryTest {
    private static Person person;
    @Autowired
    PersonRepository personRepository;
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
    public void getPersonsTest() {
        assertFalse(personRepository.getPersons().isEmpty());
    }
    @Test
    public void createPersonTest() {
        assertTrue(personRepository.createPerson(person));
        personRepository.deletePerson(person.getFirstName(), person.getLastName());
    }
    @Test
    public void updatePersonTest() {
        personRepository.createPerson(person);
        person.setPhone("123-456-7890");
        assertTrue(personRepository.updatePerson(person));
        personRepository.deletePerson(person.getFirstName(), person.getLastName());
    }
    @Test
    public void deletePersonTest() {
        personRepository.createPerson(person);
        assertTrue(personRepository.deletePerson(person.getFirstName(), person.getLastName()));
    }
}
