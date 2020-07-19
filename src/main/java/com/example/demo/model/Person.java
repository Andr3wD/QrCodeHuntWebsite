package com.example.demo.model;

import java.util.UUID;

/**
 * This class is a 'model' for user accounts.
 * Specifically the username, password (maybe), found qrcodes, etc...
 * 
 * Exceptions: comments (will be saved on QrCodes with user id instead)
 * @author Andrew
 *
 */
public class Person {
	
	private String name;
	private String password; //Most likely not the proper way to do this. TODO: research proper security techniques.
	private UUID id; //expecting to need for SQL DB
	//TODO for found qrcodes and comments, check out @onetomany
	
	

}
