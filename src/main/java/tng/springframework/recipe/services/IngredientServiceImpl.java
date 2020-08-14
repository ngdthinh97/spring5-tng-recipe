package tng.springframework.recipe.services;

import java.util.Optional;

import org.springframework.stereotype.Service;

import lombok.extern.slf4j.Slf4j;
import tng.springframework.recipe.commands.IngredientCommand;
import tng.springframework.recipe.converters.IngredientToIngredientCommand;
import tng.springframework.recipe.domain.Recipe;
import tng.springframework.recipe.repositories.RecipeRepository;

@Slf4j
@Service
public class IngredientServiceImpl implements IngredientService{
	

    private final IngredientToIngredientCommand ingredientToIngredientCommand;
    private final RecipeRepository recipeRepository;

    public IngredientServiceImpl(IngredientToIngredientCommand ingredientToIngredientCommand, RecipeRepository recipeRepository) {
        this.ingredientToIngredientCommand = ingredientToIngredientCommand;
        this.recipeRepository = recipeRepository;
    }
    
    @Override
	public IngredientCommand findByRecipeIdAndIngredientId(Long recipeId, Long ingredientId) {
		// TODO Auto-generated method stub
		 Optional<Recipe> recipeOptional = recipeRepository.findById(recipeId);

	        if (!recipeOptional.isPresent()){
	            log.error("recipe id not found. Id: " + recipeId);
	        }

	        Recipe recipe = recipeOptional.get(); // get the recipe from the Optional

	        Optional<IngredientCommand> ingredientCommandOptional = recipe.getIngredients().stream()  // Stream nhan tat ca du lieu duoc tra ve tu Optional
	                .filter(ingredient -> ingredient.getId().equals(ingredientId))	// Stream filter loc ra cac gia tri do va so sanh  
	                .map( ingredient -> ingredientToIngredientCommand.convert(ingredient)).findFirst(); // Stream map tao ra 1 cau truc du lieu, anh xa toi tung doi tuong convert no va lay phan tu dau tien trong mang vs findFirst()

	        if(!ingredientCommandOptional.isPresent()){
	            log.error("Ingredient id not found: " + ingredientId);
	        }

	        return ingredientCommandOptional.get();
	}
	
}
