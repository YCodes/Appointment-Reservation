package edu.mum.validation;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import javax.validation.Constraint;
import javax.validation.Payload;

@Documented
@Constraint(validatedBy = SamePasswordValidator.class)
//@Target( { ElementType.METHOD, ElementType.FIELD })
//@Retention(RetentionPolicy.RUNTIME)
@Target( { ElementType.FIELD,ElementType.TYPE })
@Retention(RetentionPolicy.RUNTIME)
public @interface SamePassword {
	
	 String message() default "password not the same";
	    Class<?>[] groups() default {};
	    Class<? extends Payload>[] payload() default {};

}