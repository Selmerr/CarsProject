package dat3.car.service;

import dat3.car.dto.CarRequest;
import dat3.car.dto.CarResponse;
import dat3.car.entity.Car;
import dat3.car.repository.CarRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.ArrayList;
import java.util.List;

@Service
public class CarService {

    CarRepository carRepository;

    public CarService(CarRepository carRepository) {
        this.carRepository = carRepository;
    }

    public List<CarResponse> getCars(boolean includeAll) {

        List<Car> cars = carRepository.findAll();
        List<CarResponse> response = new ArrayList<>();

        for (Car car : cars) {
            CarResponse cr = new CarResponse(car,includeAll);
            response.add(cr);
        }

        return response;
    }

    public CarResponse findById(int id) {
        Car car = carRepository.findById(id).orElseThrow(()-> new ResponseStatusException(HttpStatus.BAD_REQUEST,"Car with this id does not exist"));
        CarResponse response = new CarResponse(car,true);
        return response;
    }

    public CarResponse addCar(CarRequest body) {
        //Maybe if statement to check for car already in database. not sure
        Car newcar = CarRequest.getCarEntity(body);
        carRepository.save(newcar);
        return new CarResponse(newcar,true);
    }

    public ResponseEntity<Boolean> editCar(CarRequest body, int id) {
        Car car = carRepository.findById(id).orElseThrow(()->new ResponseStatusException(HttpStatus.BAD_REQUEST, "Car with this id does not exist"));
        if(body.getId()!=(id)) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST,"Cannot change id");
        }
        car.setBrand(body.getBrand());
        car.setModel(body.getModel());
        car.setPricePrDay(body.getPricePrDay());
        car.setBestDiscount(body.getBestDiscount());
        carRepository.save(car);
        return ResponseEntity.ok(true);
    }

    public void deleteCarById(int id) {
        Car car = carRepository.findById(id).orElseThrow(()->new ResponseStatusException(HttpStatus.BAD_REQUEST, "Car with this id does not exist"));
        carRepository.delete(car);
    }
}
