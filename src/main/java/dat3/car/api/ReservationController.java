package dat3.car.api;

import dat3.car.dto.ReservationRequest;
import dat3.car.dto.ReservationResponse;
import dat3.car.service.ReservationService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/reservations")
public class ReservationController {

    ReservationService reservationService;

    public ReservationController(ReservationService reservationService) {
        this.reservationService = reservationService;
    }

    @PostMapping()
    ReservationResponse addReservation(@RequestBody ReservationRequest body) {
        ReservationResponse response = reservationService.reserveCar(body);
        return response;
    }

    @GetMapping("/{username}")
    List<ReservationResponse> getReservationsByMember(@PathVariable String username) {
        return reservationService.getReservationsByMemberUsername(username);
    }
}
