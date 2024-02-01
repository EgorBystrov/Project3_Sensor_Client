package ru.springcourse.Project3_Sensor.models;

import jakarta.persistence.*;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;

import java.io.Serializable;
import java.util.List;


@Entity
@Table(name = "Sensor")
public class Sensor implements Serializable {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Size(min = 3, max = 30, message = "Name should be between 3 and 30 characters.")
    @NotEmpty(message = "Name should not be empty.")
    @Column(name = "name")
    private String name;

    public Sensor() {
    }

    public Sensor(String name, int age, String email) {
        this.name = name;
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


}
