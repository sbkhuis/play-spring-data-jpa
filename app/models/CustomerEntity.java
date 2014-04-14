package models;

import javax.persistence.Entity;

@Entity
public class CustomerEntity extends CustomerModel implements Customer {

	private static final long serialVersionUID = -2337234944193975675L;
	
	public CustomerEntity() {
		super();
	}
	
	public CustomerEntity(final String aName) {
		this.name = aName;
	}

	@Override
	public String name() {
		return this.name;
	}

}
