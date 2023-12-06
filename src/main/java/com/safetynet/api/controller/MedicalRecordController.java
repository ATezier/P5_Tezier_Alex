package com.safetynet.api.controller;

import com.safetynet.api.model.MedicalRecord;
import com.safetynet.api.service.MedicalRecordService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class MedicalRecordController {
    @Autowired
    MedicalRecordService medicalRecordService;


    @PostMapping("/medicalRecord")
    public ResponseEntity<?> createMedicalRecord(@RequestBody MedicalRecord medicalRecord) {
        ResponseEntity<?> res = null;
        if(medicalRecordService.createMedicalRecord(medicalRecord)) {
            res = new ResponseEntity<>(HttpStatus.CREATED);
        } else {
            res = new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        return res;
    }

    @PutMapping("/medicalRecord")
    public ResponseEntity<?> updateMedicalRecord(@RequestBody MedicalRecord medicalRecord) {
        ResponseEntity<?> res = null;
        if(medicalRecordService.updateMedicalRecord(medicalRecord)) {
            res = new ResponseEntity<>(HttpStatus.OK);
        } else {
            res = new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        return res;
    }

    @DeleteMapping("/medicalRecord")
    public ResponseEntity<?> deleteMedicalRecord(@RequestParam String firstName, @RequestParam String lastName) {
        ResponseEntity<?> res = null;
        if(medicalRecordService.deleteMedicalRecord(firstName, lastName)) {
            res = new ResponseEntity<>(HttpStatus.OK);
        } else {
            res = new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        return res;
    }
}
