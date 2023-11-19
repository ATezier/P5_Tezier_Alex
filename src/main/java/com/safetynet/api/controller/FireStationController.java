package com.safetynet.api.controller;

import com.safetynet.api.model.FireStation;
import com.safetynet.api.service.FireStationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
public class FireStationController {
    @Autowired
    FireStationService fireStationService;

    @PostMapping("/firestation")
    public void createFireStation(@RequestBody FireStation fireStation) {
        fireStationService.createFireStation(fireStation);
    }

    @PutMapping("/firestation")
    public void updateFireStation(@RequestBody FireStation fireStation) {
        fireStationService.updateFireStation(fireStation);
    }

    @DeleteMapping("/firestation")
    public void deleteFireStation(@RequestParam String address) {
        fireStationService.deleteFireStation(address);
    }

}
