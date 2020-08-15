package tng.springframework.recipe.converters;

import org.springframework.core.convert.converter.Converter;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Component;

import tng.springframework.recipe.commands.IngredientCommand;
import tng.springframework.recipe.domain.Ingredient;
import tng.springframework.recipe.domain.Recipe;

@Component
public class IngredientCommandToIngredient implements Converter<IngredientCommand, Ingredient>{

	private final UnitOfMeasureCommandToUnitOfMeasure uomConverter;
	
	
	public IngredientCommandToIngredient(UnitOfMeasureCommandToUnitOfMeasure uomConverter) {
		super();
		this.uomConverter = uomConverter;
	}

    @Nullable
	@Override
	public Ingredient convert(IngredientCommand source) {
		// TODO Auto-generated method stub
    	  if (source == null) {
              return null;
          }

          final Ingredient ingredient = new Ingredient();
          ingredient.setId(source.getId());
          if(source.getRecipeId() != null){
              Recipe recipe = new Recipe();
              recipe.setId(source.getRecipeId());
              ingredient.setRecipe(recipe);
              recipe.addIngredient(ingredient);
          }
          ingredient.setAmount(source.getAmount());
          ingredient.setDescription(source.getDescription());
          ingredient.setUom(uomConverter.convert(source.getUom()));
          return ingredient;
	}
	
	
}
