package edu.mum.validation;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Configurable;
import org.springframework.transaction.annotation.Transactional;

import edu.mum.domain.Person;
import edu.mum.service.PersonService;
@Configurable
@Transactional
public class UniqueUsenameValidator implements
ConstraintValidator<UniqueUsername, String> {

	@Autowired
	private PersonService personService;
	@Override
	public void initialize(UniqueUsername arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	
	public boolean isValid(String arg0, ConstraintValidatorContext arg1) {
		// TODO Auto-generated method stub
		Person p=null;
		try {
			
		
		 p = personService.findByUsername(arg0);
		
		} catch(Exception e) {
			return true;
		}
		
		return p == null;
	}

}
