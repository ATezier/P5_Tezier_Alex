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
public class DataManager {
    public DataCluster dataCluster = null;

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

//Person section

    public List<Person> getPersons() {
        if(dataCluster == null) loadData();
        return dataCluster.getPersons();
    }

    public Person getPerson(String firstName, String lastName) {
        Person res;
        res = this.getPersons()
                .stream()
                .filter(person -> person.getFirstName().equals(firstName) &&
                        person.getLastName().equals(lastName))
                .findAny()
                .orElse(null);
        return res;
    }

    public void createPerson(Person newData) {
        if(this.getPerson(newData.getFirstName(), newData.getLastName()) == null) {
            this.getPersons().add(newData);
            logs.debug(newData.getLastName()+" "+newData.getFirstName()+" successfully added.");
        } else {
            logs.error("Error "+newData.getLastName()+" "+newData.getFirstName()+" already exist.");
        }
    }

    public void updatePerson(Person newData) {
        Person target = this.getPerson(newData.getFirstName(), newData.getLastName());
        if(target != null) {
            target.setAddress(newData.getAddress());
            target.setCity(newData.getCity());
            target.setZip(newData.getZip());
            target.setPhone(newData.getPhone());
            target.setEmail(newData.getEmail());
            logs.debug(newData.getLastName()+" "+newData.getFirstName()+"'s data updated successfully.");
        }
        else {
            logs.error(newData.getLastName()+" "+newData.getFirstName()+" doesn't exist.");
        }
    }

    public void deletePerson(String firstName, String lastName) {
        Person target;
        if((target = this.getPerson(firstName, lastName)) != null) {
            this.getPersons().remove(target);
            logs.debug(lastName+" "+firstName+" successfully eradicated.");
        } else {
            logs.error("Error on delete, "+lastName+" "+firstName+" doesn't exist.");
        }
    }
    //End person section
    //Fire station section

    public List<FireStation> getFireStations() {
        if(dataCluster == null) loadData();
        return dataCluster.getFirestations();
    }

    public FireStation getFireStationByAddress(String address) {
        FireStation res;
        res = this.getFireStations().stream()
                .filter(fireStation -> fireStation.getAddress().equals(address))
                .findAny()
                .orElse(null);
        return res;
    }

    public List<FireStation> getFireStationsByNumber(int number) {
        List<FireStation> res;
        res = this.getFireStations().stream()
                .filter(fireStation -> fireStation.getStation() == number)
                .collect(Collectors.toList());
        return res;
    }

    public void createFireStation(FireStation newData) {
        if(this.getFireStationByAddress(newData.getAddress()) == null) {
            this.getFireStations().add(newData);
            logs.debug("Station successfully added.");
        } else {
            logs.error("Error a station at "+newData.getAddress()+" already registered.");
        }
    }

    public void updateFireStationNbr(FireStation newData) {
        FireStation target = getFireStationByAddress(newData.getAddress());
        if(target != null) {
            target.setStation(newData.getStation());
            logs.debug("Station updated successfully.");
        } else {
            logs.error("Error on update station.");
        }
    }

    public void deleteFireStation(String address) {
        FireStation target = this.getFireStationByAddress((address));
        if(target != null) {
            this.getFireStations().remove(target);
            logs.debug("Successfully deleted");
        } else {
            logs.error("The fire station doesn't exist");
        }
    }
    //End fire station section
    //Medical record section

    public List<MedicalRecord> getMedicalRecords() {
        if(dataCluster == null) loadData();
        return dataCluster.getMedicalrecords();
    }

    public MedicalRecord getMedicalRecord(String firstName, String lastName) {
        MedicalRecord res;
        res = this.getMedicalRecords().stream()
                .filter(medicalRecord -> medicalRecord.getLastName().equals(lastName) &&
                        medicalRecord.getFirstName().equals(firstName))
                .findAny()
                .orElse(null);
        return res;
    }

    public void createMedicalRecord(MedicalRecord newData) {
        if(this.getMedicalRecord(newData.getFirstName(), newData.getLastName()) == null) {
            this.getMedicalRecords().add(newData);
            logs.debug("Medical record successfully added.");
        } else {
            logs.error("Error : medical record already exist.");
        }
    }

    public void updateMedicalRecord(MedicalRecord newData) {
        MedicalRecord target;
        if((target = this.getMedicalRecord(newData.getFirstName(), newData.getLastName())) != null) {
            target.setBirthdate(newData.getBirthdate());
            target.setMedications(newData.getMedications());
            target.setAllergies(newData.getAllergies());
            logs.debug("Medical record successfully updated.");
        } else {
            logs.error("Error : "+newData.getLastName()+" "+newData.getFirstName()+" doesn't exist.");
        }
    }

    public void deleteMedicalRecord(String lastName, String firstName) {
        MedicalRecord target;
        if((target = this.getMedicalRecord(firstName, lastName)) != null) {
            this.getMedicalRecords().remove(target);
            logs.debug("Successfully deleted.");
        } else {
            logs.error("Error : "+lastName+" "+firstName+" doesn't exist.");
        }
    }
    //End medical record section
}
