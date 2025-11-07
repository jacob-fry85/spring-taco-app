package tacos.data;

import tacos.Ingredient;

import java.util.Optional;

public interface IngredientRepo {
    Iterable<Ingredient> findAll();
    Optional<Ingredient> findById(String id);
    Ingredient save(Ingredient ingredient);
}
