package com.ra5.projecte3.model;

public class AcademicProfile {

    private String grade;        // ex: "2n DAM"
    private String course;       // ex: "2024-2025"
    private String observations;
    private String status;       // "ACTIVE" / "INACTIVE"

    

    public AcademicProfile() {
    }

    public AcademicProfile(String course, String grade, String observations, String status) {
        this.course = course;
        this.grade = grade;
        this.observations = observations;
        this.status = status;
    }

    public String getGrade() {
        return grade;
    }

    public void setGrade(String grade) {
        this.grade = grade;
    }

    public String getCourse() {
        return course;
    }

    public void setCourse(String course) {
        this.course = course;
    }

    public String getObservations() {
        return observations;
    }

    public void setObservations(String observations) {
        this.observations = observations;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

}
