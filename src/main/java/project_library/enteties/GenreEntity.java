package project_library.enteties;

import java.awt.print.Book;
import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.validation.constraints.NotNull;

@Entity
public class GenreEntity {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer id;
	@NotNull(message = "Name of genre must be provided")
	private String nameOFGenre;

	@OneToMany(mappedBy = "genre", cascade = CascadeType.REFRESH, fetch = FetchType.LAZY)
	@JsonIgnore
	private List<BookEntity> books = new ArrayList<>();

	public GenreEntity() {
		super();
	}

	public GenreEntity(Integer id, String nameOFGenre, List<BookEntity> books) {
		super();
		this.id = id;
		this.nameOFGenre = nameOFGenre;
		this.books = books;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getNameOFGenre() {
		return nameOFGenre;
	}

	public void setNameOFGenre(String nameOFGenre) {
		this.nameOFGenre = nameOFGenre;
	}

	public List<BookEntity> getBooks() {
		return books;
	}

	public void setBooks(List<BookEntity> books) {
		this.books = books;
	}

}
