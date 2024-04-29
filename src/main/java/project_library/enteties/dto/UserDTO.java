package project_library.enteties.dto;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Version;
import jakarta.validation.constraints.Digits;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import project_library.enteties.BookEntity;
import project_library.enteties.RoleUser;


public class UserDTO {
	@NotNull(message = "Name must be provided")
	@Size(min = 2, max = 20, message = "Name must be between {min} and {max} characters long")
	private String name;
	@NotNull(message = "Lastname must be provided")
	@Size(min = 2, max = 20, message = "Lastname must be between {min} and {max} characters long")
	private String lastname;
	@NotNull(message = "Username must be provided")
	@Size(min = 6, max = 15, message = "Username must be between {min} and {max} characters long")
	private String username;
	@NotNull(message = "Email must be provided")
	@Pattern(regexp = "^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@"
			+ "[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$", message = "Email is not valid")
	private String email;
	@NotNull(message = "Password must be provided")
	@Size(min = 8, max = 20, message = "Password must be between {min} and {max} characters long")
	private String password;
	@NotNull(message = "Confirmed password must be provided")
	@Size(min = 8, max = 20, message = "Confirmed password must be between {min} and {max} characters long")
	private String confirmPassword;
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy")
	private LocalDate dateOfBirth;
	@Column(unique = true)
	@NotNull(message = "Personal ID must be provided")
	@Digits(integer = 10, fraction = 0, message = "Personal ID must have exactly 10 digits")
	private Integer personalID;
	@Column(unique = true)
	@Digits(integer = 10, fraction = 0, message = "Personal ID must have exactly 10 digits")
	private Long phoneNumber;
	@Column(unique = true)
	@NotNull(message = "User number must be provided")
	@Size(min = 8, max = 8, message = "User number must contain exactly 8 characters")
	private String userNumber;
	@NotNull(message = "Name of street must be provided")
	private String street;
	@NotNull(message = "Name of city must be provided")
	private String city;
	@NotNull(message = "Name of country must be provided")
	private String country;
	@Version
	private Integer version;

	private RoleUser userRole;

	@OneToMany(mappedBy = "user", cascade = CascadeType.REFRESH, fetch = FetchType.LAZY)
	@JsonIgnore
	private List<BookEntity> books = new ArrayList<>();
	
	

	public UserDTO() {
		super();
	}

	public UserDTO(
			@NotNull(message = "Name must be provided") @Size(min = 2, max = 20, message = "Name must be between {min} and {max} characters long") String name,
			@NotNull(message = "Lastname must be provided") @Size(min = 2, max = 20, message = "Lastname must be between {min} and {max} characters long") String lastname,
			@NotNull(message = "Username must be provided") @Size(min = 6, max = 15, message = "Username must be between {min} and {max} characters long") String username,
			@NotNull(message = "Email must be provided") @Pattern(regexp = "^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$", message = "Email is not valid") String email,
			@NotNull(message = "Password must be provided") @Size(min = 8, max = 20, message = "Password must be between {min} and {max} characters long") String password,
			@NotNull(message = "Confirmed password must be provided") @Size(min = 8, max = 20, message = "Confirmed password must be between {min} and {max} characters long") String confirmPassword,
			LocalDate dateOfBirth,
			@NotNull(message = "Personal ID must be provided") @Digits(integer = 10, fraction = 0, message = "Personal ID must have exactly 10 digits") Integer personalID,
			@Digits(integer = 10, fraction = 0, message = "Personal ID must have exactly 10 digits") Long phoneNumber,
			@NotNull(message = "User number must be provided") @Size(min = 8, max = 8, message = "User number must contain exactly 8 characters") String userNumber,
			@NotNull(message = "Name of street must be provided") String street,
			@NotNull(message = "Name of city must be provided") String city,
			@NotNull(message = "Name of country must be provided") String country, Integer version, RoleUser userRole,
			List<BookEntity> books) {
		super();
		this.name = name;
		this.lastname = lastname;
		this.username = username;
		this.email = email;
		this.password = password;
		this.confirmPassword = confirmPassword;
		this.dateOfBirth = dateOfBirth;
		this.personalID = personalID;
		this.phoneNumber = phoneNumber;
		this.userNumber = userNumber;
		this.street = street;
		this.city = city;
		this.country = country;
		this.version = version;
		this.userRole = userRole;
		this.books = books;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getLastname() {
		return lastname;
	}

	public void setLastname(String lastname) {
		this.lastname = lastname;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getConfirmPassword() {
		return confirmPassword;
	}

	public void setConfirmPassword(String confirmPassword) {
		this.confirmPassword = confirmPassword;
	}

	public LocalDate getDateOfBirth() {
		return dateOfBirth;
	}

	public void setDateOfBirth(LocalDate dateOfBirth) {
		this.dateOfBirth = dateOfBirth;
	}


	public Integer getPersonalID() {
		return personalID;
	}

	public void setPersonalID(Integer personalID) {
		this.personalID = personalID;
	}

	public Long getPhoneNumber() {
		return phoneNumber;
	}

	public void setPhoneNumber(Long phoneNumber) {
		this.phoneNumber = phoneNumber;
	}

	public String getUserNumber() {
		return userNumber;
	}

	public void setUserNumber(String userNumber) {
		this.userNumber = userNumber;
	}

	public String getStreet() {
		return street;
	}

	public void setStreet(String street) {
		this.street = street;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getCountry() {
		return country;
	}

	public void setCountry(String country) {
		this.country = country;
	}

	public Integer getVersion() {
		return version;
	}

	public void setVersion(Integer version) {
		this.version = version;
	}

	public RoleUser getUserRole() {
		return userRole;
	}

	public void setUserRole(RoleUser userRole) {
		this.userRole = userRole;
	}

	public List<BookEntity> getBooks() {
		return books;
	}

	public void setBooks(List<BookEntity> books) {
		this.books = books;
	}



}
