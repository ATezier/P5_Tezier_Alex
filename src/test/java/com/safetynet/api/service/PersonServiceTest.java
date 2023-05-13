package com.safetynet.api.service;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

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