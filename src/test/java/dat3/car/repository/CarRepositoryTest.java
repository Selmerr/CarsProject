package dat3.car.repository;

import dat3.car.entity.Car;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.List;

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
            carRepository.save(new Car("Toyota", "Camry", 55.0, 8));
            carRepository.save(new Car("Honda", "Civic", 40.0, 15));
            Car cartest = carRepository.save(new Car("Toyota", "Fisk", 50.0, 8));



        }
    }

    @Test
    public void countAll() {
        assertEquals(5,carRepository.count());
    }

    @Test
    public void deleteAll() {
        carRepository.deleteAll();
        assertEquals(0,carRepository.count());
    }

    @Test
    public void findByBrand() {
        List<Car> cars = carRepository.findByBrand("Toyota");
        assertEquals(3,cars.size());
    }

    @Test
    public void findByBrandAndModel() {
        List<Car> cars = carRepository.findByBrandAndModel("Toyota","Camry");
        assertEquals(2,cars.size());
        assertEquals(45.0, cars.get(0).getPricePrDay());
        assertEquals(55.0, cars.get(1).getPricePrDay());


    }

    @Test
    public void findAveragePricePerDay() {
        Double average = carRepository.findAvgPricePrDay();
        assertEquals(50,average);
    }


//    @Test
//    void findCarByReservationsIsNull() {
//        List<Car> carsWithout = carRepository.findCarByReservationsIsNull();
//        List<Car> carsAll = carRepository.findAll();
//        assertEquals(5,carsAll.size());
//        assertEquals(4,carsWithout.size());
//        assertEquals("Honda", carsWithout.get(carsWithout.size()-1).getBrand());
//        assertEquals("Fisk", carsAll.get(carsAll.size()-1).getModel());
//    }
}
