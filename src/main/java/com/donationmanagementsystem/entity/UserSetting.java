package com.donationmanagementsystem.entity;

import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import com.donationmanagementsystem.utils.BaseEntity;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EntityListeners;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
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
@Table(name="user_settings")
public class UserSetting extends BaseEntity{
	
	@Column(nullable = false)
	private String contactNo;
	
	private String alternativeContactNo;
	
	@OneToOne
	@JoinColumn(name="user_id")
	private User user;

	private boolean status;
	
}
