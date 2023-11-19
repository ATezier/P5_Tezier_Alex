package com.safetynet.api.controller;

import com.safetynet.api.dto.Summary;
import com.safetynet.api.service.SummaryService;
import org.springframework.beans.factory.annotation.Autowired;
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
    public List<Summary> firestation(@RequestParam String stationNumber) {
        List<Summary> summaries = summaryService.getSummariesByStation(Integer.parseInt(stationNumber));
        return summaries;
    }
    @GetMapping("/fire")
    public List<Summary> fire(@RequestParam String address) {
        List<Summary> summaries = null;
        if(address != null) {
            summaries = summaryService.getSummariesByAddress(address);
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
            summaries = summaryService.getSummariesByStations(stationsIntList);
        }
        return summaries;
    }
    @GetMapping("/personInfo")
    public List<Summary> personInfo(@RequestParam String firstName, @RequestParam String lastName) {
        List<Summary> summaries = null;
        if(firstName != null && lastName != null) {
            summaries = summaryService.getSummariesByName(lastName, firstName);
        }
        return summaries;
    }

    @GetMapping("/phoneAlert")
    public List<String> phoneAlert(@RequestParam String firestation) {
        List<String> phones = null;
        if(firestation != null) {
            phones = summaryService.getPhonesByFireStation(Integer.parseInt(firestation));
        }
        return phones;
    }
}
