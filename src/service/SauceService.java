package service;

import java.util.ArrayList;
import java.util.List;

public class SauceService {
    private List<String> sauces;

    public SauceService(){
        sauces = new ArrayList<>();
        sauces.add("Marinara sauce".toUpperCase());
        sauces.add("Pesto sauce".toUpperCase());
    }

    public Boolean isAvailable(String sauceName){
        return sauces.contains(sauceName.toUpperCase());
    }
}
