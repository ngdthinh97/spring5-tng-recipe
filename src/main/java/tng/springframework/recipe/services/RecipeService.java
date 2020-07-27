package tng.springframework.recipe.services;



import java.util.Set;

import tng.springframework.recipe.domain.Recipe;

/**
 * Created by jt on 6/13/17.
 */
public interface RecipeService {

    Set<Recipe> getRecipes();
}
