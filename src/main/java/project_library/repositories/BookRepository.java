package project_library.repositories;

import java.time.LocalDate;
import java.util.List;

import org.springframework.data.repository.CrudRepository;

import project_library.enteties.BookEntity;

public interface BookRepository extends CrudRepository<BookEntity, Integer> {
	public List<BookEntity> findByName(String name);
	public List<BookEntity> findByYearOfPublication(LocalDate year);
	public BookEntity findBySerialNumber(String number);
}
