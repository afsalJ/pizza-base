package entity;

import java.util.ArrayList;
import java.util.List;

public class Sauce {
    private List<String> sauces;
    Sauce(){
        sauces = new ArrayList<>();
        sauces.add("Marinara sauce");
        sauces.add("Pesto saice");
    }

    public String addSauce(String sauceName){
        if(isAvailable(sauceName)){
            return sauceName+" is already available!";
        }

        String validationResponse = validateSauce(sauceName);
        if(!validationResponse.isEmpty()){
            return validationResponse;
        }
        sauces.add(sauceName);
        return "sauce added successfully!";
    }

    public String deleteSauce(String sauceName){
        if(!isAvailable(sauceName)){
            return sauceName+" is not available";
        }
        sauces.remove(sauceName);
        return "Deleted successfully";
    }

    public List<String> getAllSauces(){
        if(sauces.isEmpty()){
            return new ArrayList<>();
        }
        return new ArrayList<>(sauces);
    }

    public Boolean isAvailable(String sauceName){
        return sauces.contains(sauceName);
    }

    public String validateSauce(String sauceName){
        if(sauceName.trim().isEmpty()){
            return "Sauce name must not be empty!";
        }
        return "";
    }
}
