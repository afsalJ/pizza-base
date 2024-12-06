package entity;

import java.util.HashMap;
import java.util.Map;

public class Base {
    private Map<String, Double> bases = new HashMap<>();
    Base(){
        bases.put("Regular", 50d);
        bases.put("Whole wheat", 75d);
    }

    public String addBase(String base, Double price){
        if(bases.containsKey(base)){
            return "Base : "+base+" is already available";
        } else if(price<0){
            return "Price must be greater than zero";
        }
        return "Base added successfully!";
    }
}
