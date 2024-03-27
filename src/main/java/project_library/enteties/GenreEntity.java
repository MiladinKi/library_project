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

@Entity
public class GenreEntity {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer id;
	private String genre;

	@OneToMany(mappedBy = "genre", cascade = CascadeType.REFRESH, fetch = FetchType.LAZY)
	@JsonIgnore
	private List<BookEntity> books = new ArrayList<>();

	public GenreEntity() {
		super();
	}

	public GenreEntity(Integer id, String genre, List<BookEntity> books) {
		super();
		this.id = id;
		this.genre = genre;
		this.books = books;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getGenre() {
		return genre;
	}

	public void setGenre(String genre) {
		this.genre = genre;
	}

	public List<BookEntity> getBooks() {
		return books;
	}

	public void setBooks(List<BookEntity> books) {
		this.books = books;
	}

}
