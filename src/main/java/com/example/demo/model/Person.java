package com.example.demo.model;

import java.util.Arrays;
import java.util.Collection;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

/**
 * This class is a 'model' for user accounts. Specifically the username,
 * password (maybe), found qrcodes, etc...
 * 
 * Exceptions: comments (will be saved on QrCodes with user id instead)
 * 
 * @author Andrew
 *
 */
@Entity // This @Entity tag tells JPA to make this class persist.
public class Person implements UserDetails {

	private String username;
	private String password; // Most likely not the proper way to do this. TODO: research proper security
										// techniques.
	@Id // Tells JPA that this id is the primary key in the DB.
	@GeneratedValue
	private Short id; // expecting to need for SQL DB
	
	private Date created;
	private String authority;
	// TODO for found qrcodes and comments, check out @onetomany

	public String getAuthority() {
		return authority;
	}

	public void setAuthority(String authority) {
		this.authority = authority;
	}

	public Date getCreated() {
		return created;
	}

	public void setCreated(Date created) {
		this.created = created;
	}

	public Short getId() {
		return id;
	}

	public void setId(Short id) {
		this.id = id;
	}

	@Override
	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public int comparePassword(String password) {
		return password.compareTo(this.password);
	}

	@Override
	public String getUsername() {
		return username;
	}
	
	public void setUsername(String username) {
		this.username = username;
	}

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		return Arrays.asList(new SimpleGrantedAuthority(authority));
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


}
