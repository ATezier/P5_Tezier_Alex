package com.safetynet.api.service;

import com.safetynet.api.model.AreaPopulation;
import com.safetynet.api.model.FireStation;
import com.safetynet.api.model.Person;
import com.safetynet.api.model.Summary;
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


    public Summary getSummaryByName(String firstName, String lastName) {
        Summary s = null;
        Person p = this.getPerson(firstName, lastName);
        if(p != null) {
            s = new Summary();
            s.updateWithPerson(p);
            s.updateWithMedicalRecord(this.getMedicalRecord(p.getFirstName(), p.getLastName()));
            s.updateWithFireStation(this.getFireStationByAddress(p.getAddress()));
        }
        return s;
    }

    public Summary getSummaryByPerson(Person p) {
        Summary s = null;
        if(p != null) {
            s = new Summary();
            s.updateWithPerson(p);
            s.updateWithMedicalRecord(this.getMedicalRecord(p.getFirstName(), p.getLastName()));
            s.updateWithFireStation(this.getFireStationByAddress(p.getAddress()));
        }
        return s;
    }

    public List<Summary> getSummariesByAddress(String address) {
        List<Summary> res = new ArrayList<>();
        List<Person> persons = this.getPersonsByAddress(address);
        for(Person p : persons) {
            Summary s = getSummaryByPerson(p);
            res.add(s);
        }
        return res;
    }

    public List<Summary> getSummariesByFireStationNumber(int number) {
        List<Summary> res = new ArrayList<>();
        List<FireStation> fireStations = this.getFireStationsByNumber(number);
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
        List<Person> persons = this.getPersons();
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
                summaries.add(getSummaryByPerson(p));
            }
        }
        return summaries;
    }


    public AreaPopulation getAreaPopulationByFireStationNumber(int number) {
        AreaPopulation res = null;
        List<Summary> summaries = null;
        summaries = this.getSummariesByFireStationNumber(number);
        if(!summaries.isEmpty()) res = new AreaPopulation(summaries);
        return res;
    }

    public AreaPopulation getAreaPopulationByAddress(String address) {
        AreaPopulation res = null;
        List<Summary> summaries = null;
        if(address != null) summaries = this.getSummariesByAddress(address);
        if(!summaries.isEmpty()) res = new AreaPopulation(summaries);
        return res;
    }

    public  AreaPopulation childAlert(String address) {
        AreaPopulation res = null;
        res = getAreaPopulationByAddress(address);
        if(res != null && res.getChildCount() < 1) res = null;
        return res;
    }
}
