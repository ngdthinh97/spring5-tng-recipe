package tng.springframework.recipe.repositories;

import org.springframework.data.repository.CrudRepository;

import tng.springframework.recipe.domain.Recipe;

public interface RecipeRepository extends CrudRepository<Recipe, Long>{
	
}
