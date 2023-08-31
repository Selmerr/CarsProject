package dat3.car.dto;

import dat3.car.entity.Car;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CarRequest {

    int id;

    String brand;

    String model;

    Double pricePrDay;

    Integer bestDiscount;

    public static Car getCarEntity(CarRequest c) {
        return new Car(c.brand,c.model,c.pricePrDay,c.bestDiscount);
    }

    public CarRequest (Car c) {
        this.id = c.getId();
        this.brand = c.getBrand();
        this.model = c.getModel();
        this.pricePrDay = c.getPricePrDay();
        this.bestDiscount = c.getBestDiscount();
    }
}
