package id.ac.ui.cs.advprog.eshop.repository;

import id.ac.ui.cs.advprog.eshop.model.Car;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Iterator;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
class CarRepositoryTest {

    @InjectMocks
    CarRepository carRepository;

    @BeforeEach
    void setUp() {
        //
    }

    @Test
    void testCreateIfIdIsNull() {
        Car car = new Car();
        car.setCarName("Toyota");
        car.setCarColor("Silver");
        car.setCarQuantity(10);

        assertNull(car.getCarId());

        Car createdCar = carRepository.create(car);

        assertNotNull(createdCar.getCarId());
        assertEquals("Toyota", createdCar.getCarName());
    }

    @Test
    void testCreateIfIdIsNotNull() {
        Car car = new Car();
        car.setCarId("existing-id-123");
        car.setCarName("Honda");

        Car createdCar = carRepository.create(car);

        // ID tidak boleh berubah
        assertEquals("existing-id-123", createdCar.getCarId());
    }

    @Test
    void testFindAll() {
        Car car1 = new Car();
        car1.setCarName("Car 1");
        carRepository.create(car1);

        Car car2 = new Car();
        car2.setCarName("Car 2");
        carRepository.create(car2);

        Iterator<Car> carIterator = carRepository.findAll();

        assertTrue(carIterator.hasNext());
        Car savedCar1 = carIterator.next();
        assertEquals(car1.getCarId(), savedCar1.getCarId());

        assertTrue(carIterator.hasNext());
        Car savedCar2 = carIterator.next();
        assertEquals(car2.getCarId(), savedCar2.getCarId());

        assertFalse(carIterator.hasNext());
    }

    @Test
    void testFindByIdFound() {
        Car car = new Car();
        car.setCarName("Suzuki");
        carRepository.create(car);

        Car foundCar = carRepository.findById(car.getCarId());

        assertNotNull(foundCar);
        assertEquals(car.getCarId(), foundCar.getCarId());
        assertEquals("Suzuki", foundCar.getCarName());
    }

    @Test
    void testFindByIdNotFound() {
        Car car = new Car();
        carRepository.create(car);

        Car foundCar = carRepository.findById("non-existent-id");
        assertNull(foundCar);
    }

    @Test
    void testFindByIdInEmptyList() {
        Car foundCar = carRepository.findById("any-id");
        assertNull(foundCar);
    }

    @Test
    void testUpdateFound() {
        Car car = new Car();
        car.setCarName("Mazda");
        car.setCarColor("Red");
        car.setCarQuantity(5);
        carRepository.create(car);

        Car updatedCar = new Car();
        updatedCar.setCarName("Mazda Updated");
        updatedCar.setCarColor("Blue");
        updatedCar.setCarQuantity(10);

        Car result = carRepository.update(car.getCarId(), updatedCar);

        assertNotNull(result);
        assertEquals("Mazda Updated", result.getCarName());
        assertEquals("Blue", result.getCarColor());
        assertEquals(10, result.getCarQuantity());

        Car inRepo = carRepository.findById(car.getCarId());
        assertEquals("Mazda Updated", inRepo.getCarName());
    }

    @Test
    void testUpdateNotFound() {
        Car car = new Car();
        carRepository.create(car);
        Car updatedCar = new Car();
        updatedCar.setCarName("Ghost Car");

        Car result = carRepository.update("non-existent-id", updatedCar);

        assertNull(result);
    }

    @Test
    void testUpdateInEmptyList() {
        Car updatedCar = new Car();
        Car result = carRepository.update("any-id", updatedCar);
        assertNull(result);
    }

    @Test
    void testDeleteFound() {
        Car car = new Car();
        carRepository.create(car);
        assertNotNull(carRepository.findById(car.getCarId()));
        carRepository.delete(car.getCarId());
        assertNull(carRepository.findById(car.getCarId()));
    }

    @Test
    void testDeleteNotFound() {
        Car car = new Car();
        carRepository.create(car);
        assertDoesNotThrow(() -> carRepository.delete("non-existent-id"));
        assertNotNull(carRepository.findById(car.getCarId()));
    }
}