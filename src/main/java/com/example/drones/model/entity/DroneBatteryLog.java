package com.example.drones.model.entity;

import com.example.drones.common.enums.Model;
import com.example.drones.common.enums.State;
import com.example.drones.common.models.BaseEntity;
import lombok.*;

import javax.persistence.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "drone_battery_log")
@Entity(name = "drone_battery_log")
@Builder
public class DroneBatteryLog extends BaseEntity {

    @Column(name = "drone_serial_number")
    private String droneSerialNumber;

    @Enumerated(EnumType.STRING)
    @Column(name = "drone_model")
    private Model droneModel;

    @Column(name = "drone_battery_capacity")
    private String droneBatteryCapacity;

    @Enumerated(EnumType.STRING)
    @Column(name = "drone_state")
    private State droneState;
}
