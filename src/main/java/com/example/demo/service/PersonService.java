package com.example.demo.service;

import java.util.Date;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.example.demo.dao.PersonDao;
import com.example.demo.model.Person;

/**
 * 
 * @author Andrew
 *
 */
// What's the point in a DAO and service? https://softwareengineering.stackexchange.com/questions/220909/service-layer-vs-dao-why-both
// Differences between DAO and service layer: https://softwareengineering.stackexchange.com/questions/358681/if-service-layer-is-doing-nothing-just-calling-methods-in-dao-can-my-service
@Service
public class PersonService implements UserDetailsService {
	// TODO: deal with passwords properly.
	// https://reflectoring.io/spring-security-password-handling/
	PersonDao personDao;
	
	@Autowired
	PasswordEncoder passwordEncoder;

	@Autowired
	public PersonService(@Qualifier("personDao") PersonDao personDao) {
		this.personDao = personDao;
	}

	public int insertPerson(Person person) {
		if (person.getId() == null) { // Don't let us add a user with a custom ID. It will be auto-generated.
			person.setPassword(passwordEncoder.encode(person.getPassword()));
			Date now = new Date();
			person.setCreated(now);
			personDao.insertPerson(person);
			return 1;
		} else {
			return -1;
		}
	}

	public int deletePersonById(Short id) {
		return personDao.deletePersonById(id);
	}

	public Person getPersonById(Short id) {
		Optional<Person> temp = personDao.getPersonById(id);
		return temp.isEmpty() ? null : temp.get();
	}

	public String getAllPersons() {
		String result = "";
		for (Person person : personDao.getAllPersonsIterable()) {
			result = result + String.format("{%d, %s}", person.getId(), person.getUsername());
		}
		return result;
	}

	//This probably isn't good practice to put into this service. TODO look into it.
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		for (Person person : personDao.getAllPersonsIterable()) {
			if (person.getUsername().equals(username)) {
				return person;
			}
		}
		//TODO do exception
		return null;
	}

}
