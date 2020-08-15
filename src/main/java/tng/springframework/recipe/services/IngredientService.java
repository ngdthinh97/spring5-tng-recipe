package tng.springframework.recipe.services;

import tng.springframework.recipe.commands.IngredientCommand;

public interface IngredientService {
	IngredientCommand findByRecipeIdAndIngredientId(Long recipeId, Long ingredientId);
	
	IngredientCommand saveIngredientCommand(IngredientCommand command);
	
	void deleteById(Long recipeId, Long idToDelete);
}
