package com.example.cookunityuserservice.model;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import java.util.Date;
import java.util.UUID;

@Entity
@Data
@NoArgsConstructor
public class RememberToken extends BaseEntity{
    public RememberToken(Long id){
        super(id);
    }

    private String token;
    @Column(name = "expired_at")
    private Date expiredAt;

    @CreationTimestamp
    @Column(name = "created_at")
    private Date createdAt;
}
