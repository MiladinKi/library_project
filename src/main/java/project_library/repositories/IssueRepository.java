package project_library.repositories;

import org.springframework.data.repository.CrudRepository;

import project_library.enteties.IssueEntity;

public interface IssueRepository extends CrudRepository<IssueEntity, Integer> {
	

}
