package com.donationmanagementsystem.config;

import java.util.Collections;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.security.core.authority.SimpleGrantedAuthority;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public enum Role {

	USER(Collections.emptySet()),
	ADMIN(Set.of(
			Permission.ADMIN_READ,
			Permission.ADMIN_CREATE,
			Permission.ADMIN_DELETE,
			Permission.ADMIN_UPDATE,
			Permission.ORGANISER_READ,
			Permission.ORGANISER_CREATE,
			Permission.ORGANISER_DELETE,
			Permission.ORGANISER_UPDATE
			)
		),
	
	ORGANISER(Set.of(
			Permission.ORGANISER_READ,
			Permission.ORGANISER_CREATE,
			Permission.ORGANISER_DELETE,
			Permission.ORGANISER_UPDATE
			)
		)
	; 

	@Getter
	private final Set<Permission> permissions;

  public List<SimpleGrantedAuthority> getAuthorities() {
    var authorities = getPermissions()
            .stream()
            .map(permission -> new SimpleGrantedAuthority(permission.getPermission()))
            .collect(Collectors.toList());
    authorities.add(new SimpleGrantedAuthority("ROLE_" + this.name()));
    return authorities;
  }
}