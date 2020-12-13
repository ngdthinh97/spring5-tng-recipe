package tng.springframework.recipe.controllers;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.model;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;
import static org.mockito.ArgumentMatchers.anyLong;
import org.junit.Before;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import tng.springframework.Exceptions.NotFoundExceptions;
import tng.springframework.recipe.domain.Recipe;
import tng.springframework.recipe.services.RecipeService;

public class RecipeControllerTest {

	@Mock
	@InjectMocks
	RecipeService recipeService;

	RecipeController controller;

	MockMvc mockMvc;

	@Before
	public void setUp() throws Exception {
		MockitoAnnotations.initMocks(this);

		controller = new RecipeController(recipeService);
		mockMvc = MockMvcBuilders.standaloneSetup(controller).build();
	}
    @Test
    public void testGetRecipeNotFound() throws Exception {

        when(recipeService.findById(anyLong())).thenThrow(NotFoundExceptions.class);

        mockMvc.perform(get("/recipe/1/show"))
                .andExpect(status().isNotFound())
                .andExpect(view().name("404error"));
    }
	@Test
	public void testGetRecipe() throws Exception {

		Recipe recipe = new Recipe();
		recipe.setId(1L);
		recipeService.deleteById(recipe.getId());
		Recipe recipeid = recipeService.findById(Long.valueOf(1));
		Long id = recipeid.getId();
		System.out.println();
		when(recipeService.findById(anyLong())).thenReturn(recipe);

		mockMvc.perform(get("/recipe/1/show")).andExpect(status().isOk()).andExpect(view().name("recipe/show"))
				.andExpect(model().attributeExists("recipe"));
	}

	@Test
	public void testGetRecipeNumberFormatException() throws Exception {

		mockMvc.perform(get("/recipe/asdf/show"))
			.andExpect(status().isBadRequest())
			.andExpect(view().name("400error"));
	}
	
	
}
