package tng.springframework.recipe.converters;

import org.springframework.core.convert.converter.Converter;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Component;

import lombok.Synchronized;
import tng.springframework.recipe.commands.NotesCommand;
import tng.springframework.recipe.domain.Notes;

@Component
public class NotesCommandToNotes implements Converter<NotesCommand, Notes>{
  
	@Synchronized
    @Nullable
	@Override
	public Notes convert(NotesCommand source) {
		// TODO Auto-generated method stub
        if(source == null) {
            return null;
        }

        final Notes notes = new Notes();
        notes.setId(source.getId());
        notes.setRecipeNotes(source.getRecipeNotes());
        return notes;
	}

	
}
