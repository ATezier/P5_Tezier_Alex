package com.safetynet.api.controller;

import com.safetynet.api.model.FireStation;
import com.safetynet.api.service.FireStationService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
public class FireStationController {
    @Autowired
    FireStationService fireStationService;

    @PostMapping("/firestation")
    public HttpStatus createFireStation(@RequestBody FireStation fireStation) {
        HttpStatus status = null;
        if(fireStationService.createFireStation(fireStation)) {
            status = HttpStatus.CREATED;
        } else {
            status = HttpStatus.BAD_REQUEST;
        }
        return status;
    }

    @PutMapping("/firestation")
    public HttpStatus updateFireStation(@RequestBody FireStation fireStation) {
        HttpStatus status = null;
        if(fireStationService.updateFireStation(fireStation)) {
            status = HttpStatus.OK;
        } else {
            status = HttpStatus.BAD_REQUEST;
        }
        return status;
    }

    @DeleteMapping("/firestation")
    public HttpStatus deleteFireStation(@RequestParam String address) {
        HttpStatus status = null;
        if(fireStationService.deleteFireStation(address)) {
            status = HttpStatus.OK;
        } else {
            status = HttpStatus.BAD_REQUEST;
        }
        return status;
    }

}
