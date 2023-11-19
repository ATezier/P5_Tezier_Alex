package com.safetynet.api.repository;


import com.fasterxml.jackson.databind.ObjectMapper;
import com.safetynet.api.CustomProperties;
import com.safetynet.api.model.*;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

@Repository
public class DataPersistent {
    private DataCluster dataCluster = null;

    @Autowired
    private ObjectMapper objectMapper;

    private static final Logger logs = LogManager.getLogger(DataCluster.class);

    @Autowired
    private CustomProperties props;

    public DataCluster loadData() {
        try {
            dataCluster = objectMapper.readValue(new File(props.getJsonPath()), DataCluster.class);
            logs.debug("Data are successfully loaded.");
        } catch (IOException e) {
            logs.error("Error on loading Data.");
            throw new RuntimeException(e);
        }
        return dataCluster;
    }

    public void savePersons(List<Person> persons) {
        if(dataCluster != null && persons != null) dataCluster.setPersons(persons);
        try {
            saveData();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
    public void saveFireStations(List<FireStation> fireStations) {
        if(dataCluster != null && fireStations != null) dataCluster.setFirestations(fireStations);
        try {
            saveData();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
    public void saveMedicalRecords(List<MedicalRecord> medicalRecords) {
        if(dataCluster != null && medicalRecords != null) dataCluster.setMedicalrecords(medicalRecords);
        try {
            saveData();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public void saveData() {
        if(dataCluster != null) {
            try {
                System.out.println(dataCluster.toString());
                objectMapper.writeValue(new File(props.getJsonPath()), dataCluster);
                logs.debug("Data have successfully saved.");
            } catch (IOException e) {
                logs.error("Error on saving Data.");
                throw new RuntimeException(e);
            }
        } else {
            logs.error("Error try to save empty Data.");
        }
    }
    public List<Person> getPersons() {
        if(dataCluster == null) loadData();
        return dataCluster.getPersons();
    }

    public List<FireStation> getFireStations() {
        if(dataCluster == null) loadData();
        return dataCluster.getFirestations();
    }

    public List<MedicalRecord> getMedicalRecords() {
        if(dataCluster == null) loadData();
        return dataCluster.getMedicalrecords();
    }

}
