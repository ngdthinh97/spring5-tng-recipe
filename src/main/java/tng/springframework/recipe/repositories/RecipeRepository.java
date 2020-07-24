package tng.springframework.recipe.repositories;

import java.util.Optional;

import org.springframework.data.repository.CrudRepository;

import tng.springframework.recipe.domain.Recipe;

public interface RecipeRepository extends CrudRepository<Recipe, Long>{
	
	Optional<Recipe> findByDescription(String description);
}
