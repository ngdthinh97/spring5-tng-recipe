package tng.springframework.recipe.services;


import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import lombok.extern.slf4j.Slf4j;
import tng.springframework.recipe.commands.RecipeCommand;
import tng.springframework.recipe.converters.RecipeCommandToRecipe;
import tng.springframework.recipe.converters.RecipeToRecipeCommand;
import tng.springframework.recipe.domain.Recipe;
import tng.springframework.recipe.repositories.RecipeRepository;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

@Slf4j
@Service
public class RecipeServiceImpl implements RecipeService {

    private final RecipeRepository recipeRepository;
    private final RecipeCommandToRecipe recipeCommandToRecipe;
    private final RecipeToRecipeCommand recipeToRecipeCommand;

    public RecipeServiceImpl(RecipeRepository recipeRepository,RecipeCommandToRecipe recipeCommandToRecipe,
    						  RecipeToRecipeCommand recipeToRecipeCommand) {
        this.recipeRepository = recipeRepository;
        this.recipeCommandToRecipe = recipeCommandToRecipe;
        this.recipeToRecipeCommand =recipeToRecipeCommand;
    }

    @Override
    public Set<Recipe> getRecipes() {
    	log.debug("i am from service");
        Set<Recipe> recipeSet = new HashSet<>();
        recipeRepository.findAll().iterator().forEachRemaining(recipeSet::add);
        return recipeSet;
    }
    
    @Override
    public Recipe findById(Long l) {
    	// TODO Auto-generated method stub
    	Optional<Recipe> recipeOptional = recipeRepository.findById(l);
    	
    	if(!recipeOptional.isPresent()) {
    		throw new RuntimeException("Recipe not found");
    	}
    	
    	return recipeOptional.get();
    }

    
    
	@Override
	@Transactional
	public RecipeCommand findCommandById(Long l) {
		// TODO Auto-generated method stub
		return recipeToRecipeCommand.convert(findById(l));
	}

	@Override
    @Transactional
	public RecipeCommand saveRecipeCommand(RecipeCommand command) {
		// TODO Auto-generated method stub
        Recipe detachedRecipe = recipeCommandToRecipe.convert(command);

        Recipe savedRecipe = recipeRepository.save(detachedRecipe);
        log.debug("Saved RecipeId:" + savedRecipe.getId());
        return recipeToRecipeCommand.convert(savedRecipe);
	}

	@Override
	public void deleteById(Long idToDelete) {
		// TODO Auto-generated method stub
		recipeRepository.deleteById(idToDelete);
	}
    
    
    
}
