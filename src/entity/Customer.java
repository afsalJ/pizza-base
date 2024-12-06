package entity;

public class Customer {
    private Staff staff;
    public Customer(){
        staff = new Staff();

        giveOrder();
        getBill();
    }

    public void giveOrder(){
        String staffResponse= staff.takeOrder();
        if(!staffResponse.isBlank()){
            System.out.println(staffResponse);
        }
    }

    public void getBill(){
        String staffResponse = staff.generateBill();
        if(staffResponse.isBlank()){
            System.out.println(staffResponse);
        }
    }
}
