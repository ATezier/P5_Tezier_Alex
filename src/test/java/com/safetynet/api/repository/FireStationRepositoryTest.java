package com.safetynet.api.repository;

import com.safetynet.api.model.FireStation;
import com.safetynet.api.model.MedicalRecord;
import com.safetynet.api.model.Person;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.BDDMockito.given;

@SpringBootTest
@AutoConfigureMockMvc
public class FireStationRepositoryTest {
    private static List<FireStation> fireStations;
    private static FireStation fireStation;
    @MockBean
    DataPersistent dataPersistent;
    @Autowired
    FireStationRepository fireStationRepository;
    @BeforeAll
    public static void setup() {
        fireStations = new ArrayList<>();
        fireStation = new FireStation();
        fireStation.setStation(99);
        fireStation.setAddress("9999 Culver St");
    }
    @Test
    public void getPersonsTest() {
        given(dataPersistent.getFireStations()).willReturn(fireStations);
        assertNotNull(fireStationRepository.getFireStations());
    }
    @Test
    public void createFireStationTest() {
        given(dataPersistent.getFireStations()).willReturn(fireStations);
        assertTrue(fireStationRepository.createFireStation(fireStation));
        fireStationRepository.deleteFireStation(fireStation.getAddress());
    }
    @Test
    public void updateFireStationTest() {
        given(dataPersistent.getFireStations()).willReturn(fireStations);
        FireStation f = new FireStation();
        fireStationRepository.createFireStation(fireStation);
        f.setAddress(fireStation.getAddress());
        f.setStation(100);
        assertTrue(fireStationRepository.updateFireStation(f));
        fireStationRepository.deleteFireStation(fireStation.getAddress());
    }
    @Test
    public void deleteFireStationTest() {
        given(dataPersistent.getFireStations()).willReturn(fireStations);
        fireStationRepository.createFireStation(fireStation);
        assertTrue(fireStationRepository.deleteFireStation(fireStation.getAddress()));
    }

}
