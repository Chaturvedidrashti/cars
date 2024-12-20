package com.example.demo.controller;

import com.example.demo.model.Car;
import com.example.demo.service.CarService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;


@RestController
@RequestMapping("/api/cars")
@Validated
public class CarController {

    @Autowired
    private CarService carService;

    // Create a new car

    @PostMapping


    public ResponseEntity<Car> addCar(@RequestBody Car car) {
        Car newCar = carService.addCar(car);
        return new ResponseEntity<>(newCar, HttpStatus.CREATED);
    }

    // Get all cars
    @GetMapping
    public List<Car> getAllCars() {
        return carService.getAllCars();
    }

    // Get car by ID
    @GetMapping("/{id}")
    public ResponseEntity<Car> getCarById(@PathVariable Long id) {
        Optional<Car> car = carService.getCarById(id);
        return car.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    // Update car details
    @PutMapping("/{id}")
    public ResponseEntity<Car> updateCar(@PathVariable Long id, @RequestBody Car carDetails) {
        Car updatedCar = carService.updateCar(id, carDetails);
        return ResponseEntity.ok(updatedCar);
    }

    // Delete a car
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteCar(@PathVariable Long id) {
        carService.deleteCar(id);
        return ResponseEntity.noContent().build();
    }

    @PostMapping("/searchByName")
    public ResponseEntity<?> searchCarsByName(@RequestBody Car searchRequest) {
        try {
            List<Car> cars = carService.searchCarsByName(searchRequest.getCarName());
            return ResponseEntity.ok(cars);
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }
    }

    @PostMapping("/searchByModel")
    public ResponseEntity<?> searchCarsByModel(@RequestBody Car searchRequest) {
        try {
            List<Car> cars = carService.searchCarsByModel(searchRequest.getModel());
            return ResponseEntity.ok(cars);
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }
    }
    @PostMapping("/searchByColor")
    public ResponseEntity<?> searchCarsByColor(@RequestBody Car searchRequest) {
        if (searchRequest.getColor() == null || searchRequest.getColor().isEmpty()) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Color is required to search.");
        }

        List<Car> cars = carService.searchCarsByColor(searchRequest.getColor());

        if (cars.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("No cars found with the specified color.");
        }

        return ResponseEntity.ok(cars);
    }


    @PostMapping("/Advancesearch")
    public ResponseEntity<?> searchCars(@RequestBody Car searchRequest) {
        List<Car> cars = carService.searchCars(searchRequest);

        if (cars.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("No cars found with the provided details");
        }

        return ResponseEntity.ok(cars);
    }

}
