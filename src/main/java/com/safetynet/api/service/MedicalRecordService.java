package com.safetynet.api.service;

import com.safetynet.api.model.MedicalRecord;
import com.safetynet.api.repository.MedicalRecordRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.Period;
import java.time.format.DateTimeFormatter;
import java.util.List;

@Service
public class MedicalRecordService {
    @Autowired
    MedicalRecordRepository medicalRecordRepository;

    public List<MedicalRecord> getMedicalRecords() { return medicalRecordRepository.getMedicalRecords(); }

    public MedicalRecord getMedicalRecord(String firstName, String lastName) { return medicalRecordRepository.getMedicalRecord(firstName, lastName); }

    public boolean createMedicalRecord(MedicalRecord medicalRecord) { return medicalRecordRepository.createMedicalRecord(medicalRecord); }

    public boolean updateMedicalRecord(MedicalRecord medicalRecord) { return medicalRecordRepository.updateMedicalRecord(medicalRecord); }

    public boolean deleteMedicalRecord(String firstName, String lastName) { return medicalRecordRepository.deleteMedicalRecord(firstName, lastName); }
    public static int getAgeFromBirthdate(String birthdate) {
        int res = 0;
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MM/dd/yyyy");
        LocalDate past = LocalDate.parse(birthdate, formatter);
        LocalDate sysdate = LocalDate.now();
        if(past != null) {
            res = Period.between(past, sysdate).getYears();
        }
        return res;
    }

    public int getAge(String firstName, String lastName) {
        int res = 0;
        MedicalRecord medicalRecord;
        medicalRecord = medicalRecordRepository.getMedicalRecord(firstName, lastName);
        if(medicalRecord != null) {
            res = getAgeFromBirthdate(medicalRecord.getBirthdate());
        }
        return res;
    }

}
