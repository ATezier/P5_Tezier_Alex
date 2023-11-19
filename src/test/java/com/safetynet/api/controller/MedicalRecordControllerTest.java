package com.safetynet.api.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.safetynet.api.model.FireStation;
import com.safetynet.api.model.MedicalRecord;
import com.safetynet.api.model.Person;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.http.MediaType.APPLICATION_JSON_UTF8;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
public class MedicalRecordControllerTest {
    private static MedicalRecord medicalRecord;
    @Autowired
    public MockMvc mockMvc;
    @BeforeAll
    private static void setUp() {
        String[] allergies = {"azertyuiop"};
        String[] medications = {"poiuytreza"};
        medicalRecord = new MedicalRecord();
        medicalRecord.setFirstName("John");
        medicalRecord.setLastName("Doe");
        medicalRecord.setBirthdate("04/01/1789");
        medicalRecord.setMedications(medications);
        medicalRecord.setAllergies(allergies);
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

}
