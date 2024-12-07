package entity;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import service.BaseService;
import service.OtherAddOnsService;
import service.ToppingService;

public class Order {
    private String pizzaBase;
    private String sauce;
    private List<String> topping;
    private HashMap<String, String> otherAddOns;

    private Double billAmount;
    private String bill;

    private BaseService baseService;
    private ToppingService toppingService;
    private OtherAddOnsService otherAddOnsService;

    Order(String pizzaBase, String sauce, List<String> topping, HashMap<String, String> otherAddOns){
        this.pizzaBase = pizzaBase;
        this.sauce = sauce;
        this.topping = topping;
        this.otherAddOns = otherAddOns;
        this.bill = "";
        this.billAmount = 0d;

        baseService = new BaseService();
        toppingService = new ToppingService();
        otherAddOnsService = new OtherAddOnsService();
    }

    public String getPizzaBase(){
        return new String(pizzaBase);
    }

    public String getSauce(){
        return new String(sauce);
    }

    public List<String> getTopping(){
        return new ArrayList<>(topping);
    }

    public Map<String, String> getOtherAddOns(){
        return new HashMap<>(otherAddOns);
    }

    public void calculateBill(){
        Double baseAmount = baseService.getPrice(pizzaBase);
        
        Double toppingAmount = 0d;
        String toppingBill;

        if(topping.size()==1){
            toppingAmount = toppingService.getPrice(topping.get(0));
            toppingBill = toppingAmount+"";
        } else{
            toppingBill = "(";
            for(int i=0;i<topping.size()-1;i++){
                Double currentToppingAmount = toppingService.getPrice(topping.get(i));
                toppingAmount = toppingAmount + currentToppingAmount;
                toppingBill += currentToppingAmount + "+";
            }
            toppingAmount = toppingAmount + toppingService.getPrice(topping.get(topping.size()-1));
            toppingBill += toppingService.getPrice(topping.get(topping.size()-1)) + ")";
        }
        

        Double addOnsAmount = 0d;
        String addOnsBill = "";
        for(String type:otherAddOns.keySet()){
            if(!otherAddOns.get(type).isEmpty()){
                if(!addOnsBill.isBlank())addOnsBill += " + ";
                Double currentAddOnPrice = otherAddOnsService.getPrice(type, otherAddOns.get(type));
                addOnsAmount = addOnsAmount + currentAddOnPrice;
                addOnsBill += currentAddOnPrice; 
            }
        }
        
        this.billAmount = baseAmount+toppingAmount+addOnsAmount;
        this.bill = baseAmount +" (base) + "+ toppingBill+" (topping) +"+addOnsBill;
    }

    public String getBill(){
        return new String(bill);
    }

    public Double getBillAmount(){
        return billAmount;
    }
}
