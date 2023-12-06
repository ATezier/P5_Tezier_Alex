package com.safetynet.api.service;
import com.safetynet.api.model.Person;
import com.safetynet.api.repository.PersonRepository;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.BDDMockito.given;

@SpringBootTest
@AutoConfigureMockMvc
public class PersonServiceTest {
    private static Person person;
    @MockBean
    PersonRepository personRepository;
    @Autowired
    PersonServiceImpl personService;

    @BeforeAll
    public static void setup() {
        person = new Person();
        person.setFirstName("Sophie");
        person.setLastName("Dupont");
        person.setAddress("1510 Culver St");
        person.setCity("Culver");
        person.setZip(97451);
        person.setPhone("841-874-6512");
        person.setEmail("sDupont@email.com");
    }
    @Test
    public void getPersonsTest() {
        List<Person> persons = new ArrayList<>();
        persons.add(person);
        given(personRepository.getPersons()).willReturn(persons);
        assertFalse(personService.getPersons().isEmpty());
    }
    @Test
    public void getPersonTest() {
        given(personRepository.getPerson(anyString(), anyString())).willReturn(person);
        assertNotNull(personService.getPerson(person.getFirstName(), person.getLastName()));
    }
    @Test
    public void createPersonTest() {
        given(personRepository.createPerson(person)).willReturn(true);
        assertTrue(personService.createPerson(person));
    }
    @Test
    public void updatePersonTest() {
        given(personRepository.updatePerson(person)).willReturn(true);
        assertTrue(personService.updatePerson(person));
    }
    @Test
    public void deletePersonTest() {
        given(personRepository.deletePerson(anyString(), anyString())).willReturn(true);
        assertTrue(personService.deletePerson(person.getFirstName(), person.getLastName()));
    }
    @Test
    public void getPersonsByAddressTest() {
        List<Person> persons = new ArrayList<>();
        persons.add(person);
        given(personRepository.getPersons()).willReturn(persons);
        assertFalse(personService.getPersonsByAddress(person.getAddress()).isEmpty());
    }
    @Test
    public void getEmailsByCityTest() {
        List<Person> persons = new ArrayList<>();
        persons.add(person);
        given(personRepository.getPersons()).willReturn(persons);
        assertFalse(personService.getEmailsByCity(person.getCity()).isEmpty());
    }
}