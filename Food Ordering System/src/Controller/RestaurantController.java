package Controller;

import Service.RestaurantService;
import Models.Restaurant;
import Exceptions.RestaurantNotFoundException;

public class RestaurantController {
    private final RestaurantService restaurantService;

    public RestaurantController(RestaurantService restaurantService) {
        this.restaurantService = restaurantService;
    }

    public void addRestaurant(Restaurant restaurant) {
        restaurantService.addRestaurant(restaurant);
    }

    public void updateMenu(String restaurantId, String item, double price) {
        try {
            restaurantService.updateMenu(restaurantId, item, price);
        } catch (RestaurantNotFoundException e) {
            System.err.println("Error: " + e.getMessage());
        }
    }
}