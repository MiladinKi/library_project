package project_library.repositories;

import org.springframework.data.repository.CrudRepository;

import project_library.enteties.ReservationEntity;

public interface ReservationRepository extends CrudRepository<ReservationEntity, Integer> {

}
