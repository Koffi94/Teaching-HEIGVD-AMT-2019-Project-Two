package ch.heigvd.amt.project2.repositories;

import ch.heigvd.amt.project2.entities.ScreeningEntity;
import org.springframework.data.repository.CrudRepository;

public interface IScreeningRepository extends CrudRepository<ScreeningEntity, Long> {
}
