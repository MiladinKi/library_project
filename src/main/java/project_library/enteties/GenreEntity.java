package project_library.enteties;

import java.awt.print.Book;
import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

@Entity
public class GenreEntity {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	@NotNull(message = "Name of genre must be provided")
	@Column(name = "genre name")
	private String nameOfGenre;
	private String describeGenre;

	@OneToMany(mappedBy = "genre", cascade = CascadeType.REFRESH, fetch = FetchType.LAZY)
	@JsonIgnore
	@JsonManagedReference
	private List<BookEntity> books = new ArrayList<>();

	public GenreEntity() {
		super();
	}

	public GenreEntity(Integer id, String nameOfGenre,
			String describeGenre, List<BookEntity> books) {
		super();
		this.id = id;
		this.nameOfGenre = nameOfGenre;
		this.describeGenre = describeGenre;
		this.books = books;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getNameOfGenre() {
		return nameOfGenre;
	}

	public void setNameOfGenre(String nameOFGenre) {
		this.nameOfGenre = nameOFGenre;
	}

	public List<BookEntity> getBooks() {
		return books;
	}

	public void setBooks(List<BookEntity> books) {
		this.books = books;
	}

	public String getDescribeGenre() {
		return describeGenre;
	}

	public void setDescribeGenre(String describeGenre) {
		this.describeGenre = describeGenre;
	}

}
