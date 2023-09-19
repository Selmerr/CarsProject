package dat3.car.config;

import dat3.car.entity.Car;
import dat3.car.entity.Member;
import dat3.car.entity.Reservation;
import dat3.car.repository.CarRepository;
import dat3.car.repository.MemberRepository;
import dat3.car.repository.ReservationRepository;
import dat3.security.entity.Role;
import dat3.security.entity.UserWithRoles;
import dat3.security.repository.UserWithRolesRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.context.annotation.Configuration;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Configuration //Stod Controller før men Configuration er mere rigtigt, åbenbart
public class DeveloperData implements ApplicationRunner {

    CarRepository carRepository;
    MemberRepository memberRepository;
    ReservationRepository reservationRepository;

    public DeveloperData (CarRepository carRepository, MemberRepository memberRepository, ReservationRepository reservationRepository) {
        this.carRepository = carRepository;
        this.memberRepository = memberRepository;
        this.reservationRepository = reservationRepository;


    }
    @Override
    public void run(ApplicationArguments args) throws Exception {

        System.out.println("fisk fisk");

        List<Car> cars = new ArrayList<>();

        //Following test data is made by ChatGPT
        cars.add(new Car("Toyota", "Camry", 45.0, 10));
        cars.add(new Car("Ford", "Mustang", 60.0, 5));
        cars.add(new Car("Honda", "Civic", 40.0, 15));
        cars.add(new Car("Chevrolet", "Impala", 55.0, 8));
        cars.add(new Car("Nissan", "Altima", 42.0, 12));
        cars.add(new Car("BMW", "3 Series", 70.0, 7));
        cars.add(new Car("Audi", "A4", 65.0, 6));
        cars.add(new Car("Mercedes-Benz", "E-Class", 75.0, 10));
        cars.add(new Car("Lexus", "RX", 80.0, 9));
        cars.add(new Car("Subaru", "Outback", 48.0, 12));
        cars.add(new Car("Tesla", "Model S", 100.0, 5));
        cars.add(new Car("Volkswagen", "Jetta", 38.0, 20));
        cars.add(new Car("Mazda", "CX-5", 50.0, 10));
        cars.add(new Car("Kia", "Soul", 35.0, 25));
        cars.add(new Car("Hyundai", "Tucson", 55.0, 8));
        cars.add(new Car("Chrysler", "300", 58.0, 25));
        cars.add(new Car("Jeep", "Wrangler", 70.0, 5));
        cars.add(new Car("GMC", "Sierra", 65.0, 10));
        cars.add(new Car("Ram", "1500", 62.0, 12));
        cars.add(new Car("Porsche", "911", 150.0, 3));
        cars.add(new Car("Ferrari", "488", 250.0, 2));
        cars.add(new Car("Lamborghini", "Huracan", 300.0, 1));
        cars.add(new Car("Jaguar", "F-Type", 120.0, 5));
        cars.add(new Car("Maserati", "GranTurismo", 180.0, 3));
        cars.add(new Car("Rolls-Royce", "Phantom", 500.0, 1));
        cars.add(new Car("Aston Martin", "DB11", 280.0, 2));
        cars.add(new Car("McLaren", "720S", 350.0, 1));
        cars.add(new Car("Bugatti", "Chiron", 1500.0, 0));
        cars.add(new Car("Koenigsegg", "Jesko", 2000.0, 0));
        cars.add(new Car("Bentley", "Continental GT", 400.0, 3));
        cars.add(new Car("Land Rover", "Range Rover", 130.0, 6));
        cars.add(new Car("Subaru", "Forester", 45.0, 15));
        cars.add(new Car("Ford", "Escape", 50.0, 10));
        cars.add(new Car("Toyota", "RAV4", 52.0, 12));
        cars.add(new Car("Chevrolet", "Equinox", 48.0, 14));
        cars.add(new Car("Honda", "CR-V", 55.0, 9));
        cars.add(new Car("Nissan", "Rogue", 53.0, 11));
        cars.add(new Car("Jeep", "Grand Cherokee", 70.0, 7));
        cars.add(new Car("GMC", "Yukon", 85.0, 5));
        cars.add(new Car("Tesla", "Model 3", 80.0, 4));
        cars.add(new Car("Volkswagen", "Tiguan", 58.0, 8));
        cars.add(new Car("Mazda", "Mazda3", 42.0, 15));
        cars.add(new Car("Kia", "Sportage", 47.0, 13));
        cars.add(new Car("Hyundai", "Santa Fe", 60.0, 6));
        cars.add(new Car("Chrysler", "Pacifica", 55.0, 10));
        cars.add(new Car("Audi", "Q5", 70.0, 5));
        cars.add(new Car("Mercedes-Benz", "GLC", 75.0, 6));
        cars.add(new Car("Lexus", "NX", 65.0, 9));
        cars.add(new Car("Volvo", "XC60", 72.0, 7));
        cars.add(new Car("Porsche", "Cayenne", 100.0, 3));

        carRepository.saveAll(cars);

        List<Member> members = new ArrayList<>();

        //Following test data is made by ChatGPT
        members.add(new Member("john_doe", "pass123", "john@example.com", "John", "Doe", "123 Main St", "New York", "10001"));
        members.add(new Member("jane_smith", "smith456", "jane@example.com", "Jane", "Smith", "456 Elm Ave", "Los Angeles", "90001"));
        members.add(new Member("michael_johnson", "mike789", "michael@example.com", "Michael", "Johnson", "789 Oak Rd", "Chicago", "60601"));
        members.add(new Member("emily_williams", "emily_pass", "emily@example.com", "Emily", "Williams", "567 Pine Ln", "Houston", "77001"));
        members.add(new Member("robert_brown", "robertpass", "robert@example.com", "Robert", "Brown", "890 Maple St", "Miami", "33101"));
        members.add(new Member("jessica_davis", "jess_pass123", "jessica@example.com", "Jessica", "Davis", "234 Cedar Rd", "Seattle", "98101"));
        members.add(new Member("david_martinez", "david_pass456", "david@example.com", "David", "Martinez", "901 Walnut Blvd", "Dallas", "75201"));
        members.add(new Member("sarah_taylor", "sarahpass789", "sarah@example.com", "Sarah", "Taylor", "345 Birch Ave", "Atlanta", "30301"));
        members.add(new Member("chris_wilson", "chris123", "chris@example.com", "Christopher", "Wilson", "678 Spruce Ct", "Boston", "02101"));
        members.add(new Member("amanda_anderson", "amanda456", "amanda@example.com", "Amanda", "Anderson", "789 Oak St", "San Francisco", "94101"));

        memberRepository.saveAll(members);

        Car car1 = cars.get(0);
        Member m1 = members.get(0);
        reservationRepository.save(new Reservation(LocalDate.now(),cars.get(0),members.get(0)));
        reservationRepository.save(new Reservation(LocalDate.now().plusDays(2),cars.get(0),members.get(5)));


    }

    @Autowired
    UserWithRolesRepository userWithRolesRepository;

    final String passwordUsedByAll = "test12";

    /*****************************************************************************************
     NEVER  COMMIT/PUSH CODE WITH DEFAULT CREDENTIALS FOR REAL
     iT'S ONE OF THE TOP SECURITY FLAWS YOU CAN DO
     *****************************************************************************************/




}
