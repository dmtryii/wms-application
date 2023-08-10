package com.dmtryii.wms.controller;

import com.dmtryii.wms.dto.response.CityDTO;
import com.dmtryii.wms.exception.CityNotCreatedException;
import com.dmtryii.wms.exception.CityNotUpdatedException;
import com.dmtryii.wms.exception.handle_exception.BadRequestRecorder;
import com.dmtryii.wms.model.City;
import com.dmtryii.wms.service.CityService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("api/cities")
public class CityController {
    private final CityService cityService;
    private final BadRequestRecorder errorRecorder;
    private final ModelMapper modelMapper;

    @GetMapping
    public ResponseEntity<List<CityDTO>> getAllCity() {
        List<CityDTO> cities = cityService.getAllCity()
                .stream()
                .map(this::mapCity)
                .toList();
        return new ResponseEntity<>(cities, HttpStatus.OK);
    }

    @GetMapping("/{city_id}")
    public ResponseEntity<CityDTO> getCityById(@PathVariable(name = "city_id") Long cityId) {
        City city = cityService.getCityById(cityId);
        return new ResponseEntity<>(
                mapCity(city),
                HttpStatus.OK
        );
    }

    @PostMapping
    public ResponseEntity<CityDTO> createCity(@RequestBody @Valid CityDTO cityRequest,
                                              BindingResult bindingResult) {
        if(bindingResult.hasErrors()) {
            String errors = errorRecorder.getRecordedErrors(bindingResult);
            throw new CityNotCreatedException(errors);
        }
        City city = mapCity(cityRequest);
        City cityResponse = cityService.createCity(city);
        return new ResponseEntity<>(
                mapCity(cityResponse),
                HttpStatus.CREATED
        );
    }

    @PutMapping("/{city_id}")
    public ResponseEntity<CityDTO> updateCity(@PathVariable(name = "city_id") Long cityId,
                                           @RequestBody @Valid CityDTO cityRequest,
                                           BindingResult bindingResult) {
        if(bindingResult.hasErrors()) {
            String errors = errorRecorder.getRecordedErrors(bindingResult);
            throw new CityNotUpdatedException(errors);
        }
        City city = mapCity(cityRequest);
        City cityResponse = cityService.updateCity(cityId, city);
        return new ResponseEntity<>(
                mapCity(cityResponse),
                HttpStatus.OK
        );
    }

    @DeleteMapping("/{city_id}")
    public ResponseEntity<HttpStatus> deleteCity(@PathVariable(name = "city_id") Long cityId) {
        cityService.deleteCityById(cityId);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    private CityDTO mapCity(City city) {
        return modelMapper.map(city, CityDTO.class);
    }

    private City mapCity(CityDTO cityDTO) {
        return modelMapper.map(cityDTO, City.class);
    }
}
