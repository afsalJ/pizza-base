package service;

import java.util.HashMap;
import java.util.Map;

public class ToppingService {
    private Map<String, Double> toppings;

    public ToppingService(){
        toppings = new HashMap<>();
        toppings.put("Mozzarella cheese".toUpperCase(), 30d);
        toppings.put("Cheddar cheese".toUpperCase(), 35d);
        toppings.put("Spinach".toUpperCase(), 20d);
        toppings.put("Corn".toUpperCase(), 15d);
        toppings.put("Mushroom".toUpperCase(), 15d);
        toppings.put("Chicken".toUpperCase(), 50d);
        toppings.put("Pepperoni".toUpperCase(), 45d);
    }

    public Double getPrice(String toppingName){
        return isAvailable(toppingName)?toppings.get(toppingName.toUpperCase()):null;
    }

    public Boolean isAvailable(String toppingName){
        return toppings.containsKey(toppingName.toUpperCase());
    }
}
