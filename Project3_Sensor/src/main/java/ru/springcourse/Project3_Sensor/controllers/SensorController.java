package ru.springcourse.Project3_Sensor.controllers;

import jakarta.validation.Valid;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.*;
import ru.springcourse.Project3_Sensor.dto.SensorDTO;
import ru.springcourse.Project3_Sensor.models.Sensor;
import ru.springcourse.Project3_Sensor.services.SensorService;
import ru.springcourse.Project3_Sensor.util.SensorErrorResponse;
import ru.springcourse.Project3_Sensor.util.SensorNotCreatedException;
import ru.springcourse.Project3_Sensor.util.SensorValidator;

import java.util.List;


@RestController
@RequestMapping("/sensors")
public class SensorController {
    private final SensorService sensorService;
    private final SensorValidator sensorValidator;
    private final ModelMapper modelMapper;

    @Autowired
    public SensorController(SensorService sensorService, SensorValidator sensorValidator, ModelMapper modelMapper) {
        this.sensorService = sensorService;
        this.sensorValidator = sensorValidator;
        this.modelMapper = modelMapper;
    }

    @PostMapping("/registration")
    public ResponseEntity<HttpStatus> create(@RequestBody @Valid SensorDTO sensorDTO,
                                             BindingResult bindingResult) {
        sensorValidator.validate(sensorDTO, bindingResult);
        if (bindingResult.hasErrors()) {
            StringBuilder errorMessage = new StringBuilder();
            List<FieldError> errors = bindingResult.getFieldErrors();
            for (FieldError error : errors) {
                errorMessage.append(error.getField())
                        .append(" : ").append(error.getDefaultMessage()).append(";");
            }
            throw new SensorNotCreatedException(errorMessage.toString());
        }
        sensorService.save(convertToSensor(sensorDTO));
        return ResponseEntity.ok(HttpStatus.OK);
    }
    @ExceptionHandler
    private ResponseEntity<SensorErrorResponse> handleException(SensorNotCreatedException exception){
        SensorErrorResponse sensorErrorResponse = new SensorErrorResponse(
                exception.getMessage(), System.currentTimeMillis()
        );
        return new ResponseEntity<>(sensorErrorResponse, HttpStatus.BAD_REQUEST);
    }
    private Sensor convertToSensor(SensorDTO sensorDTO) {
        return modelMapper.map(sensorDTO, Sensor.class);
    }

}
