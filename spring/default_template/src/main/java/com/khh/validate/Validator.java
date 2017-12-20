package com.khh.validate;

import javax.annotation.Resource;

import com.khh.DefaultVO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.validation.BindingResult;
import org.springframework.validation.Errors;

import com.omnicns.web.spring.validation.ValidationUtil;

public class Validator implements org.springframework.validation.Validator{
	Logger log = LoggerFactory.getLogger(this.getClass());
	/*
	 	<!-- jsr303 validator -->
	 	id="local_validator"
		<bean id="validatorJSR303" class="org.springframework.validation.beanvalidation.LocalValidatorFactoryBean"/>
	 */
	@Resource(name="local_validator")
	org.springframework.validation.beanvalidation.LocalValidatorFactoryBean validator;
	@Override
	public boolean supports(Class<?> clazz) {
		return DefaultVO.class.isAssignableFrom(clazz);
	}

	@Override
	public void validate(Object target, Errors errors) {
		if(DefaultVO.class.isAssignableFrom(target.getClass())){
			DefaultVO defaultVO = (DefaultVO)target;
			errors = ValidationUtil.validate(validator, target);
			if(BindingResult.class.isAssignableFrom(errors.getClass())){
				defaultVO.validate((BindingResult)errors);
				defaultVO.setErrors((BindingResult)errors);
			}
		}
	}

	public org.springframework.validation.beanvalidation.LocalValidatorFactoryBean getValidator() {
		return validator;
	}

	public void setValidator(org.springframework.validation.beanvalidation.LocalValidatorFactoryBean validator) {
		this.validator = validator;
	}
	
}
