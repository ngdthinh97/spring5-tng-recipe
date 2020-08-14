package tng.springframework.recipe.services;

import tng.springframework.recipe.commands.IngredientCommand;

public interface IngredientService {
	IngredientCommand findByRecipeIdAndIngredientId(Long recipeId, Long ingredientId);
}
