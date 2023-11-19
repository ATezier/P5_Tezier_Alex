package com.safetynet.api.dto;

import com.safetynet.api.model.FireStation;
import com.safetynet.api.model.MedicalRecord;
import com.safetynet.api.model.Person;
import com.safetynet.api.service.MedicalRecordService;
import lombok.Data;

import java.util.Arrays;

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
    private int station;

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
                ", station number='" + station + '\'' +
                '}';
    }
}
