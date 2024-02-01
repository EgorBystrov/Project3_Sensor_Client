package ru.springcourse.Project3_Sensor.dto;

import jakarta.persistence.Column;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.validation.constraints.*;
import ru.springcourse.Project3_Sensor.models.Sensor;

public class MeasurementDTO {

    @NotNull(message = "Value should not be empty")
    @Min(value = -100, message = "Value should be between -100 and 100")
    @Max(value = 100, message = "Value should be between -100 and 100")
    private float value;
    @NotNull
    private boolean raining;
    private SensorDTO sensor;

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

    public SensorDTO getSensor() {
        return sensor;
    }

    public void setSensor(SensorDTO sensor) {
        this.sensor = sensor;
    }
}
