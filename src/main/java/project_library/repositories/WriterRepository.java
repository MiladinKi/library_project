package project_library.repositories;

import org.springframework.data.repository.CrudRepository;

import project_library.enteties.WriterEntity;

public interface WriterRepository extends CrudRepository<WriterEntity, Integer> {

}
