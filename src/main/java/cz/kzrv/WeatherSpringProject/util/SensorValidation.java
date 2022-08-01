package cz.kzrv.WeatherSpringProject.util;

import cz.kzrv.WeatherSpringProject.models.Sensor;
import cz.kzrv.WeatherSpringProject.services.SensorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

@Component
public class SensorValidation implements Validator {
    private final SensorService sensor;

    @Autowired
    public SensorValidation(SensorService sensor) {
        this.sensor = sensor;
    }

    @Override
    public boolean supports(Class<?> clazz) {
        return false;
    }

    @Override
    public void validate(Object target, Errors errors) {
        Sensor sensorClass = (Sensor) target;
        if(sensor.findByName(sensorClass.getName())!=null){
            errors.rejectValue("name","Sensor with that name is already exists");
        }
    }
}
