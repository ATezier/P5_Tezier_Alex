package com.safetynet.api.service;

import com.safetynet.api.model.AreaPopulation;
import com.safetynet.api.model.FireStation;
import com.safetynet.api.model.Person;
import com.safetynet.api.repository.DataManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class PersonService extends DataManager{

    public List<Person> getPersonsByAddress(String address) {
        List<Person> res;
        res = this.getPersons()
                .stream()
                .filter(person -> person.getAddress().equals(address))
                .collect(Collectors.toList());
        return res;
    }

    public List<String> getEmailsByCity(String city) {
        List<String> emails = null;
        List<Person> persons = this.getPersons();
        if(persons != null && city != null) {
            emails = new ArrayList<>();
            for(Person person : persons) {
                if(person.getCity().equals(city) && person.getEmail() != null) emails.add(person.getEmail());
            }
        }
        return emails;
    }

    public List<String> getPhonesByFireStation(int station) {
        List<String> phones = new ArrayList<>();
        List<FireStation> fireStations = null;
        List<Person> persons = null;
        fireStations = this.getFireStationsByNumber(station);
        if(fireStations != null) {
            for(FireStation f : fireStations) {
                persons = this.getPersonsByAddress(f.getAddress());
                for(Person p : persons) {
                    phones.add(p.getPhone());
                }
            }
        }
        return phones;
    }
}
