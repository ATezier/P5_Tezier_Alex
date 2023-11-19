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
public class PersonControllerTest {
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
    public void testCommunityEmail() throws Exception {
        mockMvc.perform(get("/communityEmail")
                        .param("city", person.getCity()))
                .andExpect(status().isOk());
    }

}
