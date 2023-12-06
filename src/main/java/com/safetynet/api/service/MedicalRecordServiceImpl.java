package com.safetynet.api.service;

import com.safetynet.api.model.MedicalRecord;
import com.safetynet.api.repository.MedicalRecordRepository;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.Period;
import java.time.format.DateTimeFormatter;
import java.util.List;

@Service
public class MedicalRecordServiceImpl implements MedicalRecordService {
    public static final Logger logger = org.apache.logging.log4j.LogManager.getLogger(MedicalRecordServiceImpl.class);
    @Autowired
    MedicalRecordRepository medicalRecordRepository;

    public List<MedicalRecord> getMedicalRecords() { return medicalRecordRepository.getMedicalRecords(); }

    public MedicalRecord getMedicalRecord(String firstName, String lastName) { return medicalRecordRepository.getMedicalRecord(firstName, lastName); }

    public boolean createMedicalRecord(MedicalRecord medicalRecord) { return medicalRecordRepository.createMedicalRecord(medicalRecord); }

    public boolean updateMedicalRecord(MedicalRecord medicalRecord) { return medicalRecordRepository.updateMedicalRecord(medicalRecord); }

    public boolean deleteMedicalRecord(String firstName, String lastName) { return medicalRecordRepository.deleteMedicalRecord(firstName, lastName); }

    public int getAge(String firstName, String lastName) {
        int res = 0;
        MedicalRecord medicalRecord;
        medicalRecord = medicalRecordRepository.getMedicalRecord(firstName, lastName);
        if(medicalRecord != null) {
            res = MedicalRecordService.getAgeFromBirthdate(medicalRecord.getBirthdate());
        } else {
            logger.error("MedicalRecord not found for " + firstName + " " + lastName);
        }
        return res;
    }
}
