package tacos;

import jakarta.persistence.Entity;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.data.annotation.Id;
import tacos.data.IngredientRepo;

@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor(access= AccessLevel.PRIVATE,force = true)
public class Ingredient {
    @Id
    private final String id;
    private final String name;
    private final Type type;

    public enum Type {
        WRAP, PROTEIN, VEGGIES, CHEESE, SAUCE
    }

}
