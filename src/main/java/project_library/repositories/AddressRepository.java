package project_library.repositories;

import org.springframework.data.repository.CrudRepository;

import project_library.enteties.AddressEntity;

public interface AddressRepository extends CrudRepository<AddressEntity, Integer> {

}
