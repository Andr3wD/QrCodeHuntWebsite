package com.example.demo.model;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

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
public class Person {

	private String name;
	private String password; // Most likely not the proper way to do this. TODO: research proper security
										// techniques.
	@Id // Tells JPA that this id is the primary key in the DB.
	@GeneratedValue
	private Long id; // expecting to need for SQL DB
	private Date created;
	// TODO for found qrcodes and comments, check out @onetomany

	public Date getCreated() {
		return created;
	}

	public void setCreated(Date created) {
		this.created = created;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public int comparePassword(String password) {
		return password.compareTo(this.password);
	}

}
