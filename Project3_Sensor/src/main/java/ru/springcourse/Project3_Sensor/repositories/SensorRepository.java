package ru.springcourse.Project3_Sensor.repositories;



import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.springcourse.Project3_Sensor.models.Sensor;

import java.util.List;


@Repository
public interface SensorRepository extends JpaRepository<Sensor, Integer> {
    List <Sensor> findSensorByName(String name);
}
