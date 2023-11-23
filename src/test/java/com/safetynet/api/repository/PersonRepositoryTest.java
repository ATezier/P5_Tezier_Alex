package com.safetynet.api.repository;

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
import static org.mockito.BDDMockito.given;

@SpringBootTest
@AutoConfigureMockMvc
public class PersonRepositoryTest {
    private static List<Person> persons;
    private static Person person;
    @MockBean
    DataPersistent dataPersistent;
    @Autowired
    PersonRepository personRepository;
    @BeforeAll
    public static void setup() {
        persons = new ArrayList<>();
        person = new Person();
        person.setFirstName("Denisa");
        person.setLastName("Boyd");
        person.setAddress("1509 Culver St");
        person.setCity("Culver");
        person.setZip(97451);
        person.setPhone("841-874-6512");
        person.setEmail("jaboyd@email.com");
    }
    @Test
    public void getPersonsTest() {
        given(dataPersistent.getPersons()).willReturn(persons);
        assertNotNull(personRepository.getPersons());
    }
    @Test
    public void createPersonTest() {
        given(dataPersistent.getPersons()).willReturn(persons);
        assertTrue(personRepository.createPerson(person));
        personRepository.deletePerson(person.getFirstName(), person.getLastName());
    }
    @Test
    public void updatePersonTest() {
        given(dataPersistent.getPersons()).willReturn(persons);
        Person p = new Person();
        personRepository.createPerson(person);
        p.setFirstName(person.getFirstName());
        p.setLastName(person.getLastName());
        p.setAddress("9999 Culver St");
        p.setCity("Culver");
        p.setZip(97451);
        p.setEmail("test@tester.com");
        p.setPhone("123-456-7890");
        assertTrue(personRepository.updatePerson(p));
        personRepository.deletePerson(person.getFirstName(), person.getLastName());
    }
    @Test
    public void deletePersonTest() {
        given(dataPersistent.getPersons()).willReturn(persons);
        personRepository.createPerson(person);
        assertTrue(personRepository.deletePerson(person.getFirstName(), person.getLastName()));
    }
}
