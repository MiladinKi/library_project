package project_library.repositories;

import org.springframework.data.repository.CrudRepository;

import project_library.enteties.BookEntity;

public interface BookRepository extends CrudRepository<BookEntity, Integer> {

}
