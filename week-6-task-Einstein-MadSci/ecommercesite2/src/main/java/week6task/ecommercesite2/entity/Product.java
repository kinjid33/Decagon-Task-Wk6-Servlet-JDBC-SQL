package week6task.ecommercesite2.entity;

import java.math.BigDecimal;

public class Product {
//    fields to correspond with product table in database
    private int productId;
    private String category;
    private String name;
    private double price;
    private String image;

//    no args constructor
    public Product() {
    }

//    all args constructor
    public Product(int productId, String category, String name, double price, String image) {
        this.productId = productId;
        this.category = category;
        this.name = name;
        this.price = price;
        this.image = image;
    }

//    setters and getter for the fields
    public int getProductId() {
        return productId;
    }

    public void setProductId(int productId) {
        this.productId = productId;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

//    override toString method
    @Override
    public String toString() {
        return "Product{" +
                "productId=" + productId +
                ", category='" + category + '\'' +
                ", price='" + price + '\'' +
                ", imageUrl='" + image + '\'' +
                '}';
    }
}
