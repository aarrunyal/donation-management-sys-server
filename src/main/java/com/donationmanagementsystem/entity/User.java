package com.donationmanagementsystem.entity;

import java.util.Collection;
import java.util.List;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.donationmanagementsystem.config.Role;
import com.donationmanagementsystem.utils.BaseEntity;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.FetchType;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@EqualsAndHashCode(callSuper = true)
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table (name="users")
@JsonIgnoreProperties(value = {"createdBy", "lastModifiedBy"}, allowGetters = true)
public class User  extends BaseEntity implements UserDetails {

	private String firstName;
	private String lastName;
	private String email;
	private String password;
	private boolean verified;
	private boolean status;
	
	@Enumerated(EnumType.STRING)
	private Role role;
	
	@OneToMany(mappedBy = "user", cascade = CascadeType.ALL, orphanRemoval = true,fetch = FetchType.EAGER)
	@JsonIgnore
	private List<Token> tokens;
	
	@OneToMany(mappedBy = "user", cascade = CascadeType.ALL, fetch = FetchType.LAZY,  orphanRemoval = true )
	@JsonIgnore
	private List<UserAddress> addresses;

	@OneToMany(mappedBy = "organiser", cascade = CascadeType.ALL, orphanRemoval = true,fetch = FetchType.LAZY)
	@JsonIgnore
	private List<Donation> donations;

	@OneToOne(mappedBy = "user", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	@JsonIgnore
	private UserSetting setting;

	@OneToMany(mappedBy = "user", cascade = CascadeType.ALL, fetch = FetchType.EAGER,  orphanRemoval = true)
	@JsonIgnore
	private List<UserVerification> verifications;


	@OneToMany(mappedBy = "doner", cascade = CascadeType.ALL, fetch = FetchType.LAZY,  orphanRemoval = true)
	@JsonIgnore
	private List<DonationPayment> donors;

	
	

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		return role.getAuthorities();
	}
	@Override
	public String getUsername() {
		return email;
	}
	@Override
	public boolean isAccountNonExpired() {
		return true;
	}
	@Override
	public boolean isAccountNonLocked() {
	
		return true;
	}
	@Override
	public boolean isCredentialsNonExpired() {
		return true;
	}
	@Override
	public boolean isEnabled() {
		return true;
	}
	
	@Override
	public String getPassword() {
		return password;
	}
	
	
	@JsonIgnore
	public List<Token> getTokens(){
		return tokens;
	}
	
	@JsonIgnore
	public Role getRole(){
		return role;
	}
	
}
