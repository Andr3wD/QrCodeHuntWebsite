package com.example.demo.dao;

import java.util.Optional;

import com.example.demo.model.Person;

/**
 * This interface tells us how we should always be able to interact with our
 * database, no matter how it's implemented.
 * 
 * @author Andrew
 *
 */
public interface PersonDao {
	//TODO javadocs

	void insertPerson(Person person);
	
	int deletePersonById(Long id);
	
	Iterable<Person> getAllPersonsIterable();
	
	Optional<Person> getPersonById(Long id);
}
