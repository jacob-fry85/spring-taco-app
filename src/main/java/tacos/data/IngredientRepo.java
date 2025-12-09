package tacos.data;

import org.springframework.data.cassandra.repository.CassandraRepository;
import tacos.Ingredient;


public interface IngredientRepo
        extends CassandraRepository<Ingredient, String> {

}
