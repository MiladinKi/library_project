package project_library.controllers;

import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import jakarta.validation.Valid;
import project_library.controllers.utils.RESTError;
import project_library.controllers.utils.UserCustomValidator;
import project_library.enteties.UserEntity;
import project_library.enteties.dto.UserDTO;
import project_library.repositories.UserRepository;

@RestController
@RequestMapping(path = "api/library")
public class UserController {

	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private UserCustomValidator userValidator;
	
	@InitBinder
	protected void initBinder(final WebDataBinder binder) {
		binder.addValidators(userValidator);
	}

	@RequestMapping(method = RequestMethod.POST, path = "/addUser")
	public ResponseEntity<?> addUser(@Valid @RequestBody UserDTO newUser) {
		try {
			UserEntity user = new UserEntity();
			user.setName(newUser.getName());
			user.setLastname(newUser.getLastname());
			user.setUsername(newUser.getUsername());
			user.setEmail(newUser.getEmail());
			user.setPassword(newUser.getPassword());
			user.setDateOfBirth(newUser.getDateOfBirth());
			user.setPersonalID(newUser.getPersonalID());
			user.setPhoneNumber(newUser.getPhoneNumber());
			user.setUserNumber(newUser.getUserNumber());
			user.setStreet(newUser.getStreet());
			user.setCity(newUser.getCity());
			user.setCountry(newUser.getCountry());
			user.setUserRole(newUser.getUserRole());
			userRepository.save(user);
			return new ResponseEntity<>(user, HttpStatus.CREATED);
		} catch (Exception e) {
			return new ResponseEntity<>(new RESTError(1, "Exception occures: " + e.getMessage()),
					HttpStatus.BAD_REQUEST);
		}

	}

	@ResponseStatus(HttpStatus.BAD_REQUEST)
	@ExceptionHandler(MethodArgumentNotValidException.class)
	public Map<String, String> handleValidationExceptions(MethodArgumentNotValidException ex) {
		Map<String, String> errors = new HashMap<>();
		ex.getBindingResult().getAllErrors().forEach((error) -> {
			String fieldName = "";
			String errorMessage = "";
			if (error instanceof FieldError) {
				fieldName = ((FieldError) error).getField();
				errorMessage = error.getDefaultMessage();
			} else if (error instanceof ObjectError){
				fieldName = ((ObjectError)error).getObjectName();
				errorMessage = error.getDefaultMessage();
			}
			errors.put(fieldName, errorMessage);
		});
		return errors;
	}
}
