package com.example.demo.dao;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.example.demo.model.Person;
import com.example.demo.repositories.PersonRepository;

@Repository("personDao")
public class PersonDataAccessService implements PersonDao {

	private PersonRepository DB;

	@Autowired
	public PersonDataAccessService(PersonRepository repo) {
		this.DB = repo;
	}

	@Override
	public void insertPerson(Person person) {
		DB.save(person); // IDs are automatically assigned
	}

	@Override
	public int deletePersonById(Long id) {
		return 0;
	}

	@Override
	public Optional<Person> getPersonById(Long id) {
		return DB.findById(id);
	}

	@Override
	public Iterable<Person> getAllPersonsIterable() {
		return DB.findAll();
	}

}
