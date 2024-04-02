package project_library.enteties;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.databind.jsonFormatVisitors.JsonFormatTypes;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Version;
import jakarta.validation.constraints.Digits;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

@Entity
public class UserEntity {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer id;
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
	@Pattern(regexp = "^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@"+ "[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$",
	message = "Email is not valid")
	private String email;
	@NotNull(message = "Password must be provided")
	@Size(min = 8, max = 20, message = "Password must be between {min} and {max} characters long")
	private String password;
	@JsonFormat(
			shape = JsonFormat.Shape.STRING,
			pattern = "dd-MM-yyyy")
	private LocalDate dateOfBirth;
	@NotNull(message = "Personal ID must be provided")
	@Digits(integer = 10, fraction = 0, message = "Personal ID must have exactly 10 digits")
	private Integer personalID;
	@Digits(integer = 10, fraction = 0, message = "Personal ID must have exactly 10 digits")
	private Long phoneNumber;
	@NotNull(message = "User number must be provided")
	@Size(min =  8, max = 8, message = "User number must contain exactly 8 characters")
	private String userNumber;
	@Version
	private Integer version;

	private RoleUser userRole;

	@ManyToOne(cascade = CascadeType.REFRESH, fetch = FetchType.LAZY)
	@JoinColumn(name = "address")
	private AddressEntity address;

	@OneToMany(mappedBy = "user", cascade = CascadeType.REFRESH, fetch = FetchType.LAZY)
	@JsonIgnore
	private List<BookEntity> books = new ArrayList<>();

	public UserEntity() {
		super();
	}

	public UserEntity(Integer id, String name, String lastname, String username, String email, String password,
			LocalDate dateOfBirth, Integer personalID, Long phoneNumber, String userNumber, Integer version,
			RoleUser userRole, AddressEntity address, List<BookEntity> books) {
		super();
		this.id = id;
		this.name = name;
		this.lastname = lastname;
		this.username = username;
		this.email = email;
		this.password = password;
		this.dateOfBirth = dateOfBirth;
		this.personalID = personalID;
		this.phoneNumber = phoneNumber;
		this.userNumber = userNumber;
		this.version = version;
		this.userRole = userRole;
		this.address = address;
		this.books = books;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
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

	public AddressEntity getAddress() {
		return address;
	}

	public void setAddress(AddressEntity address) {
		this.address = address;
	}

	public List<BookEntity> getBooks() {
		return books;
	}

	public void setBooks(List<BookEntity> books) {
		this.books = books;
	}

}
