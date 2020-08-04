package tng.springframework.recipe.controllers;

import java.util.Optional;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import tng.springframework.recipe.domain.Category;
import tng.springframework.recipe.domain.UnitOfMeasure;
import tng.springframework.recipe.repositories.CategoryRepository;
import tng.springframework.recipe.repositories.RecipeRepository;
import tng.springframework.recipe.repositories.UnitOfMeasureRepository;

@Controller
public class IndexController {
	
	private CategoryRepository categoryRepository;
	private UnitOfMeasureRepository unitOfMeasureRepository;
	private RecipeRepository recipeRepository;
	
	public IndexController(CategoryRepository categoryRepository, 
			UnitOfMeasureRepository unitOfMeasureRepository,
				RecipeRepository recipeRepository) {
		this.categoryRepository = categoryRepository;
		this.unitOfMeasureRepository = unitOfMeasureRepository;
		this.recipeRepository = recipeRepository;
	}
	
	@RequestMapping({"","/","index","index.html"})
	public String getIndexPage(Model model) {
		
		model.addAttribute("recipes", recipeRepository.findAll());
		
		return "index";
	}
}
