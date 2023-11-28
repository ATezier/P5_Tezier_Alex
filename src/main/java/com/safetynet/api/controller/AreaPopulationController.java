package com.safetynet.api.controller;

import com.safetynet.api.dto.AreaPopulation;
import com.safetynet.api.service.AreaPopulationService;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AreaPopulationController {
    private static final Logger logger = org.apache.logging.log4j.LogManager.getLogger(AreaPopulationController.class);
    @Autowired
    AreaPopulationService areaPopulationService;

    @GetMapping("/childAlert")
    public ResponseEntity<?> childAlert(@RequestParam String address) {
        AreaPopulation areaPopulation = null;
        ResponseEntity<AreaPopulation> res = null;
        if(address != null) {
            areaPopulation = areaPopulationService.childAlert(address);
            res = ResponseEntity.ok(areaPopulation);
        } else {
            logger.warn("No address provided");
            res = ResponseEntity.badRequest().build();
        }
        return res;
    }
}
