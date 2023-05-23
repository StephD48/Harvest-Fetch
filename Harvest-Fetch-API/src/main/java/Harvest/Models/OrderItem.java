package Harvest.Models;

public class OrderItem {

    private int orderItemId;
    private int orderId;
    private int quantity;
    private String priceCode;
    private int farmerId;
    private int productId;

    public OrderItem(int orderItemId, int orderId, int quantity, String priceCode, int farmerId, int productId) {
        this.orderItemId = orderItemId;
        this.orderId = orderId;
        this.quantity = quantity;
        this.priceCode = priceCode;
        this.farmerId = farmerId;
        this.productId = productId;
    }

    public void setPriceCode(String priceCode) {
        this.priceCode = priceCode;
    }

    public String getPriceCode() {
        return priceCode;
    }

    public OrderItem() {

    }


    public int getOrderItemId() {
        return orderItemId;
    }

    public void setOrderItemId(int orderItemId) {
        this.orderItemId = orderItemId;
    }

    public int getOrderId() {
        return orderId;
    }

    public void setOrderId(int orderId) {
        this.orderId = orderId;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public int getFarmerId() {
        return farmerId;
    }

    public void setFarmerId(int farmerId) {
        this.farmerId = farmerId;
    }

    public int getProductId() {
        return productId;
    }

    public void setProductId(int productId) {
        this.productId = productId;
    }
}
