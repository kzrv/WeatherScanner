package cz.kzrv.WeatherSpringProject.repositories;

import cz.kzrv.WeatherSpringProject.models.Sensor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SensorRepository extends JpaRepository<Sensor,Integer> {
    Sensor findByName(String name);
}
