package cz.kzrv.WeatherSpringProject.services;

import cz.kzrv.WeatherSpringProject.models.Measurement;
import cz.kzrv.WeatherSpringProject.repositories.MeasurementRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class MeasurementService {

    private final MeasurementRepository measurementRepository;
    private final SensorService sensorService;

    @Autowired
    public MeasurementService(MeasurementRepository measurementRepository, SensorService sensorService) {
        this.measurementRepository = measurementRepository;
        this.sensorService = sensorService;
    }

    public void save(Measurement measurement) {
        measurement.setAddingDate(new Date());
        measurement.setOwner(sensorService.findByName(measurement.getOwner().getName()));
        measurementRepository.save(measurement);
    }
    public List<Measurement> list(){
        return measurementRepository.findAll();
    }
}
