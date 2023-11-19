package com.safetynet.api.repository;

import com.safetynet.api.model.MedicalRecord;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class MedicalRecordRepository {
    private List<MedicalRecord> medicalRecords = null;

    private static final Logger logs = LogManager.getLogger(MedicalRecordRepository.class);

    @Autowired
    DataPersistent dataPersistent;

    public MedicalRecord getMedicalRecord(String firstName, String lastName) {
        MedicalRecord medicalRecord = null;
        if(medicalRecords == null) medicalRecords = dataPersistent.getMedicalRecords();
        if (firstName == null || lastName == null) logs.error("Firstname or lastname is null.");
            else {
                medicalRecord = medicalRecords.stream()
                        .filter(m -> m.getFirstName().equals(firstName) && m.getLastName().equals(lastName))
                        .findAny()
                        .orElse(null);
            if (medicalRecord == null) logs.debug("MedicalRecord doesn't exist.");
        }
        return medicalRecord;
    }

    public List<MedicalRecord> getMedicalRecords() {
        if(medicalRecords == null) medicalRecords = dataPersistent.getMedicalRecords();
        return medicalRecords;
    }
    public boolean createMedicalRecord(MedicalRecord medicalRecord) {
        boolean res = false;
            if(getMedicalRecord(medicalRecord.getFirstName(), medicalRecord.getLastName()) == null) {
            res = medicalRecords.add(medicalRecord);
            dataPersistent.saveMedicalRecords(medicalRecords);
            if(res) logs.debug(medicalRecord.getFirstName()+" "+medicalRecord.getLastName()+"'s medical record is successfully created.");
            else logs.error("Fail to create.");
        }
        return res;
    }

    public boolean updateMedicalRecord(MedicalRecord medicalRecord) {
        boolean res = false;
        if(getMedicalRecord(medicalRecord.getFirstName(), medicalRecord.getLastName()) != null) {
            for(MedicalRecord m : medicalRecords) {
                if(m.getFirstName().equals(medicalRecord.getFirstName()) &&
                        m.getLastName().equals(medicalRecord.getLastName())) {
                    if(m.equals(medicalRecord)) logs.debug("Nothing to update.");
                    else {
                        m.setBirthdate(medicalRecord.getBirthdate());
                        m.setMedications(medicalRecord.getMedications());
                        m.setAllergies(medicalRecord.getAllergies());
                        dataPersistent.saveMedicalRecords(medicalRecords);
                        res = true;
                        break;
                    }
                }
            }
            if(res) logs.debug(medicalRecord.getFirstName()+" "+medicalRecord.getLastName()+"'s medical record is successfully updated.");
            else logs.error("Fail to update.");
        }
        return res;
    }

    public boolean deleteMedicalRecord(String firstName, String lastName) {
        boolean res = false;
        MedicalRecord medicalRecord = getMedicalRecord(firstName, lastName);
        if(medicalRecord != null) {
            res = medicalRecords.remove(medicalRecord);
            dataPersistent.saveMedicalRecords(medicalRecords);
            if(res) logs.debug(medicalRecord.getFirstName()+" "+medicalRecord.getLastName()+"'s medical record is successfully deleted.");
            else logs.error("Fail to delete.");
        }
        return res;
    }
}
