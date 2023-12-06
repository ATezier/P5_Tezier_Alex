package com.safetynet.api.service;

import com.safetynet.api.dto.AreaPopulation;
import com.safetynet.api.dto.Summary;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AreaPopulationServiceImpl implements AreaPopulationService {
    private static final Logger logger = org.apache.logging.log4j.LogManager.getLogger(AreaPopulationServiceImpl.class);
    @Autowired
    SummaryServiceImpl summaryService;
    public AreaPopulation getAreaPopulationByFireStationNumber(int number) {
        AreaPopulation res = null;
        List<Summary> summaries = null;
        summaries = summaryService.getSummariesByStation(number);
        if(summaries.isEmpty()) logger.error("No summary found for station number " + number);
        else res = new AreaPopulation(summaries);
        return res;
    }

    public AreaPopulation getAreaPopulationByAddress(String address) {
        AreaPopulation res = null;
        List<Summary> summaries = null;
        if(address == null) logger.error("Address is null");
        else summaries = summaryService.getSummariesByAddress(address);
        if(summaries.isEmpty()) logger.error("No summary found at address " + address);
            else res = new AreaPopulation(summaries);
        return res;
    }

    public  AreaPopulation childAlert(String address) {
        AreaPopulation res = null;
        res = getAreaPopulationByAddress(address);
        if(res != null && res.getChildCount() < 1) {
            logger.error("No child found at address " + address);
            res = null;
        }
        return res;
    }
}
