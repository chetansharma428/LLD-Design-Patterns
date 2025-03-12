// service/strategy/SelectionStrategy.java
package Service.strategy;

import Models.Restaurant;
import java.util.List;
import java.util.Map;

public interface SelectionStrategy {
    Restaurant selectRestaurant(List<Restaurant> restaurants, Map<String, Integer> items);
}
