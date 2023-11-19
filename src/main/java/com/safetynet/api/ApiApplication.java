package com.safetynet.api;

import com.safetynet.api.model.FireStation;
import com.safetynet.api.model.MedicalRecord;
import com.safetynet.api.model.Person;
import com.safetynet.api.repository.FireStationRepository;
import com.safetynet.api.repository.MedicalRecordRepository;
import com.safetynet.api.repository.PersonRepository;
import com.safetynet.api.service.FireStationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.List;

@SpringBootApplication(scanBasePackages = {"com.safetynet.*"})
public class ApiApplication implements CommandLineRunner {

    @Autowired
    private PersonRepository personRepository;

    @Autowired
    private FireStationService fireStationService;

    @Autowired
    MedicalRecordRepository medicalRecordRepository;
    public static void main(String[] args) {
        SpringApplication.run(ApiApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
        /*
        List<Person> persons = personRepository.getPersons();
        Person p = new Person();
        p.setFirstName("John");
        p.setLastName("Boydy");
        personRepository.createPerson(p);
        p.setEmail("hello");
        personRepository.updatePerson(p);
        FireStation f = new FireStation();
        f.setAddress("hello");
        f.setNumber(30);
        fireStationRepository.createFireStation(f);
        f.setNumber(31);
        fireStationRepository.updateFireStation(f);
        MedicalRecord medicalRecord = new MedicalRecord();
        medicalRecord.setFirstName("John");
        medicalRecord.setLastName("Boydy");
        medicalRecord.setBirthdate("01/01/2000");
        medicalRecordRepository.createMedicalRecord(medicalRecord);
        medicalRecord.setBirthdate("01/01/2001");
        medicalRecordRepository.updateMedicalRecord(medicalRecord);


        personRepository.deletePerson(p.getFirstName(), p.getLastName());
        fireStationRepository.deleteFireStation(f.getAddress());
        medicalRecordRepository.deleteMedicalRecord(medicalRecord.getFirstName(), medicalRecord.getLastName());
*/
    }
}
