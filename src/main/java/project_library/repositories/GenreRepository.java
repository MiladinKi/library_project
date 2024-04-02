package project_library.repositories;

import org.springframework.data.repository.CrudRepository;

import project_library.enteties.GenreEntity;

public interface GenreRepository extends CrudRepository<GenreEntity, Integer> {

}
