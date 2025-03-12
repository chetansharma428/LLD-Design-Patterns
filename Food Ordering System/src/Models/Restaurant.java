package Models;

import java.util.HashMap;
import java.util.Map;

public class Restaurant {
    private Long id;
    private String name;
    private int maxOrders;
    private double rating;
    private int currentOrders;
    private Map<String, Double> menu;

    public Restaurant(Long id, String name, int maxOrders, double rating) {
        this.id = id;
        this.name = name;
        this.maxOrders = maxOrders;
        this.rating = rating;
        this.menu = new HashMap<>();
    }

    // Getters and Setters
    public long getId() { return id; }
    public String getName() { return name; }
    public int getMaxOrders() { return maxOrders; }
    public double getRating() { return rating; }
    public int getCurrentOrders() { return currentOrders; }
    public Map<String, Double> getMenu() { return menu; }

    public void addOrUpdateMenuItem(String item, double price) {
        menu.put(item, price);
    }

    public boolean canAcceptOrder() {
        return currentOrders < maxOrders;
    }

    public void acceptOrder() {
        currentOrders++;
    }

    public void completeOrder() {
        if (currentOrders > 0) currentOrders--;
    }

    public double calculateOrderCost(Map<String, Integer> items) {
        double total = 0.0;
        for (Map.Entry<String, Integer> entry : items.entrySet()) {
            String item = entry.getKey();
            int quantity = entry.getValue();
            if (!menu.containsKey(item)) return -1;
            total += menu.get(item) * quantity;
        }
        return total;
    }
}