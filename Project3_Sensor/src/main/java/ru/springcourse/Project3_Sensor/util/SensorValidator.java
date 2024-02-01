package ru.springcourse.Project3_Sensor.util;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;
import ru.springcourse.Project3_Sensor.dto.SensorDTO;
import ru.springcourse.Project3_Sensor.models.Sensor;
import ru.springcourse.Project3_Sensor.services.SensorService;
@Component
public class SensorValidator implements Validator {
    private final SensorService sensorService;
    @Autowired
    public SensorValidator(SensorService sensorService) {
        this.sensorService = sensorService;
    }

    @Override
    public boolean supports(Class<?> clazz) {
        return Sensor.class.equals(clazz);
    }

    @Override
    public void validate(Object target, Errors errors) {
        SensorDTO sensor = (SensorDTO) target;
        if (sensorService.findSensorByName(sensor.getName()).isPresent())
            errors.rejectValue("name", "", "A sensor with this name already exists");
    }
}
