package com.safetynet.api.service;

import com.safetynet.api.dto.Summary;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.BDDMockito.given;

@SpringBootTest
@AutoConfigureMockMvc
public class AreaPopulationServiceTest {
    private static List<Summary> summaries;
    @MockBean
    SummaryService summaryService;
    @Autowired
    AreaPopulationService areaPopulationService;

    @BeforeAll
    public static void setup() {
        summaries = new ArrayList<>();
        Summary summary = new Summary();
        summary.setFirstName("John");
        summary.setLastName("Boyd");
        summary.setAddress("1509 Culver St");
        summary.setAge(36);
        summary.setEmail("test@tester.com");
        summary.setMedications(new String[]{"aznol:60mg", "hydrapermazol:900mg", "pharmacol:5000mg", "terazine:500mg"});
        summary.setAllergies(new String[]{"nillacilan"});
        summaries.add(summary);
    }

    @Test
    public void getAreaPopulationByFireStationNumberTest() {
        given(summaryService.getSummariesByStation(anyInt())).willReturn(summaries);
        assertNotNull(areaPopulationService.getAreaPopulationByFireStationNumber(9));
    }

    @Test
    public void getAreaPopulationByAddress() {
        given(summaryService.getSummariesByAddress(anyString())).willReturn(summaries);
        assertNotNull(areaPopulationService.getAreaPopulationByAddress("1509 Culver St"));
    }

    @Test
    public void childAlertTest() {
        Summary s = new Summary();
        s.setAge(10);
        summaries.add(s);
        given(summaryService.getSummariesByAddress(anyString())).willReturn(summaries);
        assertNotNull(areaPopulationService.childAlert("1509 Culver St"));
    }
}
