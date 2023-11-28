package com.safetynet.api.controller;

import com.safetynet.api.model.MedicalRecord;
import com.safetynet.api.service.MedicalRecordService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
public class MedicalRecordController {
    @Autowired
    MedicalRecordService medicalRecordService;


    @PostMapping("/medicalRecord")
    public HttpStatus createMedicalRecord(@RequestBody MedicalRecord medicalRecord) {
        HttpStatus status = null;
        if(medicalRecordService.createMedicalRecord(medicalRecord)) {
            status = HttpStatus.CREATED;
        } else {
            status = HttpStatus.BAD_REQUEST;
        }
        return status;
    }

    @PutMapping("/medicalRecord")
    public HttpStatus updateMedicalRecord(@RequestBody MedicalRecord medicalRecord) {
        HttpStatus status = null;
        if(medicalRecordService.updateMedicalRecord(medicalRecord)) {
            status = HttpStatus.OK;
        } else {
            status = HttpStatus.BAD_REQUEST;
        }
        return status;
    }

    @DeleteMapping("/medicalRecord")
    public HttpStatus deleteMedicalRecord(@RequestParam String firstName, @RequestParam String lastName) {
        HttpStatus status = null;
        if(medicalRecordService.deleteMedicalRecord(firstName, lastName)) {
            status = HttpStatus.OK;
        } else {
            status = HttpStatus.BAD_REQUEST;
        }
        return status;
    }
}
