package project_library.repositories;

import java.time.LocalDate;
import java.util.List;

import org.springframework.data.repository.CrudRepository;

import project_library.enteties.UserEntity;
import project_library.enteties.dto.UserDTO;

public interface UserRepository extends CrudRepository<UserEntity, Integer> {

//	void save(UserDTO user);
	public List<UserEntity> findByName(String name);
	public List<UserEntity> findByLastname(String lastname);
	public UserEntity findByUsername(String username);
	public UserEntity findByEmail(String email);
	public List<UserEntity> findByDateOfBirth(LocalDate dateOfBirth);
	public UserEntity findByPersonalID(Integer personalId);
	public UserEntity findByUserNumber(String userNumber);
	public UserEntity findByPhoneNumber(Long phoneNumber);
	public List<UserEntity> findByStreet(String street);
	public List<UserEntity> findByCity(String city);
	public List<UserEntity> findByCountry(String country);
}
