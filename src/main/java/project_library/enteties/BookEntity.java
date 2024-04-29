package project_library.enteties;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonManagedReference;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

@Entity
@JsonIgnoreProperties({ "hibernateLazyInitializer", "handler" })

public class BookEntity {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "id")
	private Integer id;
	@NotNull(message = "Name of book must be provided")
	private String name;
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy")
	private LocalDate yearOfPublication;
	@NotNull(message = "Serial number must be provided")
	@Size(min = 5, max = 10, message = "Serial number must be between {min} and {max} characters long")
	private String serialNumber;
	@NotNull(message = "Must have a number of copies")
	@Min(value = 1, message = "Number of copies must be at least 1")
	private Integer numberOfCopies;

	@ManyToOne(cascade = CascadeType.REFRESH, fetch = FetchType.LAZY)
	@JoinColumn(name = "genre")
	@JsonBackReference
	private GenreEntity genre;

	@ManyToMany(fetch = FetchType.LAZY, cascade = CascadeType.REFRESH)
	@JoinTable(name = "Writer_Book", joinColumns = { @JoinColumn(name = "book_id") }, inverseJoinColumns = {
			@JoinColumn(name = "writer_id") })
	@JsonIgnore
	protected List<WriterEntity> writers = new ArrayList<WriterEntity>();

	@ManyToOne(cascade = CascadeType.REFRESH, fetch = FetchType.LAZY)
	@JoinColumn(name = "user")
//	@JsonBackReference
	private UserEntity user;

	@ManyToOne(cascade = CascadeType.REFRESH, fetch = FetchType.LAZY)
	@JoinColumn(name = "reservation")
//	@JsonBackReference
	private ReservationEntity reservation;

	public BookEntity() {
		super();
	}

	public BookEntity(Integer id, String name, LocalDate yearOfPublication, String serialNumber, Integer numberOfCopies,
			GenreEntity genre, List<WriterEntity> writers, UserEntity user, ReservationEntity reservation) {
		super();
		this.id = id;
		this.name = name;
		this.yearOfPublication = yearOfPublication;
		this.serialNumber = serialNumber;
		this.numberOfCopies = numberOfCopies;
		this.genre = genre;
		this.writers = writers;
		this.user = user;
		this.reservation = reservation;
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

	public String getSerialNumber() {
		return serialNumber;
	}

	public void setSerialNumber(String serialNumber) {
		this.serialNumber = serialNumber;
	}

	public Integer getNumberOfCopies() {
		return numberOfCopies;
	}

	public void setNumberOfCopies(Integer numberOfCopies) {
		this.numberOfCopies = numberOfCopies;
	}

	public GenreEntity getGenre() {
		return genre;
	}

	public void setGenre(GenreEntity genre) {
		this.genre = genre;
	}

	public List<WriterEntity> getWriters() {
		return writers;
	}

	public void setWriters(List<WriterEntity> writers) {
		this.writers = writers;
	}

	public UserEntity getUser() {
		return user;
	}

	public void setUser(UserEntity user) {
		this.user = user;
	}

	public ReservationEntity getReservation() {
		return reservation;
	}

	public void setReservation(ReservationEntity reservation) {
		this.reservation = reservation;
	}

	public LocalDate getYearOfPublication() {
		return yearOfPublication;
	}

	public void setYearOfPublication(LocalDate yearOfPublication) {
		this.yearOfPublication = yearOfPublication;
	}

}
