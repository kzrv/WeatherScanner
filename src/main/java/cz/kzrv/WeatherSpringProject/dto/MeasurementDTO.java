package cz.kzrv.WeatherSpringProject.dto;

import cz.kzrv.WeatherSpringProject.models.Sensor;

import javax.validation.constraints.*;


public class MeasurementDTO {
    @Min(-100)
    @Max(100) //message = "Temperature got to be between -100 and 100"
    @NotNull
    private Double temperature;
    @NotNull(message = "Rain is unknown")
    private Boolean rain;
    @NotNull(message = "Sensor is empty")
    private SensorDTO owner;

    public MeasurementDTO() {
    }

    public double getTemperature() {
        return temperature;
    }

    public void setTemperature(double temperature) {
        this.temperature = temperature;
    }

    public Boolean getRain() {
        return rain;
    }

    public void setRain(Boolean rain) {
        this.rain = rain;
    }


    public SensorDTO getOwner() {
        return owner;
    }

    public void setOwner(SensorDTO owner) {
        this.owner = owner;
    }
}
