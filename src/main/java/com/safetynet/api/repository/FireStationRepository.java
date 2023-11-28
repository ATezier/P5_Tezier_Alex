package com.safetynet.api.repository;

import com.safetynet.api.model.FireStation;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class FireStationRepository {
    private List<FireStation> firestations = null;

    private static final Logger logs = LogManager.getLogger(FireStationRepository.class);

    @Autowired
    DataPersistent dataPersistent;

    public FireStation getFireStation(String address) {
        FireStation fireStation = null;
        if(firestations == null) firestations = dataPersistent.getFireStations();
        if(address == null) logs.error("Address is null.");
        else {
            fireStation = firestations.stream()
                    .filter(f -> f.getAddress().equals(address))
                    .findAny()
                    .orElse(null);
            if(fireStation == null) logs.debug("FireStation doesn't exist.");
        }
        return fireStation;
    }

    public List<FireStation> getFireStations() {
        if(firestations == null) firestations = dataPersistent.getFireStations();
        if (firestations == null) logs.debug("No FireStation found.");
        return firestations;
    }
    public boolean createFireStation(FireStation fireStation) {
        boolean res = false;
        if(getFireStation(fireStation.getAddress()) == null) {
            res = firestations.add(fireStation);
            dataPersistent.saveFireStations(firestations);
            if(res) logs.debug("FireStation at "+fireStation.getAddress()+" is successfully created.");
            else logs.error("Fail to create.");
        }
        return res;
    }

    public boolean updateFireStation(FireStation fireStation) {
        boolean res = false;
        FireStation target = getFireStation(fireStation.getAddress());
        if(target != null) {
            if(target.equals(fireStation)) logs.debug("Nothing to update.");
            else {
            target.setStation(fireStation.getStation());
            dataPersistent.saveFireStations(firestations);
            res = true;
            if (res) logs.debug("FireStation at " + fireStation.getAddress() + " is successfully updated.");
            else logs.error("Fail to update.");
            }
        }
        return res;
    }

    public boolean deleteFireStation(String address) {
        boolean res = false;
        FireStation fireStation = getFireStation(address);
        if(fireStation != null) {
            res = firestations.remove(fireStation);
            dataPersistent.saveFireStations(firestations);
            if(res) logs.debug("FireStation at "+fireStation.getAddress()+" is successfully deleted.");
            else logs.error("Fail to delete.");
        }
        return res;
    }
}
