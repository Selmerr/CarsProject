package dat3.car.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor

@Entity
public class Reservation extends AdminDetails {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    int id;

    LocalDate reservationDate;

    @ManyToOne
    @JoinColumn(name = "car_id", nullable = false)
    Car car;

    @ManyToOne
    @JoinColumn(name = "member_username", nullable = false)
    Member member;

    public Reservation(LocalDate reservationDate, Car car, Member member) {
        this.reservationDate = reservationDate;
        this.car = car;
        this.member = member;
        car.addReservation(this);
        member.addReservation(this);
    }
}
