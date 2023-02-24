package com.safetynet.api;

import com.fasterxml.jackson.databind.ObjectMapper;
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
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
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
    public void testViewPerson() throws Exception {
        mockMvc.perform(get("/person"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(view().name("person"));
    }

    @Test
    public void testNewPerson() throws Exception {
        mockMvc.perform(post("/savePerson")
                        .param("firstName", person.getFirstName())
                        .param("lastName", person.getLastName())
                        .param("address", person.getAddress())
                        .param("city", person.getCity())
                        .param("phones", person.getPhone())
                        .param("email", person.getEmail()))
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl("/person"))
                .andExpect(content().string(contains(person.getCity())));
    }

    @Test
    public void testDeletePerson() throws Exception {
        mockMvc.perform(get("/deletePerson/{firstName}/{lastName}", person.getFirstName(), person.getLastName()))
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl("/person"));
    }

    @Test
    public void testViewMedicalRecord() throws Exception {
        mockMvc.perform(get("/medicalRecord"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(view().name("medicalRecord"));
    }

    @Test
    public void testNewMedicalRecord() throws Exception {
        mockMvc.perform(post("/saveMedicalRecord")
                        .param("firstName", medicalRecord.getFirstName())
                        .param("lastName", medicalRecord.getLastName())
                        .param("birthdate", medicalRecord.getBirthdate())
                        .param("medications", medicalRecord.getMedications())
                        .param("allergies", medicalRecord.getAllergies()))
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl("/medicalRecord"))
                .andExpect(content().string(contains(medicalRecord.getBirthdate())));
    }

    @Test
    public void testDeleteMedicalRecord() throws Exception {
        mockMvc.perform(get("/deleteMedicalRecord/{firstName}/{lastName}", medicalRecord.getFirstName(), medicalRecord.getLastName()))
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl("/medicalRecord"));
    }


    @Test
    public void testViewFireStation() throws Exception {
        mockMvc.perform(get("/firestation"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(view().name("firestation"));
    }

    @Test
    public void testNewFireStation() throws Exception {
        mockMvc.perform(post("/saveFirestation")
                        .param("address", fireStation.getAddress())
                        .param("station", (String.valueOf(fireStation.getStation()))))
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl("/firestation"))
                .andExpect(content().string(contains(medicalRecord.getBirthdate())));
    }

    @Test
    public void testDeleteFireStation() throws Exception {
        mockMvc.perform(get("/deleteFirestation/{firstName}", fireStation.getAddress()))
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl("/firestation"));
    }
}
