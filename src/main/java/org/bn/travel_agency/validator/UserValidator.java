package org.bn.travel_agency.validator;

import org.bn.travel_agency.entities.User;
import org.bn.travel_agency.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

@Component
public class UserValidator implements Validator {

	@Autowired
	private UserService userService;

	@Override
	public boolean supports(Class<?> aClass) {
		return User.class.equals(aClass);
	}

	@Override
	public void validate(Object o, Errors errors) {
		User user = (User) o;

		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "username", "Required");
		if (user.getUsername().length() < 4 || user.getUsername().length() > 32) {
			errors.rejectValue("username", "Size.userForm.size");
		}

		if (userService.findByUsername(user.getUsername()) != null) {
			errors.rejectValue("username", "Duplicate.userForm.username");
		}

		if (user.getFirstName().length() < 4 || user.getFirstName().length() > 32) {
			errors.rejectValue("firstName", "Size.userForm.size");
		}

		if (user.getLastName().length() < 4 || user.getLastName().length() > 32) {
			errors.rejectValue("lastName", "Size.userForm.size");
		}

		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "password", "Required");
		if (user.getPassword().length() < 4 || user.getPassword().length() > 32) {
			errors.rejectValue("password", "Size.userForm.password");
		}

		if (!user.getConfirmPassword().equals(user.getPassword())) {
			errors.rejectValue("confirmPassword", "Different.userForm.password");
		}
	}
}

