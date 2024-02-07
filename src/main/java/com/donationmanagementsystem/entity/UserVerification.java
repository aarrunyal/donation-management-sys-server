package com.donationmanagementsystem.entity;

import com.donationmanagementsystem.utils.BaseEntity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name="user_verifications")
public class UserVerification extends BaseEntity{
    
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    public Long id;
    public String token;
    public boolean expired;

    @ManyToOne
    @JoinColumn(name="user_id")
    public User user;
}
