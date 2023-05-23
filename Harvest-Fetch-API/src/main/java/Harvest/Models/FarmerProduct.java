package Harvest.Models;

import java.math.BigDecimal;

public class FarmerProduct {
    private int farmerId;
    private int productId;
    private BigDecimal price;
    private boolean organic;
    private boolean isActive;
    private Product product;

    public FarmerProduct(int farmerId, int productId, BigDecimal price,boolean isActive, boolean organic) {
        this.farmerId = farmerId;
        this.productId = productId;
        this.price = price;
        this.isActive = isActive;
        this.organic = organic;
    }

    public FarmerProduct() {
    }


    public int getFarmerId() {
        return farmerId;
    }

    public void setFarmerId(int farmerId) {
        this.farmerId = farmerId;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public boolean isOrganic(boolean b) {
        return organic;
    }

    public void setOrganic(boolean organic) {
        this.organic = organic;
    }

    public boolean isActive(int i) {
        return isActive;
    }

    public void setActive(boolean isActive){
        this.isActive = isActive;
    }

    public int getProductId() {
        return productId;
    }

    public void setProductId(int productId) {
        this.productId = productId;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }
}
