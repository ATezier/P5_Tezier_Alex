package com.safetynet.api.service;

import com.safetynet.api.model.FireStation;
import com.safetynet.api.model.Person;
import com.safetynet.api.model.Summary;
import com.safetynet.api.repository.DataManager;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class SummaryService {
    @Autowired
    PersonService personService;

    public Summary getSummaryByName(String firstName, String lastName) {
        Summary s = null;
        Person p = personService.getPerson(firstName, lastName);
        if(p != null) {
            s = new Summary();
            s.updateWithPerson(p);
            s.updateWithMedicalRecord(personService.getMedicalRecord(p.getFirstName(), p.getLastName()));
            s.updateWithFireStation(personService.getFireStationByAddress(p.getAddress()));
        }
        return s;
    }

    public List<Summary> getSummariesByAddress(String address) {
        List<Summary> res = new ArrayList<>();
        List<Person> persons = personService.getPersonsByAddress(address);
        for(Person p : persons) {
            Summary s = getSummaryByName(p.getFirstName(), p.getLastName());
            res.add(s);
        }
        return res;
    }

    public List<Summary> getSummariesByFireStationNumber(int number) {
        List<Summary> res = new ArrayList<>();
        List<FireStation> fireStations = personService.getFireStationsByNumber(number);
        if(fireStations != null) {
            for(FireStation f : fireStations) {
                res.addAll(getSummariesByAddress(f.getAddress()));
            }
        }
        return res;
    }

    public List<Summary> getSummariesByStationNumberList(List<Integer> stations) {
        List<Summary> summaries = new ArrayList<>();
        if(stations != null) {
            for(Integer station : stations) {
                summaries.addAll(getSummariesByFireStationNumber(station));
            }
        }
        return summaries;
    }

    public  List<Summary> getSummariesByName(String lastName, String firstName) {
        List<Summary> summaries = null;
        List<Person> persons = personService.getPersons();
        List<Person> filteredPersons = null;
        if(lastName != null && firstName != null) {
            summaries = new ArrayList<>();
            summaries.add(getSummaryByName(firstName, lastName));
        }
        if(persons != null) {
            filteredPersons = persons.stream()
                    .filter(p -> p.getLastName().equals(lastName) && !p.getFirstName().equals(firstName))
                    .collect(Collectors.toList());
        }
        if(filteredPersons != null) {
            for(Person p : filteredPersons) {
                summaries.add(getSummaryByName(p.getFirstName(), p.getLastName()));
            }
        }
        return summaries;
    }
}
