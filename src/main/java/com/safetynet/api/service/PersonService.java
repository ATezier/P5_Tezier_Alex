package com.safetynet.api.service;

import com.safetynet.api.dto.AreaPopulation;
import com.safetynet.api.model.FireStation;
import com.safetynet.api.model.Person;
import com.safetynet.api.dto.Summary;
import com.safetynet.api.repository.DataPersistent;
import com.safetynet.api.repository.PersonRepository;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class PersonService {
    private static final Logger logger = org.apache.logging.log4j.LogManager.getLogger(PersonService.class);
    @Autowired
    PersonRepository personRepository;

    public List<Person> getPersons() { return personRepository.getPersons(); }

    public Person getPerson(String firstName, String lastName) { return personRepository.getPerson(firstName, lastName); }
    public boolean createPerson(Person person) { return personRepository.createPerson(person); }

    public boolean updatePerson(Person person) { return personRepository.updatePerson(person); }

    public boolean deletePerson(String firstName, String lastName) { return personRepository.deletePerson(firstName, lastName); }

    public List<Person> getPersonsByAddress(String address) {
        List<Person> persons = null;
        persons = this.getPersons().stream()
                .filter(person -> person.getAddress().equals(address))
                .collect(Collectors.toList());
        if(persons.isEmpty()) logger.warn("No person found for address " + address);
        return persons;
    }

    public List<String> getEmailsByCity(String city) {
        List<String> emails = null;
        List<Person> persons = getPersons();
        if(city == null) logger.warn("No city provided");
        else {
            emails = new ArrayList<>();
            for(Person person : persons) {
                if(person.getCity().equals(city) && person.getEmail() != null) emails.add(person.getEmail());
            }
        }
        return emails;
    }
}
