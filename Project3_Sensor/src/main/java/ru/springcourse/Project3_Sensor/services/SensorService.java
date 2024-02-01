package ru.springcourse.Project3_Sensor.services;


import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.springcourse.Project3_Sensor.models.Sensor;
import ru.springcourse.Project3_Sensor.repositories.SensorRepository;


import java.util.Optional;

@Service
@Transactional(readOnly = true)
public class SensorService {
    public final SensorRepository sensorRepository;

    public SensorService(SensorRepository sensorRepository1) {
        this.sensorRepository = sensorRepository1;

    }
    @Transactional
    public void save(Sensor sensor){
        sensorRepository.save(sensor);
    }
    public Optional<Sensor> findSensorByName(String name){
        return sensorRepository.findSensorByName(name).stream().findAny();
    }
}
