package tng.springframework.recipe.converters;

import org.springframework.core.convert.converter.Converter;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Component;

import lombok.Synchronized;
import tng.springframework.recipe.commands.IngredientCommand;
import tng.springframework.recipe.domain.Ingredient;

@Component
public class IngredientToIngredientCommand implements Converter<Ingredient, IngredientCommand>{

    private final UnitOfMeasureToUnitOfMeasureCommand uomConverter;

    public IngredientToIngredientCommand(UnitOfMeasureToUnitOfMeasureCommand uomConverter) {
        this.uomConverter = uomConverter;
    }
	
    @Synchronized
    @Nullable	
	@Override
	public IngredientCommand convert(Ingredient source) {
		// TODO Auto-generated method stub
    	 if (source == null) {
             return null;
         }

         IngredientCommand ingredientCommand = new IngredientCommand();
         ingredientCommand.setId(source.getId());
         ingredientCommand.setAmount(source.getAmount());
         ingredientCommand.setDescription(source.getDescription());
         ingredientCommand.setUnitOfMeasure(uomConverter.convert(source.getUom()));
         return ingredientCommand;
	}
	
	
}
