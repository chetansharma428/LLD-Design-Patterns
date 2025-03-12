package Service;

import Models.Restaurant;
import Repository.RestaurantRepository;
import Exceptions.RestaurantNotFoundException;
import java.util.Optional;

public class RestaurantService {
    private final RestaurantRepository restaurantRepository;

    public RestaurantService(RestaurantRepository restaurantRepository) {
        this.restaurantRepository = restaurantRepository;
    }

    public void addRestaurant(Restaurant restaurant) {
        restaurantRepository.save(restaurant);
    }

    public void updateMenu(String restaurantId, String item, double price)
            throws RestaurantNotFoundException {
        Optional<Restaurant> restaurantOpt = restaurantRepository.findById(restaurantId);
        if (!restaurantOpt.isPresent()) {
            throw new RestaurantNotFoundException("Restaurant not found: " + restaurantId);
        }
        Restaurant restaurant = restaurantOpt.get();
        restaurant.addOrUpdateMenuItem(item, price);
        restaurantRepository.save(restaurant);
    }
}