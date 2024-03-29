package com.safetynet.api.service;

import com.safetynet.api.dto.Summary;
import com.safetynet.api.model.FireStation;
import com.safetynet.api.model.MedicalRecord;
import com.safetynet.api.model.Person;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class SummaryServiceImpl implements SummaryService {
    private static final Logger logger = org.apache.logging.log4j.LogManager.getLogger(SummaryServiceImpl.class);
    @Autowired
    PersonServiceImpl personServiceImpl;
    @Autowired
    MedicalRecordServiceImpl medicalRecordServiceImpl;
    @Autowired
    FireStationServiceImpl fireStationServiceImpl;

    public void updateWithPerson(Summary summary, Person person) {
        if(person == null) logger.debug("SummaryService.updateWithPerson: person is null");
        else if (summary == null) logger.debug("SummaryService.updateWithPerson: summary is null");
        else {
            summary.setFirstName(person.getFirstName());
            summary.setLastName(person.getLastName());
            summary.setAddress(person.getAddress());
            summary.setPhone(person.getPhone());
            summary.setCity(person.getCity());
            summary.setZip(person.getZip());
            summary.setEmail(person.getEmail());
            logger.info("SummaryService.updateWithPerson:  Successfully updated.");
        }
    }

    public void updateWithMedicalRecord(Summary summary, MedicalRecord medicalRecord) {
        if(medicalRecord == null) logger.debug("SummaryService.updateWithMedicalRecord: medicalRecord is null");
        else if (summary == null) logger.debug("SummaryService.updateWithMedicalRecord: summary is null");
        else {
            summary.setAge(MedicalRecordService.getAgeFromBirthdate(medicalRecord.getBirthdate()));
            summary.setMedications(medicalRecord.getMedications());
            summary.setAllergies(medicalRecord.getAllergies());
            logger.info("SummaryService.updateWithMedicalRecord: Successfully updated.");
        }
    }

    public void updateWithFireStation(Summary summary, FireStation fireStation) {
        if (fireStation == null) logger.debug("SummaryService.updateWithFireStation: fireStation is null");
        else if (summary == null) logger.debug("SummaryService.updateWithFireStation: summary is null");
        else {
            summary.setStation(fireStation.getStation());
            logger.info("SummaryService.updateWithFireStation: Successfully updated.");
        }
    }


    public Summary getSummaryByName(String firstName, String lastName) {
        Summary s = null;
        Person p = personServiceImpl.getPerson(firstName, lastName);
        if(p != null) {
            s = new Summary();
            updateWithPerson(s, p);
            updateWithMedicalRecord(s, medicalRecordServiceImpl.getMedicalRecord(firstName, lastName));
            updateWithFireStation(s, fireStationServiceImpl.getFireStation(p.getAddress()));
            logger.info("SummaryService.getSummaryByName: Successfully created.");
        }
        return s;
    }

    public Summary getSummaryByPerson(Person p) {
        Summary s = null;
        if(p != null) {
            s = new Summary();
            updateWithPerson(s, p);
            updateWithMedicalRecord(s, medicalRecordServiceImpl.getMedicalRecord(s.getFirstName(), s.getLastName()));
            updateWithFireStation(s, fireStationServiceImpl.getFireStation(p.getAddress()));
            logger.info("SummaryService.getSummaryByPerson: Successfully created.");
        }
        return s;
    }

    public List<Summary> getSummariesByAddress(String address) {
        List<Summary> res = new ArrayList<>();
        List<Person> persons = personServiceImpl.getPersonsByAddress(address);
        for(Person p : persons) {
            Summary s = getSummaryByPerson(p);
            res.add(s);
        }
        if (res.isEmpty()) logger.debug("SummaryService.getSummariesByAddress: No summary found.");
        else logger.info("SummaryService.getSummariesByAddress: Successfully created.");
        return res;
    }

    public List<Summary> getSummariesByStation(int number) {
        List<Summary> res = new ArrayList<>();
        List<FireStation> fireStations = fireStationServiceImpl.getFireStationsByNumber(number);
        if(fireStations != null) {
            for(FireStation f : fireStations) {
                res.addAll(getSummariesByAddress(f.getAddress()));
            }
        }
        if (res.isEmpty()) logger.debug("SummaryService.getSummariesByStation: No summary found.");
        else logger.info("SummaryService.getSummariesByStation: Successfully created.");
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
        if (summaries.isEmpty()) logger.debug("SummaryService.getSummariesByStations: No summary found.");
        else logger.info("SummaryService.getSummariesByStations: Successfully created.");
        return summaries;
    }

    public  List<Summary> getSummariesByName(String lastName, String firstName) {
        List<Summary> summaries = null;
        List<Person> persons = personServiceImpl.getPersons();
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
        if (summaries.isEmpty()) logger.debug("SummaryService.getSummariesByName: No summary found.");
        else logger.info("SummaryService.getSummariesByName: Successfully created.");
        return summaries;
    }
    public List<String> getPhonesByFireStation(int station) {
        List<String> phones = new ArrayList<>();
        List<FireStation> fireStations = null;
        List<Person> persons = null;
        fireStations = fireStationServiceImpl.getFireStationsByNumber(station);
        if(fireStations != null) {
            for(FireStation f : fireStations) {
                persons = personServiceImpl.getPersonsByAddress(f.getAddress());
                for(Person p : persons) {
                    phones.add(p.getPhone());
                }
            }
        }
        if (phones.isEmpty()) logger.debug("SummaryService.getPhonesByFireStation: No phone found.");
        else logger.info("SummaryService.getPhonesByFireStation: Successfully created.");
        return phones;
    }
}
