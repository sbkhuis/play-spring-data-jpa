package controllers;

import java.util.List;

import javax.inject.Inject;
import javax.inject.Named;
import javax.inject.Singleton;

import models.CustomerEntity;
import models.CustomerRepository;
import models.QCustomerEntity;
import models.PersonEntity;
import models.PersonRepository;
import models.QPerson;
import play.Logger;
import play.db.jpa.JPA;
import play.mvc.Controller;
import play.mvc.Result;

import com.mysema.query.jpa.impl.JPAQuery;
import com.mysema.query.types.Projections;

/**
 * The main set of web services.
 */
@Named
@Singleton
public class Application extends Controller {
	
	
    private final PersonRepository personRepository;
    
    private final CustomerRepository customRepository;
    
    // We are using constructor injection to receive a repository to support our desire for immutability.
    @Inject
    public Application(final PersonRepository personRepository, final CustomerRepository customRepository) {
        this.personRepository = personRepository;
        this.customRepository = customRepository;
    }
    
    public Result customer() {
    	CustomerEntity entity = new CustomerEntity();
    	this.customRepository.save(entity);
    	return ok();
    }
    
    public Result customerIndex() {
    	QCustomerEntity qCustomer = QCustomerEntity.customerEntity;
    	
        StringBuilder result = new StringBuilder();
    	for(CustomerEntity entity : this.customRepository.findAll(qCustomer.id.gt(0L))) {
    		result.append(entity.getId() + " " +  entity.name() + "\n");
    	}
    	return ok(result.toString());
    }

    public Result index() {

    	 QPerson qPerson = QPerson.person;
         JPAQuery query = new JPAQuery(JPA.em("default"));
         
         List<PersonEntity> entities = query.from(qPerson).list(Projections.constructor(PersonEntity.class, qPerson));
         StringBuilder result = new StringBuilder();
         for(PersonEntity entity : entities) {
        	 result.append(entity.id() + " "  + entity.first()+ " "+ entity.last() + "\n");
         }
         
         Logger.error("index");
         return ok(result.toString());
    }
    
    public Result create() {
    	PersonEntity entity = new PersonEntity("stijn", "beekhuis");
    	entity.save(this.personRepository);
    	Logger.error("created: " + entity.id());
    	return redirect(controllers.routes.Application.index());
    }
    
    public Result read(Long id) {
    	QPerson qPerson = QPerson.person;
        JPAQuery query = new JPAQuery(JPA.em("default"));
        PersonEntity entity = query.from(qPerson).where(qPerson.id.eq(id)).uniqueResult(Projections.constructor(PersonEntity.class, qPerson));
        Logger.error("read: " + entity.id());
        return ok(entity.id() + " "  + entity.first() + entity.last());
    }
    
    public Result update(Long id) {
    	QPerson qPerson = QPerson.person;
        JPAQuery query = new JPAQuery(JPA.em("default"));
        PersonEntity entity = query.from(qPerson).where(qPerson.id.eq(id)).uniqueResult(Projections.constructor(PersonEntity.class, qPerson));
       
        entity.doSomething();
        entity.save(this.personRepository);
        
        Logger.error("updated: " + entity.id());
        return redirect(controllers.routes.Application.read(id));
    }
    
    public Result delete(Long id) {
    	this.personRepository.delete(id);
    	Logger.error("deleted: " + id);
    	return redirect(controllers.routes.Application.index()); 
    }
}
