import Repository.*;
import Service.*;
import Controller.*;
import Models.*;
import Service.strategy.LowestCostStrategy;

import java.util.*;

public class Main {
    public static void main(String[] args) {
        // Initialize repositories
        RestaurantRepository restaurantRepo = new InMemoryRestaurantRepository();
        OrderRepository orderRepo = new InMemoryOrderRepository();

        // Initialize services with dependencies
        RestaurantService restaurantService = new RestaurantService(restaurantRepo);
        OrderService orderService = new OrderService(
                restaurantRepo, orderRepo, new LowestCostStrategy()
        );

        // Initialize controllers
        RestaurantController restaurantController = new RestaurantController(restaurantService);
        OrderController orderController = new OrderController(orderService);

        // Onboard restaurants
        Restaurant r1 = new Restaurant(1L, "R1", 5, 4.5);
        r1.addOrUpdateMenuItem("Veg Biryani", 100);
        restaurantController.addRestaurant(r1);

        Restaurant r2 = new Restaurant(2L, "R2", 5, 4.0);
        r2.addOrUpdateMenuItem("Idli", 10);
        restaurantController.addRestaurant(r2);

        // Place an order
        Map<String, Integer> items = new HashMap<>();
        items.put("Idli", 3);
        Order order = new Order(1L, "User1", items);
        String result = orderController.placeOrder(order);
        System.out.println(result); // Output: "Order assigned to: R2"
    }
}



// ---------------------------------------------
 // old code

