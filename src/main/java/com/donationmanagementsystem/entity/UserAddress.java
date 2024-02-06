package com.donationmanagementsystem.entity;

import com.donationmanagementsystem.utils.BaseEntity;
import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Table(name="user_addresses")
public class UserAddress extends BaseEntity {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;

	@Column(name="address_line_1", nullable = false)
	private String addressLine1;

	@Column(name="address_line_2")
	private String addressLine2;

	
	@Column(nullable = false)
	private String city;
	
	@Column(nullable = false)
	private String state;

	@Column(nullable = false)
	private String country;
	
	@Column(nullable = false)
	private String postalCode;

	@ManyToOne
	@JoinColumn(name="user_id")
	@JsonIgnore
	private User user;
	
	
}
