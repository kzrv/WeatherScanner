package cz.kzrv.WeatherSpringProject.util;

public class MeasurementNotAddedException extends RuntimeException{
    public MeasurementNotAddedException(String msg) {
        super(msg);
    }
}
