package ru.springcourse.Project3_Sensor.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.springcourse.Project3_Sensor.models.Measurement;
import ru.springcourse.Project3_Sensor.repositories.MeasurementsRepository;

import java.time.LocalDateTime;
import java.util.Collections;
import java.util.List;

@Service
@Transactional(readOnly = true)
public class MeasurementService {
    private final MeasurementsRepository measurementsRepository;
    private final SensorService sensorService;
    @Autowired
    public MeasurementService(MeasurementsRepository measurementsRepository, SensorService sensorService) {
        this.measurementsRepository = measurementsRepository;
        this.sensorService = sensorService;
    }
    @Transactional
    public void addMeasurement(Measurement measurement){
        enrichMeasurement(measurement);
        measurementsRepository.save(measurement);
    }
    public void enrichMeasurement(Measurement measurement){
        measurement.setSensor(sensorService.findSensorByName(measurement.getSensor().getName()).get());
        measurement.setUpdated_at(LocalDateTime.now());
    }
    public List<Measurement> getMeasurements(){
        return measurementsRepository.findAll();
    }
    public Long getRainyDays(){
        List<Measurement> measurementList = measurementsRepository.findAll();
        Long count = 0L;
        for (Measurement measurement: measurementList){
            if (measurement.isRaining() == true)
                count++;
        }
        return count;
    }
}
