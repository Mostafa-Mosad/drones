package com.example.drones.model.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.web.multipart.MultipartFile;

import javax.persistence.*;
import javax.validation.constraints.Pattern;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "medication")
@Entity(name = "medication")
@Builder
public class Medication {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Pattern(regexp = "^[a-zA-Z0-9]([_-](?![_-])|[a-zA-Z0-9])*[a-zA-Z0-9]*([_-](?![_-]))*$")
    @Column(name = "name")
    private String name;

    @Column(name = "weight")
    private Integer weight;

//    @Pattern(regexp = "^[A-Z]([_](?![_]))*[0-9]*$")
    @Column(name = "code")
    private String code;

    @OneToOne
    @JoinColumn(name = "image_id", referencedColumnName = "id")
    private Image image;

    @ManyToOne
    @JoinColumn(name = "drone_id")
    private Drone drone;
}
