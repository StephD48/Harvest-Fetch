package Harvest.Models;

import java.util.ArrayList;
import java.util.List;

public class Product {

    private int productId;
    private String productName;
    private String pictureUrl;

    private List<ProductFarmer> farmers = new ArrayList<>();

    public Product() {
    }

    public Product(int productId, String productName, String pictureUrl) {
        this.productId = productId;
        this.productName = productName;
        this.pictureUrl = pictureUrl;
    }


    public int getProductId() {
        return productId;
    }

    public void setProductId(int productId) {
        this.productId = productId;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public String getPictureUrl() {
        return pictureUrl;
    }

    public void setPictureUrl(String pictureUrl) {
        this.pictureUrl = pictureUrl;
    }

    public List<ProductFarmer> getFarmers() {
        return farmers;
    }

    public void setFarmers(List<ProductFarmer> farmers) {
        this.farmers = farmers;
    }
}
