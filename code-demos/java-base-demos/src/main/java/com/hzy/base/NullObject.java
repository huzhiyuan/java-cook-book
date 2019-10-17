package com.hzy.base;

/**
 * @Author:huzhiyuan
 * @Date: 2019/10/17
 * @Description:
 * @version: 1.1
 */
public class NullObject {
    public static void main(String[] args) {
        String type = "xxx";
        Customer customer = null;
        if(type == "xxx"){
            customer = new Customer();
        }else{
            customer = new NullCustomer();
        }
        System.out.println(customer.getName());
    }
}
interface nullable{
    boolean isNull();
}
class NullCustomer extends Customer{
    @Override
    public String getName(){
        return "null";
    }
    @Override
    public BillingPlan getPlan(){
        return new BillingPlan();
    }

    @Override
    public PymentHistory getPymentHistory() {
        return new PymentHistory();
    }

    @Override
    public boolean isNull() {
        return true;
    }
}
class Customer implements nullable{
    private String name;
    private BillingPlan billingPlan;
    private PymentHistory pymentHistory;
    public String getName(){
          return this.name;
    }
    public BillingPlan getPlan(){
        return this.billingPlan;
    }

    public PymentHistory getPymentHistory() {
        return pymentHistory;
    }

    static Customer newNull(){
        return new NullCustomer();
    }

    @Override
    public boolean isNull() {
        return false;
    }
}
class PymentHistory{

}
class BillingPlan{

}
