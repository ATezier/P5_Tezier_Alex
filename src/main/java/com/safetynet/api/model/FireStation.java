package com.safetynet.api.model;

import lombok.Data;

@Data
public class FireStation {
    private String address;
    private int station;

    @Override
    public String toString() {
        return "{" +
                ", address=" + address + + '\'' +
                ", station number='" + station + '\'' +
                '}';
    }
}
