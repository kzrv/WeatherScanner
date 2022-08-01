package cz.kzrv.WeatherSpringProject.repositories;

import cz.kzrv.WeatherSpringProject.models.Measurement;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MeasurementRepository extends JpaRepository<Measurement,Integer> {
}
