package com.safetynet.api.repository;

import com.safetynet.api.model.FireStation;
import com.safetynet.api.model.MedicalRecord;
import com.safetynet.api.model.Person;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

@SpringBootTest
@AutoConfigureMockMvc
public class FireStationRepositoryTest {
    private static FireStation fireStation;
    @Autowired
    FireStationRepository fireStationRepository;
    @BeforeAll
    public static void setup() {
        fireStation = new FireStation();
        fireStation.setStation(99);
        fireStation.setAddress("9999 Culver St");
    }
    @Test
    public void getPersonsTest() {
        assertFalse(fireStationRepository.getFireStations().isEmpty());
    }
    @Test
    public void createFireStationTest() {
        assertTrue(fireStationRepository.createFireStation(fireStation));
        fireStationRepository.deleteFireStation(fireStation.getAddress());
    }
    @Test
    public void updateFireStationTest() {
        fireStationRepository.createFireStation(fireStation);
        fireStation.setStation(100);
        assertTrue(fireStationRepository.updateFireStation(fireStation));
        fireStationRepository.deleteFireStation(fireStation.getAddress());
    }
    @Test
    public void deleteFireStationTest() {
        fireStationRepository.createFireStation(fireStation);
        assertTrue(fireStationRepository.deleteFireStation(fireStation.getAddress()));
    }

}
