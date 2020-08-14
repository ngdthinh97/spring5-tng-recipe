package tng.springframework.recipe.converters;

import org.springframework.core.convert.converter.Converter;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Component;

import lombok.Synchronized;
import tng.springframework.recipe.commands.NotesCommand;
import tng.springframework.recipe.domain.Notes;

@Component
public class NotesToNotesCommand implements Converter<Notes, NotesCommand>{
  
	@Synchronized
    @Nullable
	@Override
	public NotesCommand convert(Notes source) {
		// TODO Auto-generated method stub
		if (source == null) {
            return null;
        }

        final NotesCommand notesCommand = new NotesCommand();
        notesCommand.setId(source.getId());
        notesCommand.setRecipeNotes(source.getRecipeNotes());
        return notesCommand;
	}

	
}
