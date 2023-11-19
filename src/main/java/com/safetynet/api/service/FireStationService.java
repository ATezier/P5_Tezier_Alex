package com.safetynet.api.service;

import com.safetynet.api.model.FireStation;
import com.safetynet.api.repository.FireStationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class FireStationService {
    @Autowired
    FireStationRepository fireStationRepository;

    public FireStation getFireStation(String address) { return fireStationRepository.getFireStation(address); }
    public List<FireStation> getFireStations() { return fireStationRepository.getFireStations(); }

    public boolean createFireStation(FireStation fireStation) { return fireStationRepository.createFireStation(fireStation); }

    public boolean updateFireStation(FireStation fireStation) { return fireStationRepository.updateFireStation(fireStation); }

    public boolean deleteFireStation(String address) { return fireStationRepository.deleteFireStation(address); }

    public List<FireStation> getFireStationsByNumber(int number) {
        List<FireStation> res;
        res = getFireStations().stream()
                .filter(fireStation -> fireStation.getStation() == number)
                .collect(Collectors.toList());
        return res;
    }
}
