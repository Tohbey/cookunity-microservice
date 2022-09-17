package com.example.cookunityuserservice.model;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import java.util.UUID;

@Entity
@Data
@NoArgsConstructor
public class Role extends BaseEntity{
    public Role(Long id){
        super(id);
    }

    private String role;
}
