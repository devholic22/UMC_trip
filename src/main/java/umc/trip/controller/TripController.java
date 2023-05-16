package umc.trip.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import umc.trip.dto.TripDto;
import umc.trip.exception.ExceptionResponse;
import umc.trip.exception.InputValidateException;
import umc.trip.service.TripService;

@RestController
@RequestMapping("/trip")
public class TripController {

    private final TripService tripService;

    @Autowired
    public TripController(TripService tripService) {
        this.tripService = tripService;
    }

    // 여행 정보 글 생성
    @PostMapping
    ResponseEntity<?> upload(@RequestBody TripDto tripDto) {
        try {
            return ResponseEntity.ok(tripService.upload(tripDto));
        } catch (InputValidateException e) {
            return errorMessage(e.getMessage());
        }
    }

    private ResponseEntity<ExceptionResponse> errorMessage(String message) {
        ExceptionResponse exceptionResponse = new ExceptionResponse(message);
        return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                .body(exceptionResponse);
    }
}
