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
    public ResponseEntity<?> createPerson(@RequestBody Person person) {
        ResponseEntity<?> res = null;
        if(personService.createPerson(person)) {
            res = new ResponseEntity<>(HttpStatus.CREATED);
        } else {
            res = new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        return res;
    }
    @PutMapping("/person")
    public ResponseEntity<?> updatePerson(@RequestBody Person person) {
        ResponseEntity<?> res = null;
        if(personService.updatePerson(person)) {
            res = new ResponseEntity<>(HttpStatus.OK);
        } else {
            res = new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        return res;
    }

    @DeleteMapping("/person")
    public ResponseEntity<?> deletePerson(@RequestParam String firstName, @RequestParam String lastName) {
        ResponseEntity<?> res = null;
        if(personService.deletePerson(firstName, lastName)) {
            res = new ResponseEntity<>(HttpStatus.OK);
        } else {
            res = new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        return res;
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
