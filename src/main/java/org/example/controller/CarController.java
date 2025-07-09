package org.example.controller;

import org.example.model.Car;
import org.example.service.CarService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/cars")
public class CarController {
    private final CarService carService;

    public CarController(CarService carService) {
        this.carService = carService;
    }

    @GetMapping
    public ResponseEntity<List<Car>> getAllCars() {
        return ResponseEntity.ok(carService.getAllCars());
    }

    @PostMapping
    public ResponseEntity<Car> createCar(@RequestBody Car car) {
        return ResponseEntity.ok(carService.saveCar(car));
    }

    @GetMapping("/{id}")
    public ResponseEntity<Car> getCarById(@PathVariable Long id) {
        Car car = carService.getCarById(id);
        return car != null ? ResponseEntity.ok(car) : ResponseEntity.notFound().build();
    }

    @PutMapping("/{id}")
    public ResponseEntity<Car> updateCar(@PathVariable Long id, @RequestBody Car carDetails) {
        Car existingCar = carService.getCarById(id);
        if (existingCar == null) {
            return ResponseEntity.notFound().build();
        }
        
        // Update the existing car with new details
        existingCar.setMake(carDetails.getMake());
        existingCar.setModel(carDetails.getModel());
        existingCar.setYear(carDetails.getYear());
        existingCar.setColor(carDetails.getColor());
        existingCar.setPrice(carDetails.getPrice());
        
        Car updatedCar = carService.saveCar(existingCar);
        return ResponseEntity.ok(updatedCar);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteCar(@PathVariable Long id) {
        carService.deleteCar(id);
        return ResponseEntity.noContent().build();
    }
}
