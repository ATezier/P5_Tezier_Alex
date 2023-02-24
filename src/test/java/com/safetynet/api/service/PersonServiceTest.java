package com.safetynet.api.service;

import com.safetynet.api.model.FireStation;
import com.safetynet.api.model.MedicalRecord;
import com.safetynet.api.model.Person;
import org.junit.Assert;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.contains;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
public class PersonServiceTest {

    @Autowired
    PersonService personService;

    @Test
    public void getPersonsByAddressTest() {
        String address = "1509 Culver St";
        assertFalse(personService.getPersonsByAddress(address).isEmpty());
    }

    @Test
    public void getPersonsByAddressTestWrongAddress() {
        String address = "tricked address";
        assertTrue(personService.getPersonsByAddress(address).isEmpty());
    }

    @Test
    public void getEmailsByCityTest() {
        String city = "Culver";
        assertFalse(personService.getEmailsByCity(city).isEmpty());
    }

    @Test
    public void getEmailsByCityTestWrongCity() {
        String city = "trickedCity";
        assertTrue(personService.getEmailsByCity(city).isEmpty());
    }

    @Test
    public void getPhonesByFireStationTest() {
        int station = 1;
        assertFalse(personService.getPhonesByFireStation(station).isEmpty());
    }

    @Test
    public void getPhonesByFireStationTestWrongStation() {
        int station = -1;
        assertTrue(personService.getPhonesByFireStation(station).isEmpty());
    }
}