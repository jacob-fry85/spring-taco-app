package tacos.data;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import tacos.TacoOrder;

import java.util.UUID;

@Repository
public interface OrderRepo
        extends CrudRepository<TacoOrder, Long> {
}
