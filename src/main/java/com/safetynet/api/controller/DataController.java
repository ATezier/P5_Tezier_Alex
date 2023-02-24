package com.safetynet.api.controller;

import com.safetynet.api.model.*;
import com.safetynet.api.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.ArrayList;
import java.util.List;

@Controller
public class DataController {
    @Autowired
     PersonService personService;
    @Autowired
     FireStationService fireStationService;
    @Autowired
     MedicalRecordService medicalRecordService;
    @Autowired
    SummaryService summaryService;
    @Autowired
    AreaPopulationService areaPopulationService;
    /**
     * Read - Get all persons
     * @return - An Iterable object of Person full filled
     */
    @GetMapping("/person")
    public String person(Model model) {
        Iterable<Person> persons = personService.getPersons();
        model.addAttribute("persons", persons);
        return "person";
    }

    @GetMapping("/createPerson")
    public String createPerson(Model model) {
        Person p = new Person();
        model.addAttribute("person", p);
        return "formNewPerson";
    }

    @GetMapping("/updatePerson/{firstName}/{lastName}")
    public String updatePerson(@PathVariable("firstName") final String firstName, @PathVariable("lastName") final String lastName, Model model) {
        Person p = personService.getPerson(firstName, lastName);
        model.addAttribute("person", p);
        return "formUpdatePerson";
    }

    @GetMapping("/deletePerson/{firstName}/{lastName}")
    public ModelAndView deletePerson(@PathVariable("firstName") final String firstName, @PathVariable("lastName") final String lastName) {
        personService.deletePerson(firstName, lastName);
        return new ModelAndView("redirect:/person");
    }

    @PostMapping("/savePerson")
    public ModelAndView savePerson(@ModelAttribute Person person) {
        if(personService.getPerson(person.getFirstName(), person.getLastName()) != null) {
            personService.updatePerson(person);
        } else {
            personService.createPerson(person);
        }
        return new ModelAndView("redirect:/person");
    }

    @GetMapping("/firestation")
    public String firestation(@RequestParam(value = "stationNumber", required = false) String stationNumber,
                              Model model) {
        Iterable<?> data = (stationNumber != null) ?
                summaryService.getSummariesByFireStationNumber(Integer.parseInt(stationNumber)) :
                fireStationService.getFireStations();
        model.addAttribute("data", data);
        return "firestation";
    }

    @GetMapping("/createFirestation")
    public String createFirestation(Model model) {
        FireStation f = new FireStation();
        model.addAttribute("fireStation", f);
        return "formNewFirestation";
    }

    @GetMapping("/updateFirestation/{address}")
    public String updateFirestation(@PathVariable("address") final String address, Model model) {
        FireStation f = fireStationService.getFireStationByAddress(address);
        model.addAttribute("fireStation", f);
        return "formUpdateFirestation";
    }

    @GetMapping("/deleteFirestation/{address}")
    public ModelAndView deleteFirestation(@PathVariable("address") final String address) {
        fireStationService.deleteFireStation(address);
        return new ModelAndView("redirect:/firestation");
    }

    @PostMapping("/saveFirestation")
    public ModelAndView saveFirestation(@ModelAttribute FireStation fireStation) {
        if(fireStationService.getFireStationByAddress(fireStation.getAddress()) != null) {
            fireStationService.updateFireStationNbr(fireStation);
        } else {
            fireStationService.createFireStation(fireStation);
        }
        return new ModelAndView("redirect:/firestation");
    }


    @GetMapping("/medicalRecord")
    public String medicalRecord(Model model) {
        Iterable<MedicalRecord> medicalRecords = medicalRecordService.getMedicalRecords();
        model.addAttribute("medicalRecords", medicalRecords);
        return "medicalRecord";
    }

    @GetMapping("/createMedicalRecord")
    public String createMedicalRecord(Model model) {
        MedicalRecord m = new MedicalRecord();
        model.addAttribute("medicalRecord", m);
        return "formNewMedicalRecord";
    }

    @GetMapping("/updateMedicalRecord/{firstName}/{lastName}")
    public String updateMedicalRecord(@PathVariable("firstName") final String firstName, @PathVariable("lastName") final String lastName, Model model) {
        MedicalRecord m = medicalRecordService.getMedicalRecord(firstName, lastName);
        model.addAttribute("medicalRecord", m);
        return "formUpdateMedicalRecord";
    }

    @GetMapping("/deleteMedicalRecord/{firstName}/{lastName}")
    public ModelAndView deleteMedicalRecord(@PathVariable("firstName") final String firstName, @PathVariable("lastName") final String lastName) {
        medicalRecordService.deleteMedicalRecord(lastName, firstName);
        return new ModelAndView("redirect:/medicalRecord");
    }

    @PostMapping("/saveMedicalRecord")
    public ModelAndView saveMedicalRecord(@ModelAttribute MedicalRecord medicalRecord) {
        if(medicalRecordService.getMedicalRecord(medicalRecord.getFirstName(), medicalRecord.getLastName()) != null) {
            medicalRecordService.updateMedicalRecord(medicalRecord);
        } else {
            medicalRecordService.createMedicalRecord(medicalRecord);
        }
        return new ModelAndView("redirect:/medicalRecord");
    }

    @GetMapping("/childAlert")
    public String childAlert(@RequestParam("address") String address, Model model) {
        AreaPopulation areaPopulation = null;
        if(address != null) areaPopulation = areaPopulationService.childAlert(address);
        model.addAttribute("areaPopulation", areaPopulation);
        return "childAlert";
    }
    @GetMapping("/phoneAlert")
    public String phoneAlert(@RequestParam("firestation") String firestation, Model model) {
        Iterable<String> phones = null;
        if(firestation != null) {
            phones = personService.getPhonesByFireStation(Integer.parseInt(firestation));
        }
        model.addAttribute("phones", phones);
        return "phoneAlert";
    }
    @GetMapping("/fire")
    public String fire(@RequestParam("address") String address, Model model) {
        Iterable<Summary> summaries = null;
        if(address != null) {
            summaries = summaryService.getSummariesByAddress(address);
        }
        model.addAttribute("summaries", summaries);
        return "fire";
    }
    @GetMapping("/flood/stations")
    public String stations(@RequestParam("stations") List<String> stations, Model model) {
        Iterable<Summary> summaries = null;
        List<Integer> input = new ArrayList<>();
        if(stations != null) {
            for(String station : stations) {
                input.add(Integer.parseInt(station));
            }
            summaries = summaryService.getSummariesByStationNumberList(input);
        }
        model.addAttribute("summaries", summaries);
        return "stations";
    }
    @GetMapping("/personInfo")
    public String personInfo(@RequestParam("firstName") String firstName, @RequestParam("lastName") String lastName, Model model) {
        Iterable<Summary> summaries = null;
        if(firstName != null && lastName != null) {
            summaries = summaryService.getSummariesByName(lastName, firstName);
        }
        model.addAttribute("summaries", summaries);
        return "personInfo";
    }
    @GetMapping("/communityEmail")
    public String communityEmail(@RequestParam("city") String city, Model model) {
        List<String> emails = null;
        if(city != null) {
            emails = personService.getEmailsByCity(city);
            System.out.println(city + " donc non null");
        }
        model.addAttribute("emails", emails);
        return "communityEmail";
    }
}
