package com.example.drones.repository;

import com.example.drones.model.entity.Drone;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DroneRepository extends JpaRepository<Drone, Long> {

    Drone findBySerialNumber(String serialNumber);
}
