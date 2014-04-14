package models;

import javax.persistence.MappedSuperclass;
import javax.validation.constraints.NotNull;

import org.springframework.data.jpa.domain.AbstractPersistable;

@MappedSuperclass
public class CustomerModel extends AbstractPersistable<Long> {	
	private static final long serialVersionUID = -7681619460064233864L;
	
	@NotNull
	protected String name;
	
	
}
