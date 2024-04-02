package project_library.enteties;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

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
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

@Entity
@JsonIgnoreProperties({ "hibernateLazyInitializer", "handler" })
public class WriterEntity {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "id")
	private Integer id;
	@NotNull(message = "Writers name must be provided")
	@Size(min = 2, max = 20, message = "Writers name must be between {min} and {max} characters long")
	private String name;
	@NotNull(message = "Writers lastname must be provided")
	@Size(min = 2, max = 20, message = "Writers lastname must be between {min} and {max} characters long")
	private String lastname;
	private String biography;

	@ManyToMany(fetch = FetchType.LAZY, cascade = CascadeType.REFRESH)
	@JoinTable(name = "Writer_Book", joinColumns = { @JoinColumn(name = "writer_id") }, inverseJoinColumns = {
			@JoinColumn(name = "book_id") })
	protected Set<BookEntity> books = new HashSet<BookEntity>();

//	@OneToMany(mappedBy = "writer", cascade = CascadeType.REFRESH, fetch = FetchType.LAZY)
//	@JsonIgnore
//	private List<BookEntity> books = new ArrayList<>();

	public WriterEntity() {
		super();
	}

	public WriterEntity(Integer id, String name, String lastname, String biography, Set<BookEntity> books) {
		super();
		this.id = id;
		this.name = name;
		this.lastname = lastname;
		this.biography = biography;
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

	public String getBiography() {
		return biography;
	}

	public void setBiography(String biography) {
		this.biography = biography;
	}

	public Set<BookEntity> getBooks() {
		return books;
	}

	public void setBooks(Set<BookEntity> books) {
		this.books = books;
	}

}
