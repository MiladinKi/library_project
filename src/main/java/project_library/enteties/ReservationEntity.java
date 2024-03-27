package project_library.enteties;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;

@Entity
@JsonIgnoreProperties({ "hibernateLazyInitializer", "handler" })
public class ReservationEntity {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer id;
	private LocalDate dateOfIssue;
	private Integer numberOfDaysRetenetionTheBook;
	private LocalDate returnDate;

	@OneToMany(mappedBy = "reservation", cascade = CascadeType.REFRESH, fetch = FetchType.LAZY)
	@JsonIgnore
	private List<BookEntity> books = new ArrayList<>();

	public ReservationEntity() {
		super();
	}

	public ReservationEntity(Integer id, LocalDate dateOfIssue, Integer numberOfDaysRetenetionTheBook,
			LocalDate returnDate, List<BookEntity> books) {
		super();
		this.id = id;
		this.dateOfIssue = dateOfIssue;
		this.numberOfDaysRetenetionTheBook = numberOfDaysRetenetionTheBook;
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

	public Integer getNumberOfDaysRetenetionTheBook() {
		return numberOfDaysRetenetionTheBook;
	}

	public void setNumberOfDaysRetenetionTheBook(Integer numberOfDaysRetenetionTheBook) {
		this.numberOfDaysRetenetionTheBook = numberOfDaysRetenetionTheBook;
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
