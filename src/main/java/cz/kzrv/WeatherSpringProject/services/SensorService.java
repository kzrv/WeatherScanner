package cz.kzrv.WeatherSpringProject.services;

import cz.kzrv.WeatherSpringProject.models.Sensor;
import cz.kzrv.WeatherSpringProject.repositories.SensorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class SensorService {
    private final SensorRepository sensorRepository;

    @Autowired
    public SensorService(SensorRepository sensorRepository) {
        this.sensorRepository = sensorRepository;
    }

    public void save(Sensor sensor){
        sensorRepository.save(sensor);
    }

    public Sensor findByName(String name){
        return sensorRepository.findByName(name);
    }
}
