package tacos.data;

import org.springframework.data.repository.Repository;
import tacos.Ingredient;

import java.util.Optional;

public interface IngredientRepo extends Repository<Ingredient, String> {

}
