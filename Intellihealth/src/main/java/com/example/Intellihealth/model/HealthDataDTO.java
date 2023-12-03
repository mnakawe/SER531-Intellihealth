package com.example.Intellihealth.model;

import lombok.Getter;

public class HealthDataDTO {
    private String age;
    private String height;
    private String weight;
    @Getter
    private String gender;
    private String alcohol;
    private String smoke;
    private String cholesterol;
    private String hypertension;
    private String copd;
    private String diabetes;
    private String muscularProblems;
    private String obesity;
    private String pneumonia;
    private String asthma;


    private  String bloodpressure;

    public String getAge() {
        return age;
    }

    public String getHeight() {
        return height;
    }

    public String getWeight() {
        return weight;
    }

    public String getAlcohol() {
        return alcohol;
    }

    public String getSmoke() {
        return smoke;
    }

    public String getCholesterol() {
        return cholesterol;
    }

    public String getHypertension() {
        return hypertension;
    }

    public String getCopd() {
        return copd;
    }

    public String getDiabetes() {
        return diabetes;
    }

    public String getMuscularProblems() {
        return muscularProblems;
    }

    public String getObesity() {
        return obesity;
    }

    public String getPneumonia() {
        return pneumonia;
    }

    public String getAsthma() {
        return asthma;
    }

    public String getBloodPressure() {
        return bloodpressure;
    }

}