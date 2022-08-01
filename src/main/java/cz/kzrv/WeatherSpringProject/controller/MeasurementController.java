package cz.kzrv.WeatherSpringProject.controller;

import cz.kzrv.WeatherSpringProject.dto.MeasurementDTO;
import cz.kzrv.WeatherSpringProject.dto.Response;
import cz.kzrv.WeatherSpringProject.models.Measurement;
import cz.kzrv.WeatherSpringProject.services.MeasurementService;
import cz.kzrv.WeatherSpringProject.util.ErrorResponse;
import cz.kzrv.WeatherSpringProject.util.MeasurementNotAddedException;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/measurement")
public class MeasurementController {
    private final MeasurementService measurementService;
    private final ModelMapper modelMapper;

    @Autowired
    public MeasurementController(MeasurementService measurementService, ModelMapper modelMapper) {
        this.measurementService = measurementService;
        this.modelMapper = modelMapper;
    }

    @PostMapping("/add")
    public ResponseEntity<HttpStatus> add(@RequestBody @Valid MeasurementDTO measurementDTO, BindingResult bindingResult){
        if(bindingResult.hasErrors()){
            throw new MeasurementNotAddedException(returnErrorsToClient(bindingResult));
        }
        measurementService.save(convert(measurementDTO));
        return ResponseEntity.ok(HttpStatus.OK);
    }

    @GetMapping()
    public Response returnAll(){
        return new Response(measurementService.list().stream().map(this::convert).collect(Collectors.toList()));
    }
    @GetMapping("/rainDays")
    public Long rainyDays(){
        return measurementService.list().stream().filter(Measurement::getRain).count();
    }

    private Measurement convert(MeasurementDTO measurementDTO) {
        return modelMapper.map(measurementDTO,Measurement.class);
    }
    private MeasurementDTO convert(Measurement measurement){
        return modelMapper.map(measurement,MeasurementDTO.class);
    }
    private String returnErrorsToClient(BindingResult bindingResult) {
        StringBuilder errorMsg = new StringBuilder();
        List<FieldError> errors = bindingResult.getFieldErrors();
        for (FieldError error : errors) {
            errorMsg.append(error.getField())
                    .append(" - ").append(error.getDefaultMessage() == null ? error.getCode() : error.getDefaultMessage())
                    .append(";");
        }
        return errorMsg.toString();
    }
    @ExceptionHandler
    public ResponseEntity<ErrorResponse> exception(MeasurementNotAddedException e){
        ErrorResponse errorResponse = new ErrorResponse(
                e.getMessage(),
                new Date()
        );
        return new ResponseEntity<>(errorResponse,HttpStatus.BAD_REQUEST);
    }
}
