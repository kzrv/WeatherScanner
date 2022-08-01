package cz.kzrv.WeatherSpringProject.dto;

import javax.validation.constraints.NotEmpty;

public class SensorDTO {
    @NotEmpty(message = "Sensor name is empty")
    private String name;

    public SensorDTO() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
