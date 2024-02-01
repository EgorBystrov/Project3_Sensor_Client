package ru.springcourse.Project3_Sensor.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.springcourse.Project3_Sensor.models.Measurement;

@Repository
public interface MeasurementsRepository extends JpaRepository<Measurement, Integer> {
}
