package Models;

import java.util.Map;

public class Order {
    private long id;
    private String user;
    private Map<String, Integer> items;
    private long assignedRestaurantId;

    public Order(long id, String user, Map<String, Integer> items) {
        this.id = id;
        this.user = user;
        this.items = items;
    }

    // Getters and Setters
    public long getId() { return id; }
    public String getUser() { return user; }
    public Map<String, Integer> getItems() { return items; }
    public long getAssignedRestaurantId() { return assignedRestaurantId; }
    public void setAssignedRestaurantId(long restaurantId) {
        this.assignedRestaurantId = restaurantId;
    }
}