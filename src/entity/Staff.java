package entity;

import java.util.*;

import service.BaseService;
import service.OtherAddOnsService;
import service.SauceService;
import service.ToppingService;
import utils.StringUtils;

public class Staff {
    private Scanner sc;
    private Order order;

    private BaseService baseService;
    private SauceService sauceService;
    private ToppingService toppingService;
    private OtherAddOnsService otherAddOnsService;

    public Staff(){
        sc = new Scanner(System.in);
        order = null;

        baseService = new BaseService();
        sauceService = new SauceService();
        toppingService = new ToppingService();
        otherAddOnsService = new OtherAddOnsService();
    }

    public String takeOrder(){
        System.out.print("Pizza base: ");
        String pizzaBase = sc.nextLine();

        System.out.print("Sauce: ");
        String sauce = sc.nextLine();

        System.out.print("Topping: ");
        String[] toppingArr = sc.nextLine().split(","); 
        List<String> topping = new ArrayList<>();
        for(String i:toppingArr){
            if(!i.isBlank())topping.add(i.trim());
        }

        HashMap<String, String> otherAddOns = new HashMap<>();
        for(String type:otherAddOnsService.getAllTypes()){
            System.out.print(StringUtils.capitalize(type)+": ");
            otherAddOns.put(type, sc.nextLine());
            if(otherAddOns.get(type)==null) otherAddOns.put(type, "");
        }
        order = new Order(pizzaBase, sauce, topping, otherAddOns);

        String validateResponse = validateOrder(order);
        if(!validateResponse.isEmpty()){
            return validateResponse;
        }
        order.calculateBill();
        return "";
    }

    public String validateOrder(Order order){
        String message = "";

        if(order.getPizzaBase().isBlank()){
            message += "Base must be chosen!\n";
        } else if(!baseService.isAvailable(order.getPizzaBase())){
            message += order.getPizzaBase() +" not found!\n";
        }

        if(!order.getSauce().isBlank() && !sauceService.isAvailable(order.getSauce())){
            message += order.getSauce()+" not found!\n";
        }

        if(order.getTopping().isEmpty()){
            message += "Atleast one topping must be chosen!\n";
        } else{
            for(String eachTopping : order.getTopping()){
                if(!toppingService.isAvailable(eachTopping)){
                    message += eachTopping +" not found!\n";
                }
            }
        }

        Map<String, String> otherAddOns = order.getOtherAddOns();
        for(String type: otherAddOns.keySet()){
            if(!otherAddOns.get(type).isBlank() && !otherAddOnsService.isAvailable(type, otherAddOns.get(type))){
                message += otherAddOns.get(type)+" not found in "+type+"!\n";
            }
        }

        return message;
    }

    public String generateBill(){
        Double billAmount = order.getBillAmount();
        String bill = order.getBill();

        Double discount = getDiscount();
        billAmount-=discount;
        if(discount == 0d){
            return "Amount to be paid : " + (billAmount) + " RS [ Calculation : "+bill+" = "+ billAmount +" ]";
        }
        return "Amount to be paid : " + (billAmount) + " RS [ Calculation : "+bill+ " - "+discount+" (discount) = "+ billAmount +" ]";
    }

    public Double getDiscount(){
        Double discount = 0d;
        if(order!=null && !order.getOtherAddOns().get("DRINK").isBlank() && !order.getOtherAddOns().get("DESSERT").isBlank()){
            discount = order.getBillAmount() * (5.0/100.0);
        }
        return discount;
    }
}
