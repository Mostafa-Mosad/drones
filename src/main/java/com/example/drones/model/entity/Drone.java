package com.example.drones.model.entity;

import com.example.drones.common.enums.Model;
import com.example.drones.common.enums.State;
import com.example.drones.common.models.BaseEntity;
import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.Max;
import javax.validation.constraints.Size;
import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "drone")
@Entity(name = "drone")
@Builder
public class Drone extends BaseEntity {

    @Size(max = 100)
    @Column(name = "serial_number", length = 100, columnDefinition = "varchar(100)", unique = true)
    private String serialNumber;

    @Enumerated(EnumType.STRING)
    @Column(name = "model")
    private Model model;

    @Max(value = 500)
    @Column(name = "weight")
    private Integer weight;

    @Column(name = "battery_capacity")
    private String batteryCapacity;

    @Enumerated(EnumType.STRING)
    @Column(name = "state")
    private State state;

    @OneToMany(mappedBy = "drone", fetch = FetchType.LAZY)
    private Set<Medication> medications;
}
