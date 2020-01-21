package com.company;

import com.sun.istack.Nullable;

import javax.persistence.*;

@Entity
@Table(name = "Course")
public class Course {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private int id;

    @Column(name = "cour_code")
    private String cour_code;

    @Column(name = "name")
    private String name;

    @Column(name = "enroll_id")
    private String enroll_id;

    public Course() {
    }

    public Course(String cour_code, String name, String enroll_id) {
        this.cour_code = cour_code;
        this.name = name;
        this.enroll_id = enroll_id;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEnroll_id() {
        return enroll_id;
    }

    public void setEnroll_id(String enroll_id) {
        this.enroll_id = enroll_id;
    }

    public String getCour_code() {
        return cour_code;
    }

    public void setCour_code(String cour_code) {
        this.cour_code = cour_code;
    }
}
