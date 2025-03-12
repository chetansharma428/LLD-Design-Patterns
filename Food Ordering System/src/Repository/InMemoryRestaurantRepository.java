package Repository;

import Models.Restaurant;

import java.util.*;

// In-memory implementation
public class InMemoryRestaurantRepository implements RestaurantRepository {
    private Map<Long, Restaurant> restaurants = new HashMap<>();

    @Override
    public void save(Restaurant restaurant) {
        restaurants.put(restaurant.getId(), restaurant);
    }

    @Override
    public Optional<Restaurant> findById(Long id) {
        return Optional.ofNullable(restaurants.get(id));
    }

    @Override
    public List<Restaurant> findAll() {
        return new ArrayList<>(restaurants.values());
    }
}
