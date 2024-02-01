package ru.springcourse.Project3_Sensor.util;

public class SensorNotCreatedException extends RuntimeException{
    public SensorNotCreatedException(String error) {
        super(error);
    }
}
