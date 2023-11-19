package com.safetynet.api.controller;

import com.safetynet.api.model.Person;
import com.safetynet.api.service.PersonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class PersonController {
    @Autowired
    PersonService personService;
    @PostMapping("/person")
    public void createPerson(@RequestBody Person person) {
        personService.createPerson(person);
    }
    @PutMapping("/person")
    public void updatePerson(@RequestBody Person person) {
        personService.updatePerson(person);
    }

    @DeleteMapping("/person")
    public void deletePerson(@RequestParam String firstName, @RequestParam String lastName) {
        personService.deletePerson(firstName, lastName);
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
