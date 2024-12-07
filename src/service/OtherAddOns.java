package service;

import java.util.HashMap;
import java.util.Map;

public class OtherAddOns {
    private Map<String, Map<String, Double>> otherAddonsMap;

    public OtherAddOns(){
        otherAddonsMap = new HashMap<>();
        otherAddonsMap.put("DRINKS", new HashMap<>());
        otherAddonsMap.put("DESSERT", new HashMap<>());

        otherAddonsMap.get("DRINKS").put("PEPSI", 17d);
        otherAddonsMap.get("DRINKS").put("7 -UP", 19d);
        otherAddonsMap.get("DRINKS").put("COKE", 20d);
        otherAddonsMap.get("DESSERT").put("LAVA CAKE", 95d);
        otherAddonsMap.get("DESSERT").put("CHOCOLATE BROWNIE", 86d);
    }

    public Boolean isAvailable(String type, String name){
        return otherAddonsMap.containsKey(type) && otherAddonsMap.get(type).containsKey(name);
    }

    public Double getPrice(String type, String name){
        return isAvailable(type, name)?otherAddonsMap.get(type).get(name):null;
    }
}
