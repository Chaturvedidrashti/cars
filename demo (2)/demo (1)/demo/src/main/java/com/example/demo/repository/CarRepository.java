package com.example.demo.repository;

import com.example.demo.model.Car;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface CarRepository extends JpaRepository<Car, Long> {

//    // Custom query for global search across multiple fields
//    @Query("SELECT c FROM Car c WHERE " +
//            "(:name IS NULL OR LOWER(c.carName) LIKE LOWER(CONCAT('%', :name, '%'))) AND " +
//            "(:model IS NULL OR LOWER(c.model) LIKE LOWER(CONCAT('%', :model, '%'))) AND " +
//            "(:year IS NULL OR c.year = :year) AND " +
//            "(:color IS NULL OR LOWER(c.color) LIKE LOWER(CONCAT('%', :color, '%'))) AND " +
//            "(:fuelType IS NULL OR LOWER(c.fuelType) LIKE LOWER(CONCAT('%', :fuelType, '%')))")
//    Page<Car> searchCars(String name, String model, Integer year, String color, String fuelType, Pageable pageable);

//    List<Car> findByCarNameContainingIgnoreCase(String carName);
//
//    List<Car> findByModelContainingIgnoreCase(String model);
//
//    List<Car> findByYear(Integer year);

    List<Car> findByCarNameContainingIgnoreCase(String carName);
    public List<Car> findByModelContainingIgnoreCase(String model);
    public List<Car> findByYear(Integer year);
    public List<Car> findByColorContainingIgnoreCase(String color);
    public List<Car> findByFuelTypeContainingIgnoreCase(String fuelType);

}
