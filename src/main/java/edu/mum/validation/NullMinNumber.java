package edu.mum.validation;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import javax.validation.Constraint;
import javax.validation.OverridesAttribute;
import javax.validation.Payload;
import javax.validation.ReportAsSingleViolation;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraintvalidation.SupportedValidationTarget;
import javax.validation.constraintvalidation.ValidationTarget;


	@NotNull
	@Min(value = 0)
	@Target( { ElementType.METHOD, ElementType.FIELD })
	@Retention(RetentionPolicy.RUNTIME)
	@Constraint(validatedBy = {})
	@Documented
	@SupportedValidationTarget(ValidationTarget.ANNOTATED_ELEMENT)
	@ReportAsSingleViolation
	public @interface NullMinNumber {

		String message() default "Number must have minimum value";

		Class<?>[] groups() default {};
		Class<? extends Payload>[] payload() default {  };

		@OverridesAttribute(constraint = Min.class, name = "value")
		long value();
	}

