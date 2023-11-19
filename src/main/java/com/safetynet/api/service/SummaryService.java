package com.safetynet.api.service;

import com.safetynet.api.dto.Summary;
import com.safetynet.api.model.FireStation;
import com.safetynet.api.model.MedicalRecord;
import com.safetynet.api.model.Person;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class SummaryService {
    @Autowired
    PersonService personService;
    @Autowired
    MedicalRecordService medicalRecordService;
    @Autowired
    FireStationService fireStationService;

    public void updateWithPerson(Summary summary, Person person) {
        if(person != null && summary != null) {
            summary.setFirstName(person.getFirstName());
            summary.setLastName(person.getLastName());
            summary.setAddress(person.getAddress());
            summary.setPhone(person.getPhone());
            summary.setCity(person.getCity());
            summary.setZip(person.getZip());
            summary.setEmail(person.getEmail());
        }
    }

    public void updateWithMedicalRecord(Summary summary, MedicalRecord medicalRecord) {
        if(medicalRecord != null && summary != null) {
            summary.setAge(MedicalRecordService.getAgeFromBirthdate(medicalRecord.getBirthdate()));
            summary.setMedications(medicalRecord.getMedications());
            summary.setAllergies(medicalRecord.getAllergies());
        }
    }

    public void updateWithFireStation(Summary summary, FireStation fireStation) {
        if(fireStation != null && summary != null) {
            summary.setStation(fireStation.getStation());
        }
    }


    public Summary getSummaryByName(String firstName, String lastName) {
        Summary s = null;
        Person p = personService.getPerson(firstName, lastName);
        if(p != null) {
            s = new Summary();
            updateWithPerson(s, p);
            updateWithMedicalRecord(s, medicalRecordService.getMedicalRecord(firstName, lastName));
            updateWithFireStation(s, fireStationService.getFireStation(p.getAddress()));
        }
        return s;
    }

    public Summary getSummaryByPerson(Person p) {
        Summary s = null;
        if(p != null) {
            s = new Summary();
            updateWithPerson(s, p);
            updateWithMedicalRecord(s, medicalRecordService.getMedicalRecord(s.getFirstName(), s.getLastName()));
            updateWithFireStation(s, fireStationService.getFireStation(p.getAddress()));
        }
        return s;
    }

    public List<Summary> getSummariesByAddress(String address) {
        List<Summary> res = new ArrayList<>();
        List<Person> persons = personService.getPersonsByAddress(address);
        for(Person p : persons) {
            Summary s = getSummaryByPerson(p);
            res.add(s);
        }
        return res;
    }

    public List<Summary> getSummariesByStation(int number) {
        List<Summary> res = new ArrayList<>();
        List<FireStation> fireStations = fireStationService.getFireStationsByNumber(number);
        if(fireStations != null) {
            for(FireStation f : fireStations) {
                res.addAll(getSummariesByAddress(f.getAddress()));
            }
        }
        return res;
    }

    public List<Summary> getSummariesByStations(List<Integer> stations) {
        List<Summary> summaries = new ArrayList<>();
        List<Integer> stationsWithoutDuplicates = stations.stream().distinct().collect(Collectors.toList());
        if(stations != null) {
            for(Integer station : stationsWithoutDuplicates) {
                summaries.addAll(getSummariesByStation(station));
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
                summaries.add(getSummaryByPerson(p));
            }
        }
        return summaries;
    }
    public List<String> getPhonesByFireStation(int station) {
        List<String> phones = new ArrayList<>();
        List<FireStation> fireStations = null;
        List<Person> persons = null;
        fireStations = fireStationService.getFireStationsByNumber(station);
        if(fireStations != null) {
            for(FireStation f : fireStations) {
                persons = personService.getPersonsByAddress(f.getAddress());
                for(Person p : persons) {
                    phones.add(p.getPhone());
                }
            }
        }
        return phones;
    }
}
