package project_library.controllers.utils;

import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import project_library.enteties.dto.UserDTO;

@Component
public class UserCustomValidator implements Validator{

	@Override
	public boolean supports(Class<?> myClass) {
		// TODO Auto-generated method stub
		return UserDTO.class.equals(myClass);
	}

	@Override
	public void validate(Object target, Errors errors) {
		UserDTO user = (UserDTO) target;
		if(!user.getPassword().equals(user.getConfirmPassword())) {
			errors.reject("400", "Passwords must be the same!");
		}
	}

}
