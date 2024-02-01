package ru.springcourse.Project3_Sensor.controllers;

import jakarta.validation.Valid;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.*;
import ru.springcourse.Project3_Sensor.dto.MeasurementDTO;
import ru.springcourse.Project3_Sensor.dto.MeasurementResponse;
import ru.springcourse.Project3_Sensor.models.Measurement;
import ru.springcourse.Project3_Sensor.services.MeasurementService;
import ru.springcourse.Project3_Sensor.util.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/measurements")
public class MeasurementController {
    private final MeasurementService measurementService;
    private final MeasurementValidator measurementValidator;
    private final ModelMapper modelMapper;
    @Autowired
    public MeasurementController(MeasurementService measurementService, MeasurementValidator measurementValidator, ModelMapper modelMapper) {
        this.measurementService = measurementService;
        this.measurementValidator = measurementValidator;
        this.modelMapper = modelMapper;
    }
    @PostMapping("/add")
    public ResponseEntity<HttpStatus> addMeasurement(@RequestBody @Valid MeasurementDTO measurementDTO,
                                          BindingResult bindingResult){
        measurementValidator.validate(measurementDTO, bindingResult);
        if (bindingResult.hasErrors()){
            StringBuilder errorMessage = new StringBuilder();
            List<FieldError> errors = bindingResult.getFieldErrors();
            for (FieldError error: errors){
                errorMessage.append(error.getField())
                        .append(" : ").append(error.getDefaultMessage()).append(";");
            }
            throw new MeasurementNotAddedException(errorMessage.toString());
        }
        measurementService.addMeasurement(convertToMeasurement(measurementDTO));
        return ResponseEntity.ok(HttpStatus.OK);
    }
    @GetMapping()
    public MeasurementResponse getMeasurements(){
        return new MeasurementResponse(measurementService.getMeasurements().stream().map(this::convertToMeasurementDTO)
                .collect(Collectors.toList()));
    }
    @GetMapping("/rainyDaysCount")
    public Long getRainyDays(){
        return measurementService.getRainyDays();
    }

    @ExceptionHandler
    private ResponseEntity<MeasurementErrorResponse> handleException(MeasurementNotAddedException exception){
        MeasurementErrorResponse measurementErrorResponse = new MeasurementErrorResponse(
                exception.getMessage(), System.currentTimeMillis()
        );
        return new ResponseEntity<>(measurementErrorResponse, HttpStatus.BAD_REQUEST);
    }
    private Measurement convertToMeasurement(MeasurementDTO measurementDTO) {
        return modelMapper.map(measurementDTO, Measurement.class);
    }
    private MeasurementDTO convertToMeasurementDTO(Measurement measurement) {
        return modelMapper.map(measurement, MeasurementDTO.class);
    }

}
