package com.example.demo.service;

import com.example.demo.model.Car;
import com.example.demo.repository.CarRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class CarService {

    @Autowired
    private CarRepository carRepository;

    // Create a new car
    public Car addCar(Car car) {
        return carRepository.save(car);
    }

    // Get all cars
    public List<Car> getAllCars() {
        return carRepository.findAll();
    }

    // Get car by ID
    public Optional<Car> getCarById(Long id) {
        return carRepository.findById(id);
    }

    // Update car details

    public Car updateCar(Long id, Car carDetails) {
        Car car = carRepository.findById(id).orElseThrow(() -> new RuntimeException("Car not found"));
        car.setCarName(carDetails.getCarName());
        car.setModel(carDetails.getModel());
        car.setYear(carDetails.getYear());
        car.setPrice(carDetails.getPrice());
        car.setColor(carDetails.getColor());
        car.setFuelType(carDetails.getFuelType());
        return car;
    }

    // Delete a car
    public void deleteCar(Long id) {
        carRepository.deleteById(id);
    }


    public List<Car> searchCarsByName(String carName) {
        List<Car> cars = carRepository.findByCarNameContainingIgnoreCase(carName);
        if (cars.isEmpty()) {
            throw new RuntimeException("No such exist car with this name: " + carName);
        }
        return cars;
    }


    public List<Car> searchCarsByModel(String model) {
        List<Car> cars = carRepository.findByModelContainingIgnoreCase(model);
        if (cars.isEmpty()) {
            throw new RuntimeException("No such exist car with this model: " + model);
        }
        return cars;
    }

    public List<Car> searchCarsByColor(String color) {
        List<Car> cars = carRepository.findByColorContainingIgnoreCase(color);
        if (cars.isEmpty()) {
            throw new RuntimeException("No such car exists with the color: " + color);
        }
        return cars;
    }



    public List<Car> searchCars(Car searchRequest) {
        // Use a simple dynamic search based on the provided criteria
        List<Car> results = new ArrayList<>();

        if (searchRequest.getCarName() != null && !searchRequest.getCarName().isEmpty()) {
            results = carRepository.findByCarNameContainingIgnoreCase(searchRequest.getCarName());
        } else if (searchRequest.getModel() != null && !searchRequest.getModel().isEmpty()) {
            results = carRepository.findByModelContainingIgnoreCase(searchRequest.getModel());
        } else if (searchRequest.getYear() != null) {
            results = carRepository.findByYear(searchRequest.getYear());
        } else if (searchRequest.getColor() != null && !searchRequest.getColor().isEmpty()) {
            results = carRepository.findByColorContainingIgnoreCase(searchRequest.getColor());
        } else if (searchRequest.getFuelType() != null && !searchRequest.getFuelType().isEmpty()) {
            results = carRepository.findByFuelTypeContainingIgnoreCase(searchRequest.getFuelType());
        }

        return results;
    }


    // Filter cars by various attributes
//    public List<Car> filterCars(String carName, String model, Integer year) {
//        if (carName != null && !carName.isEmpty()) {
//            return carRepository.findByCarNameContainingIgnoreCase(carName);
//        }
//        if (model != null && !model.isEmpty()) {
//            return carRepository.findByModelContainingIgnoreCase(model);
//        }
//        if (year != null) {
//            return carRepository.findByYear(year);
//        }
//        return carRepository.findAll();  // Return all cars if no filter is provided
//    }
}
