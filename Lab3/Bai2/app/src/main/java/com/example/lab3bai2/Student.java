package com.example.lab3bai2;

public class Student {
    private String mssv;
    private String name;
    private String gender;
    private String dob;
    private String course;
    private String major;
    private double gpa;


    public Student() {}


    public Student(String mssv, String name, String gender, String dob, String course, String major, double gpa) {
        this.mssv = mssv;
        this.name = name;
        this.gender = gender;
        this.dob = dob;
        this.course = course;
        this.major = major;
        this.gpa = gpa;
    }


    public String getMssv() { return mssv; }
    public void setMssv(String mssv) { this.mssv = mssv; }


    public String getName() { return name; }
    public void setName(String name) { this.name = name; }


    public String getGender() { return gender; }
    public void setGender(String gender) { this.gender = gender; }


    public String getDob() { return dob; }
    public void setDob(String dob) { this.dob = dob; }


    public String getCourse() { return course; }
    public void setCourse(String course) { this.course = course; }


    public String getMajor() { return major; }
    public void setMajor(String major) { this.major = major; }


    public double getGpa() { return gpa; }
    public void setGpa(double gpa) { this.gpa = gpa; }
}
