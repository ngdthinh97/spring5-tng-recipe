package tng.springframework.recipe.services;

import java.util.Set;

import tng.springframework.recipe.commands.UnitOfMeasureCommand;

public interface UnitOfMeasureService {
	
	Set<UnitOfMeasureCommand> listAllUoms();
}
