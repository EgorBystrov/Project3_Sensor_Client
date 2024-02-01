package ru.springcourse.Project3_Sensor.util;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;
import ru.springcourse.Project3_Sensor.dto.MeasurementDTO;
import ru.springcourse.Project3_Sensor.models.Measurement;
import ru.springcourse.Project3_Sensor.services.SensorService;

@Component
public class MeasurementValidator implements Validator {
    private final SensorService sensorService;
    @Autowired
    public MeasurementValidator(SensorService sensorService) {
        this.sensorService = sensorService;
    }

    @Override
    public boolean supports(Class<?> clazz) {
        return Measurement.class.equals(clazz);
    }

    @Override
    public void validate(Object target, Errors errors) {
        MeasurementDTO measurementDTO = (MeasurementDTO) target;
        if (sensorService.findSensorByName(measurementDTO.getSensor().getName()).isEmpty())
            errors.rejectValue("sensor", "", "A sensor with this name is not registered in the system");

    }
}
