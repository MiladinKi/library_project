package project_library.controllers;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import jakarta.validation.Valid;
import jakarta.validation.constraints.AssertFalse.List;
import jakarta.websocket.server.PathParam;
import project_library.controllers.utils.RESTError;
import project_library.controllers.utils.UserCustomValidator;
import project_library.enteties.UserEntity;
import project_library.enteties.dto.UserDTO;
import project_library.repositories.UserRepository;

@RestController
@RequestMapping(path = "api/library/users")
public class UserController {

	private final Logger logger = LoggerFactory.getLogger(UserController.class);

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
			logger.info("You are create new user!");
			return new ResponseEntity<>(user, HttpStatus.CREATED);
		} catch (Exception e) {
			logger.error("You have an error while create a new user!");
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
			} else if (error instanceof ObjectError) {
				fieldName = ((ObjectError) error).getObjectName();
				errorMessage = error.getDefaultMessage();
			}
			errors.put(fieldName, errorMessage);
		});
		return errors;
	}

	@RequestMapping(method = RequestMethod.GET, path = "/allUsers")
	public ResponseEntity<?> getAllUsers() {
		try {
			logger.info("You have a list of all users");
			return new ResponseEntity<>(userRepository.findAll(), HttpStatus.OK);
		} catch (Exception e) {
			logger.warn("You haver an error for list of all users");
			return new ResponseEntity<>(new RESTError(1, "Exception occures: " + e.getMessage()),
					HttpStatus.BAD_REQUEST);
		}

	}

	@RequestMapping(method = RequestMethod.GET, path = "/userId/{userId}")
	public ResponseEntity<?> userById(@PathVariable Integer userId) {
		try {
			UserEntity user = userRepository.findById(userId).get();
			logger.info("You have a data for user by id");
			return new ResponseEntity<>(user, HttpStatus.OK);

		} catch (Exception e) {
			return new ResponseEntity<>(new RESTError(1, "Exception occures: " + e.getMessage()),
					HttpStatus.BAD_REQUEST);
		}
	}

	@RequestMapping(method = RequestMethod.PUT, path = "/modifyUser/{userId}")
	public ResponseEntity<?> modifyUser(@Valid @PathVariable Integer userId, @RequestBody UserDTO updateUser) {
		UserEntity userEntity = userRepository.findById(userId).get();
		UserDTO user = new UserDTO();
		try {
			if (userEntity == null) {
				return new ResponseEntity<>(new RESTError(404, "User not found"), HttpStatus.NOT_FOUND);

			}
			if (updateUser.getName() != null) {
				user.setName(updateUser.getName());
			}
			if (updateUser.getLastname() != null) {
				user.setLastname(updateUser.getLastname());
			}
			if (updateUser.getUsername() != null) {
				user.setUsername(updateUser.getUsername());
			}
			if (updateUser.getEmail() != null) {
				user.setEmail(updateUser.getEmail());
			}
			if (updateUser.getDateOfBirth() != null) {
				user.setDateOfBirth(updateUser.getDateOfBirth());
			}
			if (updateUser.getPersonalID() != null) {
				user.setPersonalID(updateUser.getPersonalID());
			}
			if (updateUser.getPhoneNumber() != null) {
				user.setPhoneNumber(updateUser.getPhoneNumber());
			}
			if (updateUser.getUserNumber() != null) {
				user.setUserNumber(updateUser.getUserNumber());
			}
			if (updateUser.getStreet() != null) {
				user.setStreet(updateUser.getStreet());
			}
			if (updateUser.getCity() != null) {
				user.setCity(updateUser.getCity());
			}
			if (updateUser.getCountry() != null) {
				user.setCountry(updateUser.getCountry());
			}
			if (updateUser.getPassword() != null) {
				user.setPassword(updateUser.getPassword());
			}
			if (updateUser.getConfirmPassword() != null) {
				user.setConfirmPassword(updateUser.getConfirmPassword());
			}
			if(updateUser.getUserRole() != null) {
				user.setUserRole(updateUser.getUserRole());
			}
			logger.info("You are modify user by Id");
			userRepository.save(userEntity);
			return new ResponseEntity<>(user, HttpStatus.OK);

		} catch (Exception e) {
			logger.warn("You have a problem with modify user by ID");
			return new ResponseEntity<>(new RESTError(1, "Exception occures: " + e.getMessage()),
					HttpStatus.BAD_REQUEST);
		}
	}

	@RequestMapping(method = RequestMethod.DELETE, path = "/deleteById/{userId}")
	public ResponseEntity<?> deleteBYId(@PathVariable Integer userId) {
		try {
			UserEntity user = userRepository.findById(userId).get();
			userRepository.delete(user);
			logger.info("You are delete user by ID");
			return new ResponseEntity<UserEntity>(user, HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>(new RESTError(1, "Exception occures: " + e.getMessage()),
					HttpStatus.BAD_REQUEST);
		}
	}

	@RequestMapping(method = RequestMethod.GET, path = "/byName/{name}")
	public ResponseEntity<?> findByName(@PathVariable String name) {
		try {
			return new ResponseEntity<>(userRepository.findByName(name), HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>(new RESTError(1, "Exception occures: " + e.getMessage()),
					HttpStatus.BAD_REQUEST);
		}

	}

	@RequestMapping(method = RequestMethod.GET, path = "/byLastname/{lastname}")
	public ResponseEntity<?> findByLastname(@PathVariable String lastname) {
		try {
			return new ResponseEntity<>(userRepository.findByLastname(lastname), HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>(new RESTError(1, "Exception occures: " + e.getMessage()),
					HttpStatus.BAD_REQUEST);
		}

	}

	@RequestMapping(method = RequestMethod.GET, path = "/byUsername/{username}")
	public ResponseEntity<?> findByUsername(@PathVariable String username) {
		try {
			return new ResponseEntity<>(userRepository.findByUsername(username), HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>(new RESTError(1, "Exception occures: " + e.getMessage()),
					HttpStatus.BAD_REQUEST);
		}

	}

	@RequestMapping(method = RequestMethod.GET, path = "/byEmail/{email}")
	public ResponseEntity<?> findByEmail(@PathVariable String email) {
		try {
			return new ResponseEntity<>(userRepository.findByEmail(email), HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>(new RESTError(1, "Exception occures: " + e.getMessage()),
					HttpStatus.BAD_REQUEST);
		}

	}

	@RequestMapping(method = RequestMethod.GET, path = "/byDateOfBirth")
	public ResponseEntity<?> findByDateOfBirth(@RequestParam LocalDate dateOfBirth) {
		try {
			return new ResponseEntity<>(userRepository.findByDateOfBirth(dateOfBirth), HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>(new RESTError(1, "Exception occures: " + e.getMessage()),
					HttpStatus.BAD_REQUEST);
		}

	}

	@RequestMapping(method = RequestMethod.GET, path = "/personalId/{personalId}")
	public ResponseEntity<?> findByPersonalId(@PathVariable Integer personalId) {
		try {
			return new ResponseEntity<>(userRepository.findByPersonalID(personalId), HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>(new RESTError(1, "Exception occures: " + e.getMessage()),
					HttpStatus.BAD_REQUEST);
		}

	}
	
	@RequestMapping(method = RequestMethod.GET, path = "/userNumber/{userNumber}")
	public ResponseEntity<?> findByUserNumber(@PathVariable String userNumber) {
		try {
			return new ResponseEntity<>(userRepository.findByUserNumber(userNumber), HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>(new RESTError(1, "Exception occures: " + e.getMessage()),
					HttpStatus.BAD_REQUEST);
		}

	}
	
	@RequestMapping(method = RequestMethod.GET, path = "/phoneNumber/{phoneNumber}")
	public ResponseEntity<?> findByPhoneNumber(@PathVariable Long phoneNumber) {
		try {
			return new ResponseEntity<>(userRepository.findByPhoneNumber(phoneNumber), HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>(new RESTError(1, "Exception occures: " + e.getMessage()),
					HttpStatus.BAD_REQUEST);
		}

	}
	
	@RequestMapping(method = RequestMethod.GET, path = "/street/{street}")
	public ResponseEntity<?> findByStreet(@PathVariable String street) {
		try {
			return new ResponseEntity<>(userRepository.findByStreet(street), HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>(new RESTError(1, "Exception occures: " + e.getMessage()),
					HttpStatus.BAD_REQUEST);
		}

	}
	
	@RequestMapping(method = RequestMethod.GET, path = "/city/{city}")
	public ResponseEntity<?> findByCity(@PathVariable String city) {
		try {
			return new ResponseEntity<>(userRepository.findByCity(city), HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>(new RESTError(1, "Exception occures: " + e.getMessage()),
					HttpStatus.BAD_REQUEST);
		}

	}
	
	@RequestMapping(method = RequestMethod.GET, path = "/country/{country}")
	public ResponseEntity<?> findByCountry(@PathVariable String country) {
		try {
			return new ResponseEntity<>(userRepository.findByCountry(country), HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>(new RESTError(1, "Exception occures: " + e.getMessage()),
					HttpStatus.BAD_REQUEST);
		}

	}
}
