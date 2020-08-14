package tng.springframework.recipe.converters;

import org.springframework.core.convert.converter.Converter;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Component;

import lombok.Synchronized;
import tng.springframework.recipe.commands.CategoryCommand;
import tng.springframework.recipe.domain.Category;

@Component
public class CategoryToCategoryCommand implements Converter<Category, CategoryCommand>{
  
	@Synchronized
    @Nullable
	@Override
	public CategoryCommand convert(Category source) {
		// TODO Auto-generated method stub
		if (source == null) {
            return null;
        }
		
        final CategoryCommand categoryCommand = new CategoryCommand();

        categoryCommand.setId(source.getId());
        categoryCommand.setDescription(source.getDescription());

        return categoryCommand;
	}
	
	
}
