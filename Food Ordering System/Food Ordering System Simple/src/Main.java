import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

class Restaurant{
    //attributes
    private Long id;
    private String name;
    private int maxOrder;
    private Map<String, Double> Menu;
    private Double Rating;
    private int currentOrder;

    //constructor to initialize the restaurant
    public Restaurant(String name, Double Rating, int maxOrder){
        this.name = name;
        this.Rating = Rating;
        this.currentOrder = 0;
        this.Menu = new HashMap<>();
        this.maxOrder = maxOrder;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getMaxOrder() {
        return maxOrder;
    }

    public void setMaxOrder(int maxOrder) {
        this.maxOrder = maxOrder;
    }

    public Map<String, Double> getMenu() {
        return Menu;
    }

    public void setMenu(Map<String, Double> menu) {
        Menu = menu;
    }

    public Double getRating() {
        return Rating;
    }

    public void setRating(Double rating) {
        Rating = rating;
    }

    public int getCurrentOrder() {
        return currentOrder;
    }

    public void setCurrentOrder(int currentOrder) {
        this.currentOrder = currentOrder;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    // for checking whether the restaurant can accept orders or not
    public boolean canAcceptOrders(){
        return maxOrder > currentOrder;
    }

    public void OrderAccepted(){
        currentOrder++;
    }

    public void orderCompleted(){
        currentOrder--;
    }

    //for adding or updating the menu
    public void addOrUpdateMenu(String name, double price){
        Menu.put(name, price);
    }

    public double calculateCartAmount(Map<String, Integer> cart){
        double total = 0.0;
        for (Map.Entry<String, Integer> entry : cart.entrySet()) {
            String key = entry.getKey();
            int quantity = entry.getValue();
            if(Menu.containsKey(key)){
                total += Menu.get(key) * quantity;
            }else{
                return -1;
            }
        }
        return total;
    }


}

class Order{
    private String name;
    private Long id;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Map<String, Integer> getItems() {
        return items;
    }

    public void setItems(Map<String, Integer> items) {
        this.items = items;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Restaurant getAssignedRestaurant() {
        return assignedRestaurant;
    }

    public void setAssignedRestaurant(Restaurant assignedRestaurant) {
        this.assignedRestaurant = assignedRestaurant;
    }

    private Map<String, Integer> items;
    private Restaurant assignedRestaurant;

    public Order(String name, Map<String, Integer> items){
        this.name = name;
        this.items = items;
    }
}

interface SelectionStrategy{
    Restaurant selectRestaurant(List<Restaurant> restaurants, Map<String, Integer> items);
}

class LowestSelectionStrategy implements SelectionStrategy{
    public Restaurant selectRestaurant(List<Restaurant> restaurants, Map<String, Integer> items){
        Restaurant selectedRestaurant = null;
        double minPrice = Double.MAX_VALUE;
        for(Restaurant restaurant : restaurants){
            if(restaurant.canAcceptOrders()){
                double price = restaurant.calculateCartAmount(items);
                if(price != -1 && price < minPrice){
                    minPrice = price;
                    selectedRestaurant = restaurant;
                }
            }
        }
        return selectedRestaurant;
    }
}

class HighestRatingStrategy implements SelectionStrategy{
    public Restaurant selectRestaurant(List<Restaurant> restaurants, Map<String, Integer> items){
        Restaurant selectedRestaurant = null;
        double highestRating = Double.MIN_VALUE;
        for(Restaurant restaurant : restaurants){
            if(restaurant.canAcceptOrders()){
                double price = restaurant.calculateCartAmount(items);
                double rating = restaurant.getRating();
                if( price != -1 && rating > highestRating){
                    highestRating = rating;
                    selectedRestaurant = restaurant;
                }
            }
        }
        return selectedRestaurant;
    }
}

class FoodOrderingSystem{
    List<Restaurant> restaurants;
    SelectionStrategy selectionStrategy;

    public FoodOrderingSystem(){
        this.restaurants = new ArrayList<>();
        this.selectionStrategy = new LowestSelectionStrategy(); //by default strategy
    }

    public void addRestaurant(Restaurant restaurant){
        restaurants.add(restaurant);
    }

    public void setSelectionStrategy(SelectionStrategy selectionStrategy){
        this.selectionStrategy = selectionStrategy;
    }

    public boolean placeOrder(Order order){
        Restaurant selectedRestaurant = selectionStrategy.selectRestaurant(restaurants, order.getItems());
        if(selectedRestaurant != null){
            selectedRestaurant.OrderAccepted();
            order.setAssignedRestaurant(selectedRestaurant);
            System.out.println("Order accepted to: " + selectedRestaurant.getName());
            return true;
        }
        else{
            System.out.println("Order could not be placed, No restaurant can fulfill your order");
            return false;
        }
    }

    public void completeOrder(Restaurant restaurant){
        restaurant.orderCompleted();
        System.out.println("Order completed to: " + restaurant.getName());
    }
}

public class Main {
    public static void main(String[] args) {
        Restaurant r1 = new Restaurant("R1", 4.6, 5);
        r1.addOrUpdateMenu("veg Pakoda", 100);
        r1.addOrUpdateMenu("veg noodles", 140);
        r1.addOrUpdateMenu("veg biryani", 120);
        Restaurant r2 = new Restaurant("R2", 4.0, 5);
        r2.addOrUpdateMenu("chicken biryani", 160);
        r2.addOrUpdateMenu("chicken momo", 70);


        FoodOrderingSystem system = new FoodOrderingSystem();
        system.addRestaurant(r1);
        system.addRestaurant(r2);

        Map<String, Integer> items = new HashMap<>();
        items.put("veg Pakoda", 100);
        items.put("veg noodles", 140);
        items.put("veg biryani", 120);

        Order order1 = new Order("Chetan", items);

        system.placeOrder(order1);

        system.setSelectionStrategy( new HighestRatingStrategy());
        system.completeOrder(r1);
    }
}