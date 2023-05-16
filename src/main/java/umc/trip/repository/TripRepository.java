package umc.trip.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import umc.trip.entity.Trip;

public interface TripRepository extends JpaRepository<Trip, Long> {
}
