package com.safetynet.api.controller;

import com.safetynet.api.model.FireStation;
import com.safetynet.api.model.MedicalRecord;
import com.safetynet.api.model.Person;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
public class SummaryControllerTest {
    private static Person person;
    @Autowired
    public MockMvc mockMvc;
    @BeforeAll
    private static void setUp() {
        person = new Person();
        person.setFirstName("John");
        person.setLastName("Doe");
        person.setAddress("123 Main St");
        person.setCity("Anytown");
        person.setZip(12345);
        person.setPhone("0123456789");
        person.setEmail("myEmail@mail.com");
    }
    @Test
    public void testFireStation() throws Exception {
        mockMvc.perform(get("/firestation")
                        .param("stationNumber", "2"))
                .andExpect(status().isOk());
    }


    @Test
    public void testFire() throws Exception {
        mockMvc.perform(get("/fire")
                        .param("address", person.getAddress()))
                .andExpect(status().isOk());
    }


    @Test
    public void testFloodStations() throws Exception {
        mockMvc.perform(get("/flood/stations")
                        .param("stations", "(1, 2, 3)"))
                .andExpect(status().isOk());
    }

    @Test
    public void testPersonInfo() throws Exception {
        mockMvc.perform(get("/personInfo")
                        .param("firstName", person.getFirstName())
                        .param("lastName", person.getLastName()))
                .andExpect(status().isOk());
    }


    @Test
    public void testPhoneAlert() throws Exception {
        mockMvc.perform(get("/phoneAlert")
                        .param("firestation", "2"))
                .andExpect(status().isOk());
    }

}
