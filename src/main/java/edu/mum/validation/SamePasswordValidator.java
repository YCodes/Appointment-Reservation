package edu.mum.validation;

import javax.transaction.Transactional;
import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Configurable;
import org.springframework.security.crypto.password.PasswordEncoder;

import edu.mum.domain.Person;

@Configurable
@Transactional
public class SamePasswordValidator implements ConstraintValidator<SamePassword, Person> {
	
	@Autowired
	private PasswordEncoder passwordEncoder;

	@Override
	public void initialize(SamePassword arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public boolean isValid(Person arg0, ConstraintValidatorContext arg1) {
		if(arg0.getPassword().equals(arg0.getVerifyPassword())) 
		{
			return true;
		}
		boolean b = false;
		try {
		b=passwordEncoder.matches(arg0.getPassword(), arg0.getVerifyPassword());
		} catch(Exception e) {
			return true;
		}
		return b;
	}


	



}