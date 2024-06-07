package com.scaler.userservicefirstspringapi.models;

import jakarta.persistence.*;
import lombok.*;

@MappedSuperclass
@Getter
@Setter
public class BaseModel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private boolean deleted;
}
