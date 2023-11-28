package com.safetynet.api.controller;

import com.safetynet.api.model.Person;
import com.safetynet.api.service.PersonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class PersonController {
    @Autowired
    PersonService personService;
    @PostMapping("/person")
    public HttpStatus createPerson(@RequestBody Person person) {
        HttpStatus status = null;
        if(personService.createPerson(person)) {
            status = HttpStatus.CREATED;
        } else {
            status = HttpStatus.BAD_REQUEST;
        }
        return status;
    }
    @PutMapping("/person")
    public HttpStatus updatePerson(@RequestBody Person person) {
        HttpStatus status = null;
        if(personService.updatePerson(person)) {
            status = HttpStatus.OK;
        } else {
            status = HttpStatus.BAD_REQUEST;
        }
        return status;
    }

    @DeleteMapping("/person")
    public HttpStatus deletePerson(@RequestParam String firstName, @RequestParam String lastName) {
        HttpStatus status = null;
        if(personService.deletePerson(firstName, lastName)) {
            status = HttpStatus.OK;
        } else {
            status = HttpStatus.BAD_REQUEST;
        }
        return status;
    }
    @GetMapping("/communityEmail")
    public ResponseEntity<?> communityEmail(@RequestParam String city) {
        ResponseEntity<List<String>> res = null;
        List<String> emails = null;
        if(city != null) {
            emails = personService.getEmailsByCity(city);
            if(emails.isEmpty()) {
                res = new ResponseEntity<>(HttpStatus.NOT_FOUND);
            } else {
                res = new ResponseEntity<>(emails, HttpStatus.OK);
            }
        } else {
            res = new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        return res;
    }
}
