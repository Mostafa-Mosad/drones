package com.example.drones.repository;

import com.example.drones.model.entity.Drone;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Set;

public interface DroneRepository extends JpaRepository<Drone, Long> {

    Drone findBySerialNumber(String serialNumber);

    @Query(value = "SELECT * FROM {h-schema}drone " +
            " WHERE drone.state= 'IDLE' AND " +
            " cast(SUBSTRING(drone.battery_capacity, 1, length(drone.battery_capacity)-1) as int4) >= 25",
            nativeQuery = true)
    Set<Drone> getAvailableDrones();
}
