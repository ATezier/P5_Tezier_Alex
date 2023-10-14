package com.safetynet.api;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.safetynet.api.model.FireStation;
import com.safetynet.api.model.MedicalRecord;
import com.safetynet.api.model.Person;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.mockito.internal.util.reflection.FieldReader;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.ArgumentMatchers.contains;
import static org.mockito.Mockito.verify;
import static org.springframework.http.MediaType.APPLICATION_JSON_UTF8;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
public class DataControllerTest {
    private static Person person;
    private static MedicalRecord medicalRecord;
    private static FireStation fireStation;

    @Autowired
    public MockMvc mockMvc;

    @BeforeAll
    private static void setUp() {
        String[] allergies = {"azertyuiop"};
        String[] medications = {"poiuytreza"};
        person = new Person();
        medicalRecord = new MedicalRecord();
        fireStation = new FireStation();
        person.setFirstName("John");
        person.setLastName("Doe");
        person.setAddress("123 Main St");
        person.setCity("Anytown");
        person.setZip(12345);
        person.setPhone("0123456789");
        person.setEmail("myEmail@mail.com");
        medicalRecord.setFirstName(person.getFirstName());
        medicalRecord.setLastName(person.getLastName());
        medicalRecord.setBirthdate("04/01/1789");
        medicalRecord.setMedications(medications);
        medicalRecord.setAllergies(allergies);
        fireStation.setStation(42);
        fireStation.setAddress("3214 Main St");
    }



    @Test
    public void testCreatePerson() throws Exception {
        String url = "/person";
        ObjectMapper mapper = new ObjectMapper();
        mapper.configure(SerializationFeature.WRAP_ROOT_VALUE, false);
        ObjectWriter ow = mapper.writer().withDefaultPrettyPrinter();
        String requestJson=ow.writeValueAsString(person);

        mockMvc.perform(post(url).contentType(APPLICATION_JSON_UTF8)
                        .content(requestJson))
                .andExpect(status().isOk());
    }


    @Test
    public void testUpdatePerson() throws Exception {
        String url = "/person";
        person.setCity("SomeWhereElse");
        ObjectMapper mapper = new ObjectMapper();
        mapper.configure(SerializationFeature.WRAP_ROOT_VALUE, false);
        ObjectWriter ow = mapper.writer().withDefaultPrettyPrinter();
        String requestJson=ow.writeValueAsString(person);

        mockMvc.perform(put(url).contentType(APPLICATION_JSON_UTF8)
                        .content(requestJson))
                .andExpect(status().isOk());
    }

    @Test
    public void testDeletePerson() throws Exception {
        mockMvc.perform(delete("/person")
                        .param("firstName", person.getFirstName())
                        .param("lastName", person.getLastName()))
                .andExpect(status().isOk());
    }

    @Test
    public void testCreateFireStation() throws Exception {
        String url = "/firestation";
        ObjectMapper mapper = new ObjectMapper();
        mapper.configure(SerializationFeature.WRAP_ROOT_VALUE, false);
        ObjectWriter ow = mapper.writer().withDefaultPrettyPrinter();
        String requestJson=ow.writeValueAsString(fireStation);

        mockMvc.perform(post(url).contentType(APPLICATION_JSON_UTF8)
                        .content(requestJson))
                .andExpect(status().isOk());
    }


    @Test
    public void testUpdateFireStation() throws Exception {
        String url = "/firestation";
        fireStation.setStation(40);
        ObjectMapper mapper = new ObjectMapper();
        mapper.configure(SerializationFeature.WRAP_ROOT_VALUE, false);
        ObjectWriter ow = mapper.writer().withDefaultPrettyPrinter();
        String requestJson=ow.writeValueAsString(fireStation);

        mockMvc.perform(put(url).contentType(APPLICATION_JSON_UTF8)
                        .content(requestJson))
                .andExpect(status().isOk());
    }

    @Test
    public void testDeleteFireStation() throws Exception {
        mockMvc.perform(delete("/firestation")
                        .param("address", fireStation.getAddress()))
                .andExpect(status().isOk());
    }
    @Test
    public void testCreateMedicalRecord() throws Exception {
        String url = "/medicalRecord";
        ObjectMapper mapper = new ObjectMapper();
        mapper.configure(SerializationFeature.WRAP_ROOT_VALUE, false);
        ObjectWriter ow = mapper.writer().withDefaultPrettyPrinter();
        String requestJson=ow.writeValueAsString(medicalRecord);

        mockMvc.perform(post(url).contentType(APPLICATION_JSON_UTF8)
                        .content(requestJson))
                .andExpect(status().isOk());
    }

    @Test
    public void testUpdateMedicalRecord() throws Exception {
        String url = "/medicalRecord";
        medicalRecord.setBirthdate("04/01/1790");
        ObjectMapper mapper = new ObjectMapper();
        mapper.configure(SerializationFeature.WRAP_ROOT_VALUE, false);
        ObjectWriter ow = mapper.writer().withDefaultPrettyPrinter();
        String requestJson=ow.writeValueAsString(medicalRecord);

        mockMvc.perform(put(url).contentType(APPLICATION_JSON_UTF8)
                        .content(requestJson))
                .andExpect(status().isOk());
    }

    @Test
    public void testDeleteMedicalRecord() throws Exception {
        mockMvc.perform(delete("/person")
                        .param("firstName", medicalRecord.getFirstName())
                        .param("lastName", medicalRecord.getLastName()))
                .andExpect(status().isOk());
    }

    @Test
    public void testFireStation() throws Exception {
        mockMvc.perform(get("/firestation")
                        .param("stationNumber", "2"))
                .andExpect(status().isOk());
    }
    @Test
    public void testChildAlert() throws Exception {
        mockMvc.perform(get("/childAlert")
                .param("address", person.getAddress()))
                .andExpect(status().isOk());
    }

    @Test
    public void testPhoneAlert() throws Exception {
        mockMvc.perform(get("/phoneAlert")
                        .param("firestation", "2"))
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
    public void testCommunityEmail() throws Exception {
        mockMvc.perform(get("/communityEmail")
                        .param("city", person.getCity()))
                .andExpect(status().isOk());
    }
}
