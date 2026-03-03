package id.ac.ui.cs.advprog.eshop.service;

import id.ac.ui.cs.advprog.eshop.model.Car;
import id.ac.ui.cs.advprog.eshop.repository.CarRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class CarServiceImplTest {

    @Mock
    private CarRepository carRepository;

    @InjectMocks
    private CarServiceImpl carService;

    @Test
    void testCreate() {
        Car car = new Car();
        when(carRepository.create(car)).thenReturn(car);
        Car createdCar = carService.create(car);
        assertEquals(car, createdCar);
    }

    @Test
    void testFindAll() {
        Car car = new Car();
        List<Car> carList = new ArrayList<>();
        carList.add(car);
        Iterator<Car> iterator = carList.iterator();
        when(carRepository.findAll()).thenReturn(iterator);

        List<Car> result = carService.findAll();
        assertEquals(1, result.size());
    }

    @Test
    void testFindById() {
        Car car = new Car();
        when(carRepository.findById("1")).thenReturn(car);
        Car foundCar = carService.findById("1");
        assertEquals(car, foundCar);
    }

    @Test
    void testUpdate() {
        Car car = new Car();
        carService.update("1", car);
        verify(carRepository, times(1)).update("1", car);
    }

    @Test
    void testDelete() {
        carService.deleteCarById("1");
        verify(carRepository, times(1)).delete("1");
    }
}