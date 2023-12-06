package com.safetynet.api.service;

import com.safetynet.api.model.FireStation;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;
@Service
public interface FireStationService {
    public FireStation getFireStation(String address);
    public List<FireStation> getFireStations();
    public boolean createFireStation(FireStation fireStation);
    public boolean updateFireStation(FireStation fireStation);
    public boolean deleteFireStation(String address);
    public List<FireStation> getFireStationsByNumber(int number);
}
