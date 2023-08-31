package dat3.car.service;

import dat3.car.dto.CarRequest;
import dat3.car.dto.CarResponse;
import dat3.car.entity.Car;
import dat3.car.repository.CarRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.http.HttpStatus;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.web.server.ResponseStatusException;

import static org.junit.jupiter.api.Assertions.*;


import java.util.List;

@DataJpaTest
//DirtiesContext provided to me by chatGPT,
// after I explained it looked like H2 kept track of the auto-incremented value for the id
@DirtiesContext(classMode = DirtiesContext.ClassMode.BEFORE_EACH_TEST_METHOD)
class CarServiceH2Test {

    @Autowired
    CarRepository carRepository;
    CarService carService;

    Car c1, c2;
    @BeforeEach
    void setUp() {
        c1 = carRepository.save(new Car("Ford","Mustang",60,10));
        c2 = carRepository.save(new Car("Nissan","Rogue",53, 15));
        carService = new CarService(carRepository);
    }


    @Test
    void getCarsAllDetails() {
        List<CarResponse> cars = carService.getCars(true);
        assertEquals(2,cars.size());
        assertEquals("Nissan",cars.get(1).getBrand());
        assertEquals(10,cars.get(0).getBestDiscount());
        assertEquals(1,cars.get(0).getId());

    }

    @Test
    void getCarsNoDetails() {
        List<CarResponse> cars = carService.getCars(false);
        assertEquals(2,cars.size());
        assertEquals("Nissan",cars.get(1).getBrand());
        assertNull(cars.get(0).getBestDiscount());

    }

    @Test
    void findByIdFound() {
        CarResponse cr = carService.findById(1);
        assertNotNull(cr,"Car was not null");
        assertEquals(1,cr.getId());
        assertEquals("Ford",cr.getBrand());
        assertEquals(10,cr.getBestDiscount());
    }

    @Test
    void findByIdNotFound() {
        ResponseStatusException ex = assertThrows(ResponseStatusException.class,
                ()-> carService.findById(3),"Car with this id does not exist");
        assertEquals(HttpStatus.NOT_FOUND,ex.getStatusCode());
    }

    @Test
    void addCar() {
        CarRequest request = CarRequest.builder().
                brand("Kia").
                model("Soul").
                pricePrDay(500.0).
                bestDiscount(0).
                build();
        carService.addCar(request);

        CarResponse response = carService.findById(3);
        assertEquals("Kia",response.getBrand());
    }
    //Add another test if I decide to check for cars already in the database

    @Test
    void editCarDoesExist() {
        CarRequest request = new CarRequest(c1);
        request.setModel("Fisk");
        carService.editCar(request,1);
        CarResponse response = carService.findById(1);
        assertEquals("Ford",response.getBrand());
        assertEquals("Fisk",response.getModel());
        assertEquals(60,response.getPricePrDay());
        assertEquals(10,response.getBestDiscount());

    }

    @Test
    void editCarDoesNotExist() {
        CarRequest request = new CarRequest();
        ResponseStatusException ex = assertThrows(ResponseStatusException.class,
                ()->carService.editCar(request,3),"Car with this id does not exit");
        assertEquals(HttpStatus.NOT_FOUND,ex.getStatusCode());
    }

    @Test
    void editCarPrimaryKeyThrows() {
        CarRequest request = new CarRequest(c1);
        request.setId(3);
        ResponseStatusException ex = assertThrows(ResponseStatusException.class,
                ()->carService.editCar(request,1),"Can't edit id");
        assertEquals(HttpStatus.BAD_REQUEST,ex.getStatusCode());

    }

    @Test
    void deleteCarById() {
        carService.deleteCarById(1);
        assertFalse(carRepository.existsById(1));
    }

    @Test
    void deleteCarByIdDoesNotExists() {
        ResponseStatusException ex = assertThrows(ResponseStatusException.class,
                ()-> carService.deleteCarById(3),"Car with this id does not exist");
        assertEquals(HttpStatus.NOT_FOUND,ex.getStatusCode());
    }
}