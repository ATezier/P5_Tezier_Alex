package com.safetynet.api;

import com.safetynet.api.model.AreaPopulation;
import com.safetynet.api.model.Person;
import com.safetynet.api.model.Summary;
import com.safetynet.api.repository.DataManager;
import com.safetynet.api.service.AreaPopulationService;
import com.safetynet.api.service.PersonService;
import com.safetynet.api.service.SummaryService;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.List;

@SpringBootApplication(scanBasePackages = {"com.safetynet.*"})
public class ApiApplication implements CommandLineRunner {
    @Autowired
    PersonService personService;

    public static void main(String[] args) {
        SpringApplication.run(ApiApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
    }
}
