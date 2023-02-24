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

@Service
public class AreaPopulationService {
    @Autowired
    SummaryService summaryService;

    public AreaPopulation getAreaPopulationByFireStationNumber(int number) {
        AreaPopulation res = null;
        List<Summary> summaries = null;
        summaries = summaryService.getSummariesByFireStationNumber(number);
        if(!summaries.isEmpty()) res = new AreaPopulation(summaries);
        return res;
    }

    public AreaPopulation getAreaPopulationByAddress(String address) {
        AreaPopulation res = null;
        List<Summary> summaries = null;
        if(address != null) summaries = summaryService.getSummariesByAddress(address);
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
