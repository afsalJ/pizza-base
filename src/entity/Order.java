package entity;

import java.util.ArrayList;
import java.util.List;

import service.BaseService;
import service.ToppingService;

public class Order {
    private String pizzaBase;
    private String sauce;
    private List<String> topping;

    private Double billAmount;
    private String bill;

    private BaseService baseService;
    private ToppingService toppingService;

    Order(String pizzaBase, String sauce, List<String> topping){
        this.pizzaBase = pizzaBase;
        this.sauce = sauce;
        this.topping = topping;
        this.bill = "";
        this.billAmount = 0d;

        baseService = new BaseService();
        toppingService = new ToppingService();
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

    public void calculateBill(){
        Double baseAmount = baseService.getPrice(pizzaBase);
        Double toppingAmount = 0d;
        String toppingBill = "(";
        for(int i=0;i<topping.size()-1;i++){
            Double currentToppingAmount = toppingService.getPrice(topping.get(i));
            toppingAmount = toppingAmount + currentToppingAmount;
            toppingBill = currentToppingAmount + "+";
        }
        toppingAmount = toppingAmount + toppingService.getPrice(topping.get(topping.size()-1));
        toppingBill = toppingService.getPrice(topping.get(topping.size()-1)) + ")";
        
        this.billAmount = baseAmount+toppingAmount;
        this.bill = baseAmount +" (base) "+ toppingBill+" (topping) ";
    }

    public String getBill(){
        return new String(bill);
    }

    public Double getBillAmount(){
        return billAmount;
    }
}
