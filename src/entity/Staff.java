package entity;

import java.util.*;

import service.BaseService;
import service.SauceService;
import service.ToppingService;

public class Staff {
    private Scanner sc;
    private Order order;

    private BaseService baseService;
    private SauceService sauceService;
    private ToppingService toppingService;

    public Staff(){
        sc = new Scanner(System.in);

        baseService = new BaseService();
        sauceService = new SauceService();
        toppingService = new ToppingService();
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
        order = new Order(pizzaBase, sauce, topping);

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

        return message;
    }

    public String generateBill(){
        Double billAmount = order.getBillAmount();
        String bill = order.getBill();

        return "Amount to be paid : " + (billAmount) + " RS [ Calculation : "+bill+ " = "+ billAmount +" ]";
    }
}
