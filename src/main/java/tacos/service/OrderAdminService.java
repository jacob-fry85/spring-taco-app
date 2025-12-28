package tacos.service;

import org.springframework.stereotype.Service;
import tacos.data.OrderRepo;

@Service
public class OrderAdminService {
    OrderRepo orderRepo;
    public void deleteOrders() {
        orderRepo.deleteAll();
    }
}
