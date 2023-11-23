package com.safetynet.api.service;

import com.safetynet.api.model.FireStation;
import com.safetynet.api.repository.FireStationRepository;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.BDDMockito.given;

@SpringBootTest
@AutoConfigureMockMvc
public class FireStationServiceTest {
    private static FireStation fireStation;
    @MockBean
    FireStationRepository fireStationRepository;
    @Autowired
    FireStationService fireStationService;

    @BeforeAll
    public static void setup() {
        fireStation = new FireStation();
        fireStation.setStation(9);
        fireStation.setAddress("1510 Culver St");
    }

    @Test
    public void getFireStationTest() {
        given(fireStationRepository.getFireStation(anyString())).willReturn(fireStation);
        assertNotNull(fireStationService.getFireStation(fireStation.getAddress()));
    }

    @Test
    public void getFireStationsTest() {
        List<FireStation> fireStations = List.of(fireStation);
        given(fireStationRepository.getFireStations()).willReturn(fireStations);
        assertFalse(fireStationService.getFireStations().isEmpty());
    }

    @Test
    public void createFireStationTest() {
        given(fireStationRepository.createFireStation(fireStation)).willReturn(true);
        assertTrue(fireStationService.createFireStation(fireStation));
    }

    @Test
    public void updateFireStationTest() {
        given(fireStationRepository.updateFireStation(fireStation)).willReturn(true);
        assertTrue(fireStationService.updateFireStation(fireStation));
    }

    @Test
    public void deleteFireStationTest() {
        given(fireStationRepository.deleteFireStation(anyString())).willReturn(true);
        assertTrue(fireStationService.deleteFireStation(fireStation.getAddress()));
    }

    @Test
    public void getFireStationsByNumberTest() {
        List<FireStation> fireStations = List.of(fireStation);
        given(fireStationRepository.getFireStations()).willReturn(fireStations);
        assertFalse(fireStationService.getFireStationsByNumber(fireStation.getStation()).isEmpty());
    }
}
