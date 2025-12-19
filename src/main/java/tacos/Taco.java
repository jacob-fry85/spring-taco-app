package tacos;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;

import java.util.Date;
import java.util.List;

@Data
@Entity
public class Taco {
    @Id
    private Long id;

    @Id
    private Date createdAt = new Date();

    @NotNull
    @Size(min=5, message="Name must beat least 5 characters long.")
    private String name;

    @Size(min=1, message="You must choose at least 1 ingredient")
    private List<IngredientUDT> ingredients;

    public void addIngredient(Ingredient ingredient) {
        this.ingredients.add(TacoUDRUtils.toIngredientUDT((ingredient)));
    }
}
