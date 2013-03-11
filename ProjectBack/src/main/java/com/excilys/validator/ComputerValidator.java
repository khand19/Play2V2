package com.excilys.validator;

import java.text.SimpleDateFormat;
import java.util.Date;

import org.springframework.stereotype.Service;
import org.springframework.util.Assert;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import com.excilys.bean.Computer;

@Service
public class ComputerValidator implements Validator {

	public boolean supports(Class<?> arg0) {
		return Computer.class.isAssignableFrom(arg0);
	}

	public void validate(Object target, Errors errors) {
		ValidationUtils.rejectIfEmpty(errors, "nameComputer", "nameCssError",
				"Name is required");
		Computer c = (Computer) target;
		if (c.getIntroducedDate() != null) {
			try {
				Assert.isInstanceOf(Date.class,
						((Computer) target).getIntroducedDate());
			} catch (Exception e) {
				errors.rejectValue("introducedDate", "introducedDateCssError",
						"Connard");
			}
		}
		
		if (c.getDscountedDate() != null) {
			String myDate = c.getDscountedDate().toString();
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
			sdf.setLenient(true);
		  	Date d = new Date();
			  	
		  	try {
			   d = sdf.parse(myDate );
			   String t = sdf.format(d);
			   if(t.compareTo(myDate) !=  0)
				   errors.rejectValue("dscountedDate", "dscountedDateCssError","Connard");
		  	} catch (Exception e) {
//				   errors.rejectValue("dscountedDate", "dscountedDateCssError","Connard2");
		  	}
		}else{
//			if()
			   errors.rejectValue("dscountedDate", "dscountedDateCssError","Connard3");
		}
	}
}
