package ru.springcourse.Project3_Sensor.util;

public class MeasurementNotAddedException extends RuntimeException{
    public MeasurementNotAddedException(String error) {
        super(error);
    }
}
