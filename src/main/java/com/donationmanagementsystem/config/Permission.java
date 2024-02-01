package com.donationmanagementsystem.config;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public enum Permission {

		//	permissions for admin
	  	ADMIN_READ("admin:read"),
	    ADMIN_UPDATE("admin:update"),
	    ADMIN_CREATE("admin:create"),
	    ADMIN_DELETE("admin:delete"),
	    
	    //permissions for organiser
	    ORGANISER_READ("organiser:read"),
	    ORGANISER_UPDATE("organiser:update"),
	    ORGANISER_CREATE("organiser:create"),
	    ORGANISER_DELETE("organiser:delete");

	
	@Getter
	private final String permission;
}
