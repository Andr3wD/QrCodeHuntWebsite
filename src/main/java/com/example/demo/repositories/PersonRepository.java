package com.example.demo.repositories;

import org.springframework.data.repository.CrudRepository;

import com.example.demo.model.Person;

// Why crud vs jpa repo? https://www.tutorialspoint.com/difference-between-crudrepository-and-jparepository-in-java#:~:text=Crud%20Repository%20is%20the%20base,acts%20as%20a%20marker%20interface.&text=JpaRepository%20ties%20your%20repositories%20to,sorting%20and%20paging%20or%20not.
// Why Long instead of UUID? (space) https://jivimberg.io/blog/2018/11/05/using-uuid-on-spring-data-jpa-entities/
// DAO and JPA? https://stackoverflow.com/questions/15554826/new-to-java-whats-jpa-and-dao
// What is JPA? https://www.infoworld.com/article/3379043/what-is-jpa-introduction-to-the-java-persistence-api.html
public interface PersonRepository extends CrudRepository<Person, Long> {
	// This doesn't have anything in it, because CrudRepository<Person, Long> has a bunch of what we need already. 
	// https://en.wikipedia.org/wiki/Convention_over_configuration
}
