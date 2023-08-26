package dat3.car.repository;

import dat3.car.entity.Car;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
public class CarRepositoryTest {
    @Autowired
    CarRepository carRepository;

    boolean isInitialized = false;

    @BeforeEach
    void setUp() {
        if (!isInitialized) {
            carRepository.deleteAll();
            carRepository.save(new Car("Toyota", "Camry", 45.0, 10));
            carRepository.save(new Car("Ford", "Mustang", 60.0, 5));
            carRepository.save(new Car("Honda", "Civic", 40.0, 15));
            carRepository.save(new Car("Chevrolet", "Impala", 55.0, 8));
        }
    }

    @Test
    public void countAll() {
        assertEquals(4,carRepository.count());
    }

    @Test
    public void deleteAll() {
        carRepository.deleteAll();
        assertEquals(0,carRepository.count());
    }

    @Test
    public void findByBrand() {
        Car car1 = carRepository.findByBrand("Toyota");
        assertEquals("Toyota",car1.getBrand());
    }



}
