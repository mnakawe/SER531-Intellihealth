package com.intellihealth.model;

public class CardioPatient {
    private String patientUri;
    private int age;
    private String gender;
    private String bloodPressure;
    private String cholesterol;
    private String glucose;
    private String smoker;
    private String cardiovascular;

    public String getPatientUri() {
        return patientUri;
    }

    public int getAge() {
        return age;
    }

    public String getGender() {
        return gender;
    }

    public String getBloodPressure() {
        return bloodPressure;
    }

    public String getCholesterol() {
        return cholesterol;
    }

    public String getGlucose() {
        return glucose;
    }

    public String getSmoker() {
        return smoker;
    }

    public String getCardiovascular() {
        return cardiovascular;
    }

    public void setPatientUri(String patientUri) {
        this.patientUri = patientUri;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public void setBloodPressure(String bloodPressure) {
        this.bloodPressure = bloodPressure;
    }

    public void setCholesterol(String cholesterol) {
        this.cholesterol = cholesterol;
    }

    public void setGlucose(String glucose) {
        this.glucose = glucose;
    }

    public void setSmoker(String smoker) {
        this.smoker = smoker;
    }

    public void setCardiovascular(String cardiovascular) {
        this.cardiovascular = cardiovascular;
    }
}
