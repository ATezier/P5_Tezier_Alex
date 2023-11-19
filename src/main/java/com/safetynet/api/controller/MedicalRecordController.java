package com.safetynet.api.controller;

import com.safetynet.api.model.MedicalRecord;
import com.safetynet.api.service.MedicalRecordService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
public class MedicalRecordController {
    @Autowired
    MedicalRecordService medicalRecordService;


    @PostMapping("/medicalRecord")
    public void createMedicalRecord(@RequestBody MedicalRecord medicalRecord) {
        medicalRecordService.createMedicalRecord(medicalRecord);
    }

    @PutMapping("/medicalRecord")
    public void updateMedicalRecord(@RequestBody MedicalRecord medicalRecord) {
        medicalRecordService.updateMedicalRecord(medicalRecord);
    }

    @DeleteMapping("/medicalRecord")
    public void deleteMedicalRecord(@RequestParam String firstName, @RequestParam String lastName) {
        medicalRecordService.deleteMedicalRecord(lastName, firstName);
    }
}
