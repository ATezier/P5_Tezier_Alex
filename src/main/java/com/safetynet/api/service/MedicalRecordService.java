package com.safetynet.api.service;

import com.safetynet.api.model.MedicalRecord;
import com.safetynet.api.model.Person;
import com.safetynet.api.repository.DataManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.Period;
import java.time.format.DateTimeFormatter;

@Service
public class MedicalRecordService extends DataManager{

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

    public int getAgeFromPerson(Person person) {
        int res = 0;
        MedicalRecord medicalRecord;
        medicalRecord = this.getMedicalRecord(person.getFirstName(), person.getLastName());
        if(medicalRecord != null) {
            res = getAgeFromBirthdate(medicalRecord.getBirthdate());
        }
        return res;
    }

}
