package project_library.repositories;

import java.util.List;

import org.springframework.data.repository.CrudRepository;


import project_library.enteties.WriterEntity;

public interface WriterRepository extends CrudRepository<WriterEntity, Integer> {
	public List<WriterEntity> findByName(String name);
	public List<WriterEntity> findByLastname(String lastname);
}
