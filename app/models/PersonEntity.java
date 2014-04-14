package models;

import com.mysema.query.annotations.QueryProjection;

public class PersonEntity {
	Person person;
	
	public PersonEntity(String name, String surname) {
		this.person = new Person();
		this.person.firstname= name;
		this.person.surname= surname;
	}
	
	@QueryProjection
	public PersonEntity(Person person) {
		this.person = person;
	}
	
	public Long id() {
		return this.person.id;
	}
	
	public String first() {
		return this.person.firstname;
		
	}
	
	public String last() {
		return this.person.surname;
	}
	
	public void doSomething() {
		this.person.firstname = "Stijn changed";
	}
	
	public void save(PersonRepository repos) {
		repos.save(this.person);
	}
}