package tng.springframework.recipe.repositories;

import org.springframework.data.repository.CrudRepository;

import tng.springframework.recipe.domain.Category;

public interface CategoryRepository extends CrudRepository<Category, Long>{

}
