package com.safetynet.api.repository;

import com.safetynet.api.model.Person;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class PersonRepository {
    private List<Person> persons = null;

    private static final Logger logs = LogManager.getLogger(PersonRepository.class);
    @Autowired
    DataPersistent dataPersistent;

    public Person getPerson(String firstName, String lastName) {
        Person person = null;
        if(persons == null) persons = dataPersistent.getPersons();
        if(firstName == null || lastName == null) logs.error("firstname or lastname is null");
        else {
            person = persons.stream()
                    .filter(p -> p.getFirstName().equals(firstName) && p.getLastName().equals(lastName))
                    .findAny()
                    .orElse(null);
            if(person == null) logs.debug("Person doesn't exist.");
        }
        return person;
    }
    public List<Person> getPersons() {
        if(persons == null) persons = dataPersistent.getPersons();
        if (persons == null) logs.error("No person found.");
        return persons; }

    public boolean createPerson(Person person) {
        boolean res = false;
        if(getPerson(person.getFirstName(), person.getLastName()) == null) {
            res = persons.add(person);
            dataPersistent.savePersons(persons);
            if(res) logs.debug(person.getFirstName()+" "+person.getLastName()+" is successfully created.");
            else logs.error("Fail to create.");
        }
        return res;
    }

    public boolean updatePerson(Person person) {
        boolean res = false;
        if (getPerson(person.getFirstName(), person.getLastName()) != null) {
            for(Person p : persons) {
                if(p.getFirstName().equals(person.getFirstName()) && p.getLastName().equals(person.getLastName())) {
                    if(p.equals(person)) logs.debug("Nothing to update.");
                    else {
                    p.setAddress(person.getAddress());
                    p.setCity(person.getCity());
                    p.setEmail(person.getEmail());
                    p.setZip(person.getZip());
                    p.setPhone(person.getPhone());
                    dataPersistent.savePersons(persons);
                    res = true;
                    break;
                    }
                }
            }
            if(res) logs.debug(person.getFirstName()+" "+person.getLastName()+" is successfully updated.");
            else logs.error("Fail to update.");
        }
        return res;
    }

    public boolean deletePerson(String firstName, String lastName) {
        boolean res = false;
        Person person = getPerson(firstName, lastName);
        if(person != null) {
            res = persons.remove(person);
            dataPersistent.savePersons(persons);
            if(res) logs.debug(person.getFirstName()+" "+person.getLastName()+" is successfully deleted.");
            else logs.error("Fail to delete.");
        }
        return res;
    }
}
