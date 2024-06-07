package com.scaler.userservicefirstspringapi.models;

import jakarta.persistence.*;
import lombok.*;

@Getter
@Setter
@Entity
public class Role extends BaseModel {
    private String value;

}
