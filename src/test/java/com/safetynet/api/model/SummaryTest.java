package com.safetynet.api.model;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;


@SpringBootTest
public class SummaryTest {
    private static Person person;
    private static MedicalRecord medicalRecord;
    private static FireStation fireStation;
    private static Summary s;

    @BeforeAll
    private static void setup() {
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
        s = new Summary();
        s.updateWithFireStation(fireStation);
        s.updateWithMedicalRecord(medicalRecord);
        s.updateWithPerson(person);
    }

    @Test
    public void testEqualSameObj() throws Exception{
        assertTrue(s.equals(s));
    }

    @Test
    public void testEqualNull() throws Exception{
        assertFalse(s.equals(null));
    }
}
