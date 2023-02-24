package com.safetynet.api.model;

import lombok.Data;

import java.util.Arrays;
import java.util.List;

@Data
public class DataCluster {
    List<Person> persons;
    List<FireStation> firestations;
    List<MedicalRecord> medicalrecords;

    @Override
    public String toString() {
        String res = "Persons :\n";
        res += (persons != null)? persons.toString() : "\nlist is null";
        res += "\nFire stations :\n";
        res += (firestations != null)? firestations.toString() : "\nlist is null";
        res += "\nMedical records :\n";
        res += (medicalrecords != null)? medicalrecords.toString() : "\nlist is null";
        return res;
    }
}
