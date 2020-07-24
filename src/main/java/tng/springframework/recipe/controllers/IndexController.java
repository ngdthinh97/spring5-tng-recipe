package tng.springframework.recipe.controllers;

import java.util.Optional;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import tng.springframework.recipe.domain.Category;
import tng.springframework.recipe.domain.UnitOfMeasure;
import tng.springframework.recipe.repositories.CategoryRepository;
import tng.springframework.recipe.repositories.UnitOfMeansureRepository;

@Controller
public class IndexController {
	
	private CategoryRepository categoryRepository;
	private UnitOfMeansureRepository unitOfMeasureRepository;
	
	public IndexController(CategoryRepository categoryRepository, UnitOfMeansureRepository unitOfMeasureRepository) {
		this.categoryRepository = categoryRepository;
		this.unitOfMeasureRepository = unitOfMeasureRepository;
	}



	@RequestMapping({"","/","index","index.html"})
	public String getIndexPage() {
		
		Optional<Category> categoryOptional = categoryRepository.findByDescription("American");
		Optional<UnitOfMeasure> unitOfMeasureOptional = unitOfMeasureRepository.findByDescription("Teaspoon");
			
		System.out.println("Cat id is:" + categoryOptional.get().getId());
		System.out.println("UOM id is:" + unitOfMeasureOptional.get().getId());
		return "index";
	}
}
