package Repository;

import Models.Order;
import java.util.*;

public interface OrderRepository {
    void save(Order order);
    Optional<Order> findById(long id);
}

