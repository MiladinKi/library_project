package project_library.enteties;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonManagedReference;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;

@Entity
@JsonIgnoreProperties({ "hibernateLazyInitializer", "handler" })
public class ReservationEntity {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer id;
	@NotNull(message = "Date of issue must be provided")
	@JsonFormat(shape = JsonFormat.Shape.STRING,
			pattern = "dd-MM-yyyy")
	private LocalDate dateOfIssue;
	@NotNull(message = "Number of reservation days must be provided")
	@Min(value = 1, message = "Number of reservation days must be at least 1")
	private Integer numberOfReservationDays;
	@JsonFormat(shape = JsonFormat.Shape.STRING,
			pattern = "dd-MM-yyyy")
	private LocalDate returnDate;

	@OneToMany(mappedBy = "reservation", cascade = CascadeType.REFRESH, fetch = FetchType.LAZY)
//	@JsonManagedReference
	private List<BookEntity> books = new ArrayList<>();

	public ReservationEntity() {
		super();
	}

	public ReservationEntity(Integer id, LocalDate dateOfIssue, Integer numberOfReservationDays, LocalDate returnDate,
			List<BookEntity> books) {
		super();
		this.id = id;
		this.dateOfIssue = dateOfIssue;
		this.numberOfReservationDays = numberOfReservationDays;
		this.returnDate = returnDate;
		this.books = books;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public LocalDate getDateOfIssue() {
		return dateOfIssue;
	}

	public void setDateOfIssue(LocalDate dateOfIssue) {
		this.dateOfIssue = dateOfIssue;
	}

	public Integer getNumberOfReservationDays() {
		return numberOfReservationDays;
	}

	public void setNumberOfReservationDays(Integer numberOfReservationDays) {
		this.numberOfReservationDays = numberOfReservationDays;
	}

	public LocalDate getReturnDate() {
		return returnDate;
	}

	public void setReturnDate(LocalDate returnDate) {
		this.returnDate = returnDate;
	}

	public List<BookEntity> getBooks() {
		return books;
	}

	public void setBooks(List<BookEntity> books) {
		this.books = books;
	}

}
