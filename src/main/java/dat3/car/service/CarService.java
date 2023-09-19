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
        List<CarResponse> response = cars.stream().map((car -> new CarResponse(car,includeAll))).toList();

//        for (Car fisk : cars) {
//            CarResponse cr = new CarResponse(fisk,includeAll);
//            response.add(cr);
//        }

        return response;
    }

    public List<CarResponse> getCarsByBrandAndModel(String brand, String model) {
        List<Car> cars = carRepository.findByBrandAndModel(brand,model);
        List<CarResponse> response = cars.stream().map((car -> new CarResponse(car,false))).toList();
        return response;
    }

    public List<CarResponse> getCarsWithoutReservations() {
        List<Car> cars = carRepository.findCarsByReservationsIsNull();
        List<CarResponse> response = cars.stream().map((car -> new CarResponse(car,false))).toList();
        return response;
    }

    public CarResponse findById(int id) {
        Car car = carRepository.findById(id).orElseThrow(()-> new ResponseStatusException(HttpStatus.NOT_FOUND,"Car with this id does not exist"));
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
        Car car = carRepository.findById(id).orElseThrow(()->new ResponseStatusException(HttpStatus.NOT_FOUND, "Car with this id does not exist"));
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

    public List<CarResponse> getCarsWithHighestDiscount() {
        List<Car> cars = carRepository.findCarsWithHighestDiscount();
        List<CarResponse> response = cars.stream().map((car -> new CarResponse(car,true))).toList();
        return response;
    }

    public void deleteCarById(int id) {
        Car car = carRepository.findById(id).orElseThrow(()->new ResponseStatusException(HttpStatus.NOT_FOUND, "Car with this id does not exist"));
        carRepository.delete(car);
    }

    public Double getAvgPricePrDay() {
    return null;
    }
}
