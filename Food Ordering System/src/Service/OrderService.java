package Service;

import Models.Order;
import Models.Restaurant;
import Repository.RestaurantRepository;
import Repository.OrderRepository;
import Service.strategy.SelectionStrategy;
import Exceptions.OrderAssignmentException;
import java.util.*;

public class OrderService {
    private final RestaurantRepository restaurantRepository;
    private final OrderRepository orderRepository;
    private SelectionStrategy strategy;

    public OrderService(
            RestaurantRepository restaurantRepository,
            OrderRepository orderRepository,
            SelectionStrategy strategy
    ) {
        this.restaurantRepository = restaurantRepository;
        this.orderRepository = orderRepository;
        this.strategy = strategy;
    }

    public void setStrategy(SelectionStrategy strategy) {
        this.strategy = strategy;
    }

    public Order placeOrder(Order order) throws OrderAssignmentException {
        List<Restaurant> restaurants = restaurantRepository.findAll();
        Restaurant selectedRestaurant = strategy.selectRestaurant(restaurants, order.getItems());
        if (selectedRestaurant == null) {
            throw new OrderAssignmentException("No restaurant can fulfill the order.");
        }
        selectedRestaurant.acceptOrder();
        order.setAssignedRestaurantId(selectedRestaurant.getId());
        orderRepository.save(order);
        restaurantRepository.save(selectedRestaurant);
        return order;
    }

    public void completeOrder(String restaurantId) {
        Optional<Restaurant> restaurantOpt = restaurantRepository.findById(restaurantId);
        restaurantOpt.ifPresent(restaurant -> {
            restaurant.completeOrder();
            restaurantRepository.save(restaurant);
        });
    }
}