package com.StudentManagemen.university.model;


public class Student {
    private Long id;
    private String name;
    private String email;
    private String major;
    private int year;

    // Constructor فارغ
    public Student() {}

    // Constructor مع المعاملات
    public Student(Long id, String name, String email, String major, int year) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.major = major;
        this.year = year;
    }

    // Getters and Setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getMajor() {
        return major;
    }

    public void setMajor(String major) {
        this.major = major;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    @Override
    public String toString() {
        return "Student{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", email='" + email + '\'' +
                ", major='" + major + '\'' +
                ", year=" + year +
                '}';
    }
}