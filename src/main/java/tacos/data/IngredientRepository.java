package tacos.data;

import org.springframework.data.repository.CrudRepository;
import tacos.entities.Ingredient;

/**
 * JPA interface for Ingredient. Extends CrudRepository
 * ID in Ingredient class has a type of String.
 * @author pawel
 *
 */
public interface IngredientRepository extends CrudRepository<Ingredient, String> {	
}
