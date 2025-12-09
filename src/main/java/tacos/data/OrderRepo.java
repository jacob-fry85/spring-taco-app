package tacos.data;

import org.springframework.data.cassandra.repository.CassandraRepository;
import tacos.TacoOrder;

import java.util.UUID;


public interface OrderRepo
        extends CassandraRepository<TacoOrder, UUID> {
}
