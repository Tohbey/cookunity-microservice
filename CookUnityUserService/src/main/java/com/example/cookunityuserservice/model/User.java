package com.example.cookunityuserservice.model;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;
import java.util.UUID;

@Data
@Entity
@NoArgsConstructor
public class User extends BaseEntity{

    public User(UUID id){
        super(id);
    }

    private String lastName;
    private String otherNames;
    private String phoneNumber;
    private String email;
    private String password;
    private String secretAnswer;
    private int isActive;
    private String status;
    private String currentAddress;

    @OneToMany
    private List<Address> addressList;

    @OneToOne
    private SecretQuestion secretQuestion;

    @OneToOne
    private RememberToken token;


    @CreationTimestamp
    @Column(name = "created_at")
    private Date createdAt;

    @UpdateTimestamp
    @Column(name = "updated_at")
    private LocalDateTime updatedAt;

    @OneToOne
    private Role roles;
}
