package ru.springcourse.Project3_Sensor.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jakarta.validation.constraints.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "Measurement")
public class Measurement {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @ManyToOne
    @JoinColumn(name = "sensor_name", referencedColumnName = "name")
    private Sensor sensor;
    @Column(name = "value")
    @NotNull(message = "Value should not be empty")
    @Min(value = -100, message = "Value should be between -100 and 100")
    @Max(value = 100, message = "Value should be between -100 and 100")
    private float value;
    @Column(name = "raining")
    @NotNull
    private boolean raining;
    @Column(name = "updated_at")
    private LocalDateTime updated_at;

    public Measurement(float value, boolean raining) {
        this.value = value;
        this.raining = raining;
    }
    public Measurement(){
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Sensor getSensor() {
        return sensor;
    }

    public void setSensor(Sensor sensor) {
        this.sensor = sensor;
    }

    public float getValue() {
        return value;
    }

    public void setValue(float value) {
        this.value = value;
    }

    public boolean isRaining() {
        return raining;
    }

    public void setRaining(boolean raining) {
        this.raining = raining;
    }

    public LocalDateTime getUpdated_at() {
        return updated_at;
    }

    public void setUpdated_at(LocalDateTime updated_at) {
        this.updated_at = updated_at;
    }
}
