package com.example.drones.model.entity;

import com.example.drones.common.models.BaseEntity;
import lombok.*;

import javax.persistence.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "image")
@Entity(name = "image")
@Builder
public class Image extends BaseEntity {

    @Column(name = "name")
    private String name;

    @Column(name = "type")
    private String type;

    @Lob
    @Column(name = "content")
    private byte[] content;

    @OneToOne(mappedBy = "image")
    private Medication medication;
}
