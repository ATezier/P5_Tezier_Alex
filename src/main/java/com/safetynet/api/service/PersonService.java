package com.safetynet.api.service;

import com.safetynet.api.model.Person;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
@Service
public interface PersonService {
    public List<Person> getPersons();
    public Person getPerson(String firstName, String lastName);
    public boolean createPerson(Person person);
    public boolean updatePerson(Person person);
    public boolean deletePerson(String firstName, String lastName);
    public List<Person> getPersonsByAddress(String address);
    public List<String> getEmailsByCity(String city);
}
