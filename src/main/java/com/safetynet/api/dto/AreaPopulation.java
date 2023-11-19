package com.safetynet.api.dto;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class AreaPopulation {
    private List<Summary> adults;
    private List<Summary> childs;
    private int adultCount;
    private int childCount;

    public AreaPopulation() {
        adultCount = 0;
        childCount = 0;
        adults = null;
        childs = null;
    }

    public AreaPopulation(List<Summary> summaries) {
        adultCount = 0;
        childCount = 0;
        if(summaries == null) {
            adults = null;
            childs = null;
        } else {
            adults = new ArrayList<>();
            childs = new ArrayList<>();
            for(Summary s : summaries) {
                if(s.getAge() > 18) {
                    adults.add(s);
                    adultCount++;
                } else {
                    childs.add(s);
                    childCount++;
                }
            }
        }
    }

    @Override
    public String toString() {
        String res = null;
        res = "adults : \n";
        res += (adults != null)?adults.toString():"list is null";
        res += "\nchilds : \n";
        res += (childs != null)?childs.toString():"list is null";
        res += "\nnumber of adults : "+adultCount;
        res += "\nnumber of childs : "+childCount;
        return res;
    }
}
