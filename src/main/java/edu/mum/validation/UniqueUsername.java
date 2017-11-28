package edu.mum.validation;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import javax.validation.Constraint;
import javax.validation.Payload;

@Documented
@Constraint(validatedBy = UniqueUsenameValidator.class)
//@Target( { ElementType.METHOD, ElementType.FIELD })
//@Retention(RetentionPolicy.RUNTIME)
@Target( { ElementType.FIELD })
@Retention(RetentionPolicy.RUNTIME)
public @interface UniqueUsername {
	
	 String message() default "Invalid username aready exist";
	    Class<?>[] groups() default {};
	    Class<? extends Payload>[] payload() default {};
	
	

}
