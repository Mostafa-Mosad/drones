package com.example.drones.model.entity;

import com.example.drones.common.models.BaseEntity;
import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.Pattern;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "medication")
@Entity(name = "medication")
@Builder
public class Medication extends BaseEntity {

    @Pattern(regexp = "^[a-zA-Z0-9-_]+$")
    @Column(name = "name")
    private String name;

    @Column(name = "weight")
    private Integer weight;

    @Pattern(regexp = "^[A-Z-0-9_]+$")
    @Column(name = "code")
    private String code;

    @OneToOne
    @JoinColumn(name = "image_id", referencedColumnName = "id")
    private Image image;

    @ManyToOne
    @JoinColumn(name = "drone_id")
    private Drone drone;
}
