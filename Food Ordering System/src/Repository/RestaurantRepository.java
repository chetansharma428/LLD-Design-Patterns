package Repository;

import Models.Restaurant;
import java.util.*;

public interface RestaurantRepository {
    void save(Restaurant restaurant);
    Optional<Restaurant> findById(Long id);
    List<Restaurant> findAll();
}

