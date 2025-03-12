package Service.strategy;

import Models.Restaurant;

import java.util.List;
import java.util.Map;

public class HighestRatingStrategy implements SelectionStrategy{
    @Override
    public Restaurant selectRestaurant(List<Restaurant> restaurants, Map<String, Integer> items) {
        Restaurant selectedRestaurant = null;
        double maxRating = -1;

        // Iterate through all restaurants to find the one with the highest rating
        for (Restaurant restaurant : restaurants) {
            if (restaurant.canAcceptOrder()) { // Check if the restaurant can accept orders
                double cost = restaurant.calculateOrderCost(items);
                if (cost != -1 && restaurant.getRating() > maxRating) { // Check if the rating is higher than the current maximum
                    maxRating = restaurant.getRating();
                    selectedRestaurant = restaurant;
                }
            }
        }
        return selectedRestaurant;
    }
}
