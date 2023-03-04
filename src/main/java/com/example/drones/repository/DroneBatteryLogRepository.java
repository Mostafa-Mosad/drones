package com.example.drones.repository;

import com.example.drones.model.entity.DroneBatteryLog;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DroneBatteryLogRepository extends JpaRepository<DroneBatteryLog, Long> {

    DroneBatteryLog findFirstByDroneSerialNumberOrderByCreatedDateDesc(String droneSerialNumber);
}
