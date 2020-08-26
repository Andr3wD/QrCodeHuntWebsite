package com.example.demo.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import com.example.demo.dao.PersonDao;

public class DBUserDetailsService implements UserDetailsService {
	
	PersonDao personDao;
	
	@Autowired
	public DBUserDetailsService(@Qualifier("personDao") PersonDao personDao) {
		this.personDao = personDao;
	}

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		return null;
	}

}
