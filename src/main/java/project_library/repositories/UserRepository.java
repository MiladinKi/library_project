package project_library.repositories;

import org.springframework.data.repository.CrudRepository;

import project_library.enteties.UserEntity;

public interface UserRepository extends CrudRepository<UserEntity, Integer> {

}
