package umc.trip.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import umc.trip.entity.Trip;

import java.util.List;

public interface TripRepository extends JpaRepository<Trip, Long> {
    List<Trip> findByTitleContaining(String title);
}
