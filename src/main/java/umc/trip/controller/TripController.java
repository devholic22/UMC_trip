package umc.trip.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import umc.trip.dto.TripDto;
import umc.trip.dto.TripEditDto;
import umc.trip.exception.ExceptionResponse;
import umc.trip.exception.InputValidateException;
import umc.trip.exception.TargetNotFoundException;
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

    // 여행 정보 글 조회
    @GetMapping("/{id}")
    ResponseEntity<?> find(@PathVariable Long id) {
        try {
            return ResponseEntity.ok(tripService.read(id));
        } catch (TargetNotFoundException e) {
            return errorMessage(e.getMessage());
        }
    }

    // 여행 정보 글 수정
    @PutMapping("/{id}")
    ResponseEntity<?> edit(@PathVariable Long id, @RequestBody TripEditDto editDto) {
        try {
            return ResponseEntity.ok(tripService.edit(id, editDto));
        } catch (TargetNotFoundException | InputValidateException e) {
            return errorMessage(e.getMessage());
        }
    }

    private ResponseEntity<ExceptionResponse> errorMessage(String message) {
        ExceptionResponse exceptionResponse = new ExceptionResponse(message);
        return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                .body(exceptionResponse);
    }
}
