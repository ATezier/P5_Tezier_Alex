package com.safetynet.api.model;

import com.safetynet.api.repository.DataManager;
import com.safetynet.api.service.MedicalRecordService;
import com.safetynet.api.service.PersonService;
import lombok.Data;

import java.util.Arrays;
import java.util.List;

@Data
public class Summary {
    private String lastName;
    private String firstName;
    private String phone;
    private String address;
    private String city;
    private int zip;
    private String email;
    private int age;
    private String[] medications;
    private String[] allergies;
    private int fireStation;

    public void updateWithPerson(Person person) {
        if(person != null) {
            lastName = person.getLastName();
            firstName = person.getFirstName();
            phone = person.getPhone();
            address = person.getAddress();
            city = person.getCity();
            zip = person.getZip();
            email = person.getEmail();
        }
    }

    public void updateWithMedicalRecord(MedicalRecord medicalRecord) {
        if(medicalRecord != null) {
            age = MedicalRecordService.getAgeFromBirthdate(medicalRecord.getBirthdate());
            medications = medicalRecord.getMedications();
            allergies = medicalRecord.getAllergies();
        }
    }

    public void updateWithFireStation(FireStation fireStation) {
        if(fireStation != null) {
            this.fireStation = fireStation.getStation();
        }
    }

    @Override
    public String toString() {
        return "{" +
                "firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", age='" + age + '\'' +
                ", address=" + address + + '\'' +
                ", city='" + city + '\'' +
                ", zip='" + zip + '\'' +
                ", phone='" + phone + '\'' +
                ", email='" + email + '\'' +
                ", medications='" + Arrays.toString(medications) + '\'' +
                ", allergies='" + Arrays.toString(allergies) + '\'' +
                ", station number='" + fireStation + '\'' +
                '}';
    }
}
