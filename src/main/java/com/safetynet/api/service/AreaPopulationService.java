package com.safetynet.api.service;

import com.safetynet.api.dto.AreaPopulation;
import com.safetynet.api.dto.Summary;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AreaPopulationService {
    @Autowired SummaryService summaryService;
    public AreaPopulation getAreaPopulationByFireStationNumber(int number) {
        AreaPopulation res = null;
        List<Summary> summaries = null;
        summaries = summaryService.getSummariesByStation(number);
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
