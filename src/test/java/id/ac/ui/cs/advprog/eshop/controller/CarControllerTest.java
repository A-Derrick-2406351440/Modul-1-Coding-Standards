package id.ac.ui.cs.advprog.eshop.controller;

import id.ac.ui.cs.advprog.eshop.model.Car;
import id.ac.ui.cs.advprog.eshop.service.CarService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.ui.Model;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class CarControllerTest {

    @Mock
    private CarService carService;

    @Mock
    private Model model;

    @InjectMocks
    private CarController carController;

    @Test
    void testCreateCarPage() {
        String viewName = carController.createCarPage(model);
        assertEquals("createCar", viewName);
        verify(model).addAttribute(eq("car"), any(Car.class));
    }

    @Test
    void testCreateCarPost() {
        Car car = new Car();
        String viewName = carController.createCarPost(car, model);
        assertEquals("redirect:listCar", viewName);
        verify(carService).create(car);
    }

    @Test
    void testCarListPage() {
        List<Car> cars = new ArrayList<>();
        when(carService.findAll()).thenReturn(cars);
        String viewName = carController.carListPage(model);
        assertEquals("carList", viewName);
        verify(model).addAttribute("cars", cars);
    }

    @Test
    void testEditCarPage() {
        Car car = new Car();
        when(carService.findById("1")).thenReturn(car);
        String viewName = carController.editCarPage("1", model);
        assertEquals("editCar", viewName);
        verify(model).addAttribute("car", car);
    }

    @Test
    void testEditCarPost() {
        Car car = new Car();
        car.setCarId("1");
        String viewName = carController.editCarPost(car, model);
        assertEquals("redirect:listCar", viewName);
        verify(carService).update("1", car);
    }

    @Test
    void testDeleteCar() {
        String viewName = carController.deleteCar("1");
        assertEquals("redirect:listCar", viewName);
        verify(carService).deleteCarById("1");
    }
}