package com.donationmanagementsystem.entity;

import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import com.donationmanagementsystem.utils.BaseEntity;
import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EntityListeners;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@EntityListeners(AuditingEntityListener.class)
@EqualsAndHashCode(callSuper = true)
@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Table(name="user_addresses")
public class UserAddress extends BaseEntity {


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

	private boolean status;

	@ManyToOne
	@JoinColumn(name="user_id")
	@JsonIgnore
	private User user;
	
	
}
