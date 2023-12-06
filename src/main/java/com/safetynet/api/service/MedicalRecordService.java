package com.safetynet.api.service;

import com.safetynet.api.model.MedicalRecord;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.Period;
import java.time.format.DateTimeFormatter;
import java.util.List;

@Service
public interface MedicalRecordService {

    public List<MedicalRecord> getMedicalRecords();
    public MedicalRecord getMedicalRecord(String firstName, String lastName);
    public boolean createMedicalRecord(MedicalRecord medicalRecord);
    public boolean updateMedicalRecord(MedicalRecord medicalRecord);
    public boolean deleteMedicalRecord(String firstName, String lastName);
    public int getAge(String firstName, String lastName);
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
}
