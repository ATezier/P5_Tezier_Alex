package com.safetynet.api.controller;

import com.safetynet.api.model.FireStation;
import com.safetynet.api.service.FireStationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class FireStationController {
    @Autowired
    FireStationService fireStationService;

    @PostMapping("/firestation")
    public ResponseEntity<?> createFireStation(@RequestBody FireStation fireStation) {
        ResponseEntity<?> res = null;
        if(fireStationService.createFireStation(fireStation)) {
            res = new ResponseEntity<>(HttpStatus.CREATED);
        } else {
            res = new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        return res;
    }

    @PutMapping("/firestation")
    public ResponseEntity<?> updateFireStation(@RequestBody FireStation fireStation) {
        ResponseEntity<?> res = null;
        if(fireStationService.updateFireStation(fireStation)) {
            res = new ResponseEntity<>(HttpStatus.OK);
        } else {
            res = new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        return res;
    }

    @DeleteMapping("/firestation")
    public ResponseEntity<?> deleteFireStation(@RequestParam String address) {
        ResponseEntity<?> res = null;
        if(fireStationService.deleteFireStation(address)) {
            res = new ResponseEntity<>(HttpStatus.OK);
        } else {
            res = new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        return res;
    }

}
