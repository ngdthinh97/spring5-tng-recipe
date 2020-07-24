package tng.springframework.recipe.repositories;

import java.util.Optional;

import org.springframework.data.repository.CrudRepository;

import tng.springframework.recipe.domain.UnitOfMeasure;

public interface UnitOfMeansureRepository extends CrudRepository<UnitOfMeasure, Long>{

	Optional<UnitOfMeasure> findByDescription(String description);
}
