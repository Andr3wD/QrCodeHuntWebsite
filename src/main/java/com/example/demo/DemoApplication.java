package com.example.demo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;

import com.example.demo.model.Person;
import com.example.demo.service.PersonService;

@SpringBootApplication
public class DemoApplication {
	// TODO remove nocache in application.properties

	@Autowired
	private PersonService personService;

	public static void main(String[] args) {
		SpringApplication.run(DemoApplication.class, args);

	}

	// Thanks to https://stackoverflow.com/a/44923402
	@EventListener(ApplicationReadyEvent.class)
	private void makeAdmin() {
		//TEST
		if (personService.getPersonById((short) 0) == null) {
			Person p = new Person();
			// TODO WARN Replace with actual admin account eventually.
			p.setUsername("admin");
			p.setPassword("password");
			p.setAuthority("ROLE_ADMIN");
			personService.insertPerson(p);
		}

	}

}
