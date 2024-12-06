package service;

import java.util.HashMap;
import java.util.Map;

public class BaseService {
    private Map<String, Double> bases;
    
    public BaseService(){
        bases = new HashMap<>();
        bases.put("Regular".toUpperCase(), 50d);
        bases.put("Whole wheat".toUpperCase(), 75d);
    }

    public Double getPrice(String base){
        if(!isAvailable(base)){
            return null;
        }
        return bases.get(base.toUpperCase());
    }

    public Boolean isAvailable(String base){
        return bases.containsKey(base.toUpperCase());
    }
}
