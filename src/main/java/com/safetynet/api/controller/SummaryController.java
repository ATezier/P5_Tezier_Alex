package com.safetynet.api.controller;

import com.safetynet.api.dto.Summary;
import com.safetynet.api.service.SummaryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
public class SummaryController {
    @Autowired
    SummaryService summaryService;

    @GetMapping("/firestation")
    public ResponseEntity<?> firestation(@RequestParam String stationNumber) {
        ResponseEntity<List<Summary>> res = null;
        List<Summary> summaries = summaryService.getSummariesByStation(Integer.parseInt(stationNumber));
        if(summaries.isEmpty()) {
            res = ResponseEntity.notFound().build();
        } else {
            res = ResponseEntity.ok(summaries);
        }
        return res;
    }
    @GetMapping("/fire")
    public ResponseEntity<?> fire(@RequestParam String address) {
        ResponseEntity<List<Summary>> res = null;
        List<Summary> summaries = null;
        if(address != null) {
            summaries = summaryService.getSummariesByAddress(address);
            if (summaries.isEmpty()) {
                res = ResponseEntity.notFound().build();
            } else {
                res = ResponseEntity.ok(summaries);
            }
        } else {
            res = ResponseEntity.badRequest().build();
        }
        return res;
    }
    @GetMapping("/flood/stations")
    public ResponseEntity<?> stations(@RequestParam String stations) {
        List<Summary> summaries = null;
        ResponseEntity<List<Summary>> res = null;
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
            summaries = summaryService.getSummariesByStations(stationsIntList);
            if(summaries.isEmpty()) {
                res = ResponseEntity.notFound().build();
            } else {
                res = ResponseEntity.ok(summaries);
            }
        }
        return res;
    }
    @GetMapping("/personInfo")
    public ResponseEntity<?> personInfo(@RequestParam String firstName, @RequestParam String lastName) {
        List<Summary> summaries = null;
        ResponseEntity<List<Summary>> res = null;
        if(firstName != null && lastName != null) {
            summaries = summaryService.getSummariesByName(lastName, firstName);
            if(summaries.isEmpty()) {
                res = ResponseEntity.notFound().build();
            } else {
                res = ResponseEntity.ok(summaries);
            }
        } else {
            res = ResponseEntity.badRequest().build();
        }
        return res;
    }

    @GetMapping("/phoneAlert")
    public ResponseEntity<?> phoneAlert(@RequestParam String firestation) {
        ResponseEntity<List<String>> res = null;
        List<String> phones = null;
        if(firestation != null) {
            phones = summaryService.getPhonesByFireStation(Integer.parseInt(firestation));
            if(phones.isEmpty()) {
                res = ResponseEntity.notFound().build();
            } else {
                res = ResponseEntity.ok(phones);
            }
        } else {
            res = ResponseEntity.badRequest().build();
        }
        return res;
    }
}
