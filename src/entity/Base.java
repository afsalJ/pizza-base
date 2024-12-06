package entity;

import java.util.HashMap;
import java.util.Map;

public class Base {
    private Map<String, Double> bases;
    Base(){
        bases = new HashMap<>();
        bases.put("Regular", 50d);
        bases.put("Whole wheat", 75d);
    }

    public String addBase(String base, Double price){
        if(isAvailableBase(base)){
            return "Base : "+base+" is already available!";
        } 
        String validationMessage = validateBase(base) +"\n"+validatePrice(price);
        if(!validationMessage.isEmpty()){
            return validationMessage;
        }
        bases.put(base, price);
        return "Base added successfully!";
    }

    public String deleteBase(String base){
        if(!isAvailableBase(base)){
            return "No base available with name:"+base;
        }
        bases.remove(base);
        return "Base deleted successfully";
    }

    public String updateBase(String base, Double price){
        if(!isAvailableBase(base)){
            return "No base available with name"+base;
        }
        String validationMessage = validatePrice(price);
        if(!validationMessage.isEmpty()){
            return validationMessage;
        }
        bases.put(base, price);
        return "Base updated successfully";
    }

    public Map<String, Double> getAllBases(){
        if(bases==null || bases.isEmpty()){
            return new HashMap<>();
        }
        return new HashMap<>(bases);
    }

    public String validateBase(String base){
        if(base.trim().equals("")){
            return "Base must not be empty!";
        }
        return "";
    }

    public String validatePrice(Double price){
        if(price<0){
            return "Price must be greater than zero!";
        }
        return "";
    }

    public Boolean isAvailableBase(String base){
        return bases.containsKey(base);
    }
}
