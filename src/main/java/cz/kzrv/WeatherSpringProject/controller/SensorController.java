package cz.kzrv.WeatherSpringProject.controller;

import cz.kzrv.WeatherSpringProject.dto.SensorDTO;
import cz.kzrv.WeatherSpringProject.models.Sensor;
import cz.kzrv.WeatherSpringProject.services.SensorService;
import cz.kzrv.WeatherSpringProject.util.ErrorResponse;
import cz.kzrv.WeatherSpringProject.util.SensorNotCreatedException;
import cz.kzrv.WeatherSpringProject.util.SensorValidation;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Date;

import static cz.kzrv.WeatherSpringProject.util.ErrorsUtil.returnErrorsToClient;

@RestController
@RequestMapping("/sensor")
public class SensorController {
    private final SensorService sensorService;
    private final ModelMapper modelMapper;
    private final SensorValidation sensorValidation;

    @Autowired
    public SensorController(SensorService sensorService, ModelMapper modelMapper, SensorValidation sensorValidation) {
        this.sensorService = sensorService;
        this.modelMapper = modelMapper;
        this.sensorValidation = sensorValidation;
    }

    @PostMapping("/registration")
    public ResponseEntity<HttpStatus> registration(@Valid @RequestBody SensorDTO sensorDTO, BindingResult bindingResult){
        Sensor sensor = convert(sensorDTO);
        sensorValidation.validate(sensor,bindingResult);
        if(bindingResult.hasErrors()){
           throw new SensorNotCreatedException(returnErrorsToClient(bindingResult));
        }
        sensorService.save(sensor);
        return ResponseEntity.ok(HttpStatus.OK);
    }

    @ExceptionHandler
    public ResponseEntity<ErrorResponse> exception(SensorNotCreatedException e){
        ErrorResponse errorResponse = new ErrorResponse(
                e.getMessage(),
                new Date()
        );
        return new ResponseEntity<>(errorResponse,HttpStatus.BAD_REQUEST);

    }

    private Sensor convert(SensorDTO sensorDTO){
        return modelMapper.map(sensorDTO,Sensor.class);
    }

}
