package cz.kzrv.WeatherSpringProject.dto;

import cz.kzrv.WeatherSpringProject.models.Measurement;

import java.util.List;

public class Response {
    private List<MeasurementDTO> list;

    public Response(List<MeasurementDTO> list) {
        this.list = list;
    }

    public List<MeasurementDTO> getList() {
        return list;
    }

    public void setList(List<MeasurementDTO> list) {
        this.list = list;
    }
}
