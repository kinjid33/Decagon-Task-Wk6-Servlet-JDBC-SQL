package week6task.ecommercesite2.entity;

import java.math.BigDecimal;

public class Cart extends Product {

//    This class extends Product class and therefore inherits the fields in the product class
//    variable to track quantity of product to be bought
    private double quantity;
//      no args constructor
    public Cart() {}

//    getter for quantity
    public double getQuantity() {
        return quantity;
    }
//setter for quantity
    public void setQuantity(double quantity) {
        this.quantity = quantity;
    }
}
