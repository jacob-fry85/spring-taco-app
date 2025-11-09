package tacos.data;

import tacos.TacoOrder;

public interface OrderRepo {
    TacoOrder save(TacoOrder order);
}
