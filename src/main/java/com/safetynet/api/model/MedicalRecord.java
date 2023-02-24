package com.safetynet.api.model;

import lombok.Data;

import java.util.Arrays;

@Data
public class MedicalRecord {
    private String firstName;
    private String lastName;
    private String birthdate;
    private String[] medications;
    private String[] allergies;

    @Override
    public String toString() {
        return "{" +
                "firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", birhdate=" + birthdate + + '\'' +
                ", medications='" + Arrays.toString(medications) + '\'' +
                ", allergies='" + Arrays.toString(allergies) + '\'' +
                '}';
    }
}
