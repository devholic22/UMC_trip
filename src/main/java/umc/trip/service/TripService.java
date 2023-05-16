package umc.trip.service;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import umc.trip.dto.TripDto;
import umc.trip.entity.Trip;
import umc.trip.exception.InputValidateException;
import umc.trip.repository.TripRepository;

@Service
@Transactional
public class TripService {

    private final TripRepository tripRepository;

    public TripService(TripRepository tripRepository) {
        this.tripRepository = tripRepository;
    }

    // 여행 정보 생성
    public Trip upload(TripDto tripDto) {
        validateTripDto(tripDto);

        Trip newTrip = Trip.builder()
                .writer("익명")
                .nation(tripDto.getNation())
                .title(tripDto.getTitle())
                .content(tripDto.getContent())
                .view(0L)
                .score(tripDto.getScore())
                .build();

        tripRepository.save(newTrip);

        return newTrip;
    }

    private void validateTripDto(TripDto tripDto) {
        if (tripDto.getNation() == null ||
            tripDto.getTitle() == null ||
            tripDto.getContent() == null ||
            tripDto.getScore() == null ||
            tripDto.getScore() < 0 ||
            tripDto.getScore() > 10)
            throw new InputValidateException("validation error");
    }
}
