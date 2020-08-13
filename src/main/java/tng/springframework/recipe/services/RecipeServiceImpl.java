package tng.springframework.recipe.services;


import org.springframework.stereotype.Service;

import lombok.extern.slf4j.Slf4j;
import tng.springframework.recipe.domain.Recipe;
import tng.springframework.recipe.repositories.RecipeRepository;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

@Slf4j
@Service
public class RecipeServiceImpl implements RecipeService {

    private final RecipeRepository recipeRepository;

    public RecipeServiceImpl(RecipeRepository recipeRepository) {
        this.recipeRepository = recipeRepository;
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
    
}
