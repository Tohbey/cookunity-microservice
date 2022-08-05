package com.example.cookunityuserservice.model;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import java.util.UUID;

@Data
@Entity
@NoArgsConstructor
public class Address extends BaseEntity{
    public Address(UUID id){
        super(id);
    }

    private String country;
    private String state;
    private String LGA;
    private String address;
    private Boolean isCurrent;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;
}
