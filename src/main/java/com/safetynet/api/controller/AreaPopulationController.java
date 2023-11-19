package com.safetynet.api.controller;

import com.safetynet.api.dto.AreaPopulation;
import com.safetynet.api.service.AreaPopulationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AreaPopulationController {
    @Autowired
    AreaPopulationService areaPopulationService;

    @GetMapping("/childAlert")
    public AreaPopulation childAlert(@RequestParam String address) {
        AreaPopulation areaPopulation = null;
        if(address != null) areaPopulation = areaPopulationService.childAlert(address);
        return areaPopulation;
    }
}
