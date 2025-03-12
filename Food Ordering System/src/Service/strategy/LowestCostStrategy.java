package Service.strategy;

import Models.Restaurant;

import java.util.List;
import java.util.Map;

public class LowestCostStrategy implements SelectionStrategy {
    @Override
    public Restaurant selectRestaurant(List<Restaurant> restaurants, Map<String, Integer> items) {
        Restaurant selectedRestaurant = null;
        double minCost = Double.MAX_VALUE;

        // Iterate through all restaurants to find the one with the lowest cost
        for (Restaurant restaurant : restaurants) {
            if (restaurant.canAcceptOrder()) { // Check if the restaurant can accept orders
                double cost = restaurant.calculateOrderCost(items);
                if (cost != -1 && cost < minCost) { // Check if the cost is valid and lower than the current minimum
                    minCost = cost;
                    selectedRestaurant = restaurant;
                }
            }
        }
        return selectedRestaurant;
    }
}
