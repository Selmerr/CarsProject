package dat3.car.repository;

import dat3.car.entity.Car;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;


public interface CarRepository extends JpaRepository<Car, Integer> {

    List<Car> findByBrand(String brand);
    List<Car> findByBrandAndModel(String brand, String model);
    List<Car> findCarsByReservationsIsNull();

    //Query i got from ChatGPT
    @Query("SELECT c FROM Car c WHERE c.bestDiscount = (SELECT MAX(c2.bestDiscount) FROM Car c2)")
    List<Car> findCarsWithHighestDiscount();

    @Query("SELECT AVG(pricePrDay) FROM Car")
    double findAvgPricePrDay();



}
