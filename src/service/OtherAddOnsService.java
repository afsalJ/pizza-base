package service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class OtherAddOnsService {
    private Map<String, Map<String, Double>> otherAddonsMap;

    public OtherAddOnsService(){
        otherAddonsMap = new HashMap<>();
        otherAddonsMap.put("DRINK", new HashMap<>());
        otherAddonsMap.put("DESSERT", new HashMap<>());

        otherAddonsMap.get("DRINK").put("PEPSI", 17d);
        otherAddonsMap.get("DRINK").put("7 -UP", 19d);
        otherAddonsMap.get("DRINK").put("COKE", 20d);
        otherAddonsMap.get("DESSERT").put("LAVA CAKE", 95d);
        otherAddonsMap.get("DESSERT").put("CHOCOLATE BROWNIE", 86d);
    }

    public Boolean isAvailable(String type, String name){
        type = type.toUpperCase();
        name = name.toUpperCase();
        return otherAddonsMap.containsKey(type) && otherAddonsMap.get(type).containsKey(name);
    }

    public Double getPrice(String type, String name){
        type = type.toUpperCase();
        name = name.toUpperCase();
        return isAvailable(type, name)?otherAddonsMap.get(type).get(name):null;
    }

    public List<String> getAllTypes(){
        List<String> types = new ArrayList<>();
        for(String type:otherAddonsMap.keySet()){
            types.add(type);
        }
        return types;
    }
}
