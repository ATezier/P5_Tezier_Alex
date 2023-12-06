package com.safetynet.api.service;

import com.safetynet.api.dto.AreaPopulation;
import org.springframework.stereotype.Service;

@Service
public interface AreaPopulationService {
    public AreaPopulation getAreaPopulationByFireStationNumber(int number);
    public AreaPopulation getAreaPopulationByAddress(String address);
    public  AreaPopulation childAlert(String address);
}
