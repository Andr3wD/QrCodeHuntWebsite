package com.example.demo.model;

import java.util.Date;
import java.util.UUID;

/**
 * This class is a 'model' for QrCodes.
 * Specifically the id, location, hint, name, created date, and so on.
 * 
 * @author Andrew
 *
 */
public class QrCode {

	// Needs to have: UUID for specific QrCode, private location (for me
	// specifically)

	// TODO after: add name, hint, and time/day added

	// TODO later: add public commenting, difficulty, and track # times found.
	// Maybe track #times found by counting the number of users in DB who have found this instead.
	// Will probably just go with # of unique users to url to track how many found it.
	// Would be resource-intensive to rely on backend to calculate that every time.

	private UUID id;
	private String location;
	private String hint;
	private String name;
	private Date created;

	public QrCode() {

	}

	public QrCode(UUID id, String location, String hint, String name) {
		this.id = id;
		this.location = location;
		this.hint = hint;
		this.name = name;
	}

	public UUID getId() {
		return id;
	}

	public String getLocation() {
		return location;
	}

	public void setId(UUID id) {
		this.id = id;
	}

	public void setLocation(String location) {
		this.location = location;
	}

	public String getHint() {
		return hint;
	}

	public void setHint(String hint) {
		this.hint = hint;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Date getCreated() {
		return created;
	}

	public void setCreated(Date created) {
		this.created = created;
	}

	@Override
	public String toString() {
		return "{" + id + ", " + location  + ", " + hint + ", " + name + ", " + created + "}";
	}

}
