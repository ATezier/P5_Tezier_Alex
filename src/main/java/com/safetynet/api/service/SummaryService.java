package com.safetynet.api.service;

import com.safetynet.api.dto.Summary;
import com.safetynet.api.model.FireStation;
import com.safetynet.api.model.MedicalRecord;
import com.safetynet.api.model.Person;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public interface SummaryService {
    public void updateWithPerson(Summary summary, Person person);
    public void updateWithMedicalRecord(Summary summary, MedicalRecord medicalRecord);
    public void updateWithFireStation(Summary summary, FireStation fireStation);
    public Summary getSummaryByName(String firstName, String lastName);
    public Summary getSummaryByPerson(Person p);
    public List<Summary> getSummariesByAddress(String address);
    public List<Summary> getSummariesByStation(int number);
    public List<Summary> getSummariesByStations(List<Integer> stations);
    public  List<Summary> getSummariesByName(String lastName, String firstName);
    public List<String> getPhonesByFireStation(int station);
}