//import java.util.*;
//
//// Restaurant class represents a restaurant with its properties and functionalities
//class Restaurant {
//    private String name; // Name of the restaurant
//    private int maxOrders; // Maximum number of orders the restaurant can handle at a time
//    private double rating; // Rating of the restaurant (out of 5)
//    private int currentOrders; // Current number of orders being processed
//    private Map<String, Double> menu; // Menu of the restaurant (item -> price)
//
//    // Constructor to initialize a restaurant
//    public Restaurant(String name, int maxOrders, double rating) {
//        this.name = name;
//        this.maxOrders = maxOrders;
//        this.rating = rating;
//        this.currentOrders = 0;
//        this.menu = new HashMap<>();
//    }
//
//    // Getter methods
//    public String getName() {
//        return name;
//    }
//
//    public int getMaxOrders() {
//        return maxOrders;
//    }
//
//    public double getRating() {
//        return rating;
//    }
//
//    public int getCurrentOrders() {
//        return currentOrders;
//    }
//
//    public Map<String, Double> getMenu() {
//        return menu;
//    }
//
//    // Check if the restaurant can accept more orders
//    public boolean canAcceptOrder() {
//        return currentOrders < maxOrders;
//    }
//
//    // Accept an order (increment current orders)
//    public void acceptOrder() {
//        currentOrders++;
//    }
//
//    // Complete an order (decrement current orders)
//    public void completeOrder() {
//        if (currentOrders > 0) {
//            currentOrders--;
//        }
//    }
//
//    // Add or update an item in the menu
//    public void addOrUpdateMenuItem(String item, double price) {
//        menu.put(item, price);
//    }
//
//    // Calculate the total cost of an order
//    public double calculateOrderCost(Map<String, Integer> items) {
//        double totalCost = 0.0;
//        for (Map.Entry<String, Integer> entry : items.entrySet()) {
//            String item = entry.getKey();
//            int quantity = entry.getValue();
//            if (menu.containsKey(item)) {
//                totalCost += menu.get(item) * quantity; // Add item cost to total
//            } else {
//                return -1; // Item not found in the menu
//            }
//        }
//        return totalCost;
//    }
//}
//
//// Order class represents an order placed by a user
//class Order {
//    private String user; // Name of the user placing the order
//    private Map<String, Integer> items; // Items in the order (item -> quantity)
//    private Restaurant assignedRestaurant; // Restaurant assigned to fulfill the order
//
//    // Constructor to initialize an order
//    public Order(String user, Map<String, Integer> items) {
//        this.user = user;
//        this.items = items;
//    }
//
//    // Getter methods
//    public String getUser() {
//        return user;
//    }
//
//    public Map<String, Integer> getItems() {
//        return items;
//    }
//
//    public Restaurant getAssignedRestaurant() {
//        return assignedRestaurant;
//    }
//
//    // Set the restaurant assigned to fulfill the order
//    public void setAssignedRestaurant(Restaurant restaurant) {
//        this.assignedRestaurant = restaurant;
//    }
//}
//
//// SelectionStrategy interface defines the strategy for selecting a restaurant
//interface SelectionStrategy {
//    Restaurant selectRestaurant(List<Restaurant> restaurants, Map<String, Integer> items);
//}
//
//// LowestCostStrategy selects the restaurant with the lowest cost for the order
//class LowestCostStrategy implements SelectionStrategy {
//    @Override
//    public Restaurant selectRestaurant(List<Restaurant> restaurants, Map<String, Integer> items) {
//        Restaurant selectedRestaurant = null;
//        double minCost = Double.MAX_VALUE;
//
//        // Iterate through all restaurants to find the one with the lowest cost
//        for (Restaurant restaurant : restaurants) {
//            if (restaurant.canAcceptOrder()) { // Check if the restaurant can accept orders
//                double cost = restaurant.calculateOrderCost(items);
//                if (cost != -1 && cost < minCost) { // Check if the cost is valid and lower than the current minimum
//                    minCost = cost;
//                    selectedRestaurant = restaurant;
//                }
//            }
//        }
//        return selectedRestaurant;
//    }
//}
//
//// HighestRatingStrategy selects the restaurant with the highest rating
//class HighestRatingStrategy implements SelectionStrategy {
//    @Override
//    public Restaurant selectRestaurant(List<Restaurant> restaurants, Map<String, Integer> items) {
//        Restaurant selectedRestaurant = null;
//        double maxRating = -1;
//
//        // Iterate through all restaurants to find the one with the highest rating
//        for (Restaurant restaurant : restaurants) {
//            if (restaurant.canAcceptOrder()) { // Check if the restaurant can accept orders
//                double cost = restaurant.calculateOrderCost(items);
//                if (cost != -1 && restaurant.getRating() > maxRating) { // Check if the rating is higher than the current maximum
//                    maxRating = restaurant.getRating();
//                    selectedRestaurant = restaurant;
//                }
//            }
//        }
//        return selectedRestaurant;
//    }
//}
//
//// FoodOrderingSystem manages restaurants and orders
//class FoodOrderingSystem {
//    private List<Restaurant> restaurants; // List of all restaurants
//    private SelectionStrategy strategy; // Strategy for selecting a restaurant
//
//    // Constructor to initialize the system
//    public FoodOrderingSystem() {
//        this.restaurants = new ArrayList<>();
//        this.strategy = new LowestCostStrategy(); // Default strategy is lowest cost
//    }
//
//    // Add a restaurant to the system
//    public void addRestaurant(Restaurant restaurant) {
//        restaurants.add(restaurant);
//    }
//
//    // Set the selection strategy (e.g., lowest cost or highest rating)
//    public void setSelectionStrategy(SelectionStrategy strategy) {
//        this.strategy = strategy;
//    }
//
//    // Place an order using the selected strategy
//    public boolean placeOrder(Order order) {
//        Restaurant selectedRestaurant = strategy.selectRestaurant(restaurants, order.getItems());
//        if (selectedRestaurant != null) {
//            selectedRestaurant.acceptOrder(); // Accept the order
//            order.setAssignedRestaurant(selectedRestaurant); // Assign the restaurant to the order
//            System.out.println("Order assigned to: " + selectedRestaurant.getName());
//            return true;
//        } else {
//            System.out.println("Cannot assign the order. No restaurant can fulfill it.");
//            return false;
//        }
//    }
//
//    // Mark an order as completed
//    public void completeOrder(Restaurant restaurant) {
//        restaurant.completeOrder(); // Complete the order
//        System.out.println("Order completed at: " + restaurant.getName());
//    }
//}
//
//public class Main {
//    public static void main(String[] args) {
//
//        // Create restaurants
//        Restaurant r1 = new Restaurant("R1", 5, 4.5);
//        r1.addOrUpdateMenuItem("Veg Biryani", 100);
//        r1.addOrUpdateMenuItem("Chicken Biryani", 150);
//
//        Restaurant r2 = new Restaurant("R2", 5, 4.0);
//        r2.addOrUpdateMenuItem("Idli", 10);
//        r2.addOrUpdateMenuItem("Dosa", 50);
//        r2.addOrUpdateMenuItem("Veg Biryani", 80);
//        r2.addOrUpdateMenuItem("Chicken Biryani", 175);
//
//        Restaurant r3 = new Restaurant("R3", 1, 4.9);
//        r3.addOrUpdateMenuItem("Idli", 15);
//        r3.addOrUpdateMenuItem("Dosa", 30);
//        r3.addOrUpdateMenuItem("Gobi Manchurian", 150);
//        r3.addOrUpdateMenuItem("Chicken Biryani", 175);
//
//        // Create food ordering system
//        FoodOrderingSystem system = new FoodOrderingSystem();
//        system.addRestaurant(r1);
//        system.addRestaurant(r2);
//        system.addRestaurant(r3);
//
//        // Place orders
//        Map<String, Integer> order1Items = new HashMap<>();
//        order1Items.put("Idli", 3);
//        order1Items.put("Dosa", 1);
//        Order order1 = new Order("Ashwin", order1Items);
//        system.placeOrder(order1); // Should assign to R3 (lowest cost)
//
//        Map<String, Integer> order2Items = new HashMap<>();
//        order2Items.put("Idli", 3);
//        order2Items.put("Dosa", 1);
//        Order order2 = new Order("Harish", order2Items);
//        system.placeOrder(order2); // Should assign to R2 (R3 is full)
//
//        // Change strategy to highest rating
//        system.setSelectionStrategy(new HighestRatingStrategy());
//
//        Map<String, Integer> order3Items = new HashMap<>();
//        order3Items.put("Veg Biryani", 3);
//        order3Items.put("Dosa", 1);
//        Order order3 = new Order("Shruthi", order3Items);
//        system.placeOrder(order3); // Should assign to R1 (highest rating)
//
//        // Complete an order
//        system.completeOrder(r3);
//
//        // Place another order
//        Map<String, Integer> order4Items = new HashMap<>();
//        order4Items.put("Idli", 3);
//        order4Items.put("Dosa", 1);
//        Order order4 = new Order("Harish", order4Items);
//        system.placeOrder(order4); // Should assign to R3 (now available)
//    }
//}