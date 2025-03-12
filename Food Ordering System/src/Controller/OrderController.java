package Controller;

import Service.OrderService;
import Models.Order;
import Exceptions.OrderAssignmentException;

public class OrderController {
    private final OrderService orderService;

    public OrderController(OrderService orderService) {
        this.orderService = orderService;
    }

    public String placeOrder(Order order) {
        try {
            Order placedOrder = orderService.placeOrder(order);
            return "Order assigned to: " + placedOrder.getAssignedRestaurantId();
        } catch (OrderAssignmentException e) {
            return "Failed to assign order: " + e.getMessage();
        }
    }

    public void completeOrder(String restaurantId) {
        orderService.completeOrder(restaurantId);
    }
}