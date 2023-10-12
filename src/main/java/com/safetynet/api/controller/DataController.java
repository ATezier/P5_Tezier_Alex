package com.safetynet.api.controller;

import com.safetynet.api.model.*;
import com.safetynet.api.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
public class DataController {
    @Autowired
     PersonService personService;
    @Autowired
     FireStationService fireStationService;
    @Autowired
     MedicalRecordService medicalRecordService;

    @PostMapping("/person")
    public void createPerson(@RequestBody Person person) {
        personService.createPerson(person);
    }
    @PutMapping("/person")
    public void updatePerson(@RequestBody Person person) {
        personService.updatePerson(person);
    }

    @DeleteMapping("/person")
    public void deletePerson(@RequestParam String firstName,@RequestParam String lastName) {
        personService.deletePerson(firstName, lastName);
    }

    @PostMapping("/firestation")
    public void createFireStation(@RequestBody FireStation fireStation) {
        fireStationService.createFireStation(fireStation);
    }

    @PutMapping("/firestation")
    public void updateFireStation(@RequestBody FireStation fireStation) {
        fireStationService.updateFireStationNbr(fireStation);
    }

    @DeleteMapping("/firestation")
    public void deleteFireStation(@RequestParam String address) {
        fireStationService.deleteFireStation(address);
    }

    @GetMapping("/firestation")
    public List<Summary> firestation(@RequestParam String stationNumber) {
        List<Summary> summaries = personService.getSummariesByFireStationNumber(Integer.parseInt(stationNumber));
        return summaries;
    }

    @PostMapping("/medicalRecord")
    public void createMedicalRecord(@RequestBody MedicalRecord medicalRecord) {
        medicalRecordService.createMedicalRecord(medicalRecord);
    }

    @PutMapping("/medicalRecord")
    public void updateMedicalRecord(@RequestBody MedicalRecord medicalRecord) {
        medicalRecordService.updateMedicalRecord(medicalRecord);
    }

    @DeleteMapping("/medicalRecord")
    public void deleteMedicalRecord(@RequestParam String firstName, @RequestParam String lastName) {
        medicalRecordService.deleteMedicalRecord(lastName, firstName);
    }

    @GetMapping("/childAlert")
    public AreaPopulation childAlert(@RequestParam String address) {
        AreaPopulation areaPopulation = null;
        if(address != null) areaPopulation = personService.childAlert(address);
        return areaPopulation;
    }
    @GetMapping("/phoneAlert")
    public List<String> phoneAlert(@RequestParam String firestation) {
        List<String> phones = null;
        if(firestation != null) {
            phones = personService.getPhonesByFireStation(Integer.parseInt(firestation));
        }
        return phones;
    }
    @GetMapping("/fire")
    public List<Summary> fire(@RequestParam String address) {
        List<Summary> summaries = null;
        if(address != null) {
            summaries = personService.getSummariesByAddress(address);
        }
        return summaries;
    }
    @GetMapping("/flood/stations")
    public List<Summary> stations(@RequestParam String stations) {
        List<Summary> summaries = null;
        List<Integer> stationsIntList = new ArrayList<>();
        String refinedInput;
        if(stations != null) {
            refinedInput = stations.replace("(","");
            refinedInput = refinedInput.replace(")", "");
            refinedInput = refinedInput.replace("[", "");
            refinedInput = refinedInput.replace("]", "");
            refinedInput = refinedInput.replace("{", "");
            refinedInput = refinedInput.replace("}", "");
            refinedInput = refinedInput.replace(" ", "");
            for(String station : refinedInput.split(",")) {
                stationsIntList.add(Integer.parseInt(station));
            }
            summaries = personService.getSummariesByStationNumberList(stationsIntList);
        }
        return summaries;
    }
    @GetMapping("/personInfo")
    public List<Summary> personInfo(@RequestParam String firstName, @RequestParam String lastName) {
        List<Summary> summaries = null;
        if(firstName != null && lastName != null) {
            summaries = personService.getSummariesByName(lastName, firstName);
        }
        return summaries;
    }
    @GetMapping("/communityEmail")
    public List<String> communityEmail(@RequestParam String city) {
        List<String> emails = null;
        if(city != null) {
            emails = personService.getEmailsByCity(city);
        }
        return emails;
    }
}
