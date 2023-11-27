package com.example.Intellihealth.model;

public class CovidPatient {
    private String patientUri;
    private String gender;
    private String pneumonia;
    private int age;
    private String diabetes;
    private String copd;
    private String bloodPressure;
    private String otherDisease;
    private String cardiovascular;
    private String obesity;

    public String getPatientUri() {
        return patientUri;
    }

    public String getGender() {
        return gender;
    }

    public String getPneumonia() {
        return pneumonia;
    }

    public int getAge() {
        return age;
    }

    public void setPatientUri(String patientUri) {
        this.patientUri = patientUri;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public void setPneumonia(String pneumonia) {
        this.pneumonia = pneumonia;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public void setDiabetes(String diabetes) {
        this.diabetes = diabetes;
    }

    public void setCopd(String copd) {
        this.copd = copd;
    }

    public void setBloodPressure(String bloodPressure) {
        this.bloodPressure = bloodPressure;
    }

    public void setOtherDisease(String otherDisease) {
        this.otherDisease = otherDisease;
    }

    public void setCardiovascular(String cardiovascular) {
        this.cardiovascular = cardiovascular;
    }

    public void setObesity(String obesity) {
        this.obesity = obesity;
    }

    public void setTobacco(String tobacco) {
        this.tobacco = tobacco;
    }

    public String getDiabetes() {
        return diabetes;
    }

    public String getCopd() {
        return copd;
    }

    public String getBloodPressure() {
        return bloodPressure;
    }

    public String getOtherDisease() {
        return otherDisease;
    }

    public String getCardiovascular() {
        return cardiovascular;
    }

    public String getObesity() {
        return obesity;
    }

    public String getTobacco() {
        return tobacco;
    }

    private String tobacco;
}
