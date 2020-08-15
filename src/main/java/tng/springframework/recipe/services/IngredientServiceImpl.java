package tng.springframework.recipe.services;

import java.util.Optional;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import lombok.extern.slf4j.Slf4j;
import tng.springframework.recipe.commands.IngredientCommand;
import tng.springframework.recipe.converters.IngredientCommandToIngredient;
import tng.springframework.recipe.converters.IngredientToIngredientCommand;
import tng.springframework.recipe.domain.Ingredient;
import tng.springframework.recipe.domain.Recipe;
import tng.springframework.recipe.repositories.RecipeRepository;
import tng.springframework.recipe.repositories.UnitOfMeasureRepository;

@Slf4j
@Service
public class IngredientServiceImpl implements IngredientService{
	
	private final IngredientCommandToIngredient ingredientCommandToIngredient;
    private final IngredientToIngredientCommand ingredientToIngredientCommand;
    private final RecipeRepository recipeRepository;
    private final UnitOfMeasureRepository unitOfMeasureRepository;
    

    public IngredientServiceImpl(IngredientToIngredientCommand ingredientToIngredientCommand, 
    							RecipeRepository recipeRepository,
    							IngredientCommandToIngredient ingredientCommandToIngredient,
    							UnitOfMeasureRepository unitOfMeasureRepository) {
    	
        this.ingredientToIngredientCommand = ingredientToIngredientCommand;
        this.recipeRepository = recipeRepository;
        this.ingredientCommandToIngredient =ingredientCommandToIngredient;
        this.unitOfMeasureRepository=unitOfMeasureRepository;
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



	@Override
    @Transactional
	public IngredientCommand saveIngredientCommand(IngredientCommand command) {
		// TODO Auto-generated method stub
        Optional<Recipe> recipeOptional = recipeRepository.findById(command.getRecipeId());
        
        if(!recipeOptional.isPresent()){

          
            log.error("Recipe not found for id: " + command.getRecipeId());
            return new IngredientCommand();
        } else {
            Recipe recipe = recipeOptional.get();

            Optional<Ingredient> ingredientOptional = recipe
                    .getIngredients()
                    .stream()
                    .filter(ingredient -> ingredient.getId().equals(command.getId()))
                    .findFirst();

            if(ingredientOptional.isPresent()){
                Ingredient ingredientFound = ingredientOptional.get();
                ingredientFound.setDescription(command.getDescription());
                ingredientFound.setAmount(command.getAmount());
                ingredientFound.setUom(unitOfMeasureRepository
                        .findById(command.getUom().getId())
                        .orElseThrow(() -> new RuntimeException("UOM NOT FOUND"))); 
            } else {
                //add new Ingredient
            	Ingredient ingredient = ingredientCommandToIngredient.convert(command);
                ingredient.setRecipe(recipe);
                recipe.addIngredient(ingredient);
                
            }

            Recipe savedRecipe = recipeRepository.save(recipe);
            
            Optional<Ingredient> savedIngredientOptional = savedRecipe.getIngredients().stream()
                    .filter(recipeIngredients -> recipeIngredients.getId().equals(command.getId()))
                    .findFirst();

            //check by description
            if(!savedIngredientOptional.isPresent()){
                //not totally safe... But best guess
                savedIngredientOptional = savedRecipe.getIngredients().stream()
                        .filter(recipeIngredients -> recipeIngredients.getDescription().equals(command.getDescription()))
                        .filter(recipeIngredients -> recipeIngredients.getAmount().equals(command.getAmount()))
                        .filter(recipeIngredients -> recipeIngredients.getUom().getId().equals(command.getUom().getId()))
                        .findFirst();
            }
            
            //to do check for fail
            return ingredientToIngredientCommand.convert(savedIngredientOptional.get());
            
        }
    
	}
	
}
