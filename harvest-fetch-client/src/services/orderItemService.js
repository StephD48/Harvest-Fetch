import * as base from "./baseService";
const model = "orderItem";

export function getEmptyOrderItem(){
    return {
        orderItemId: 0,
        orderId: 0,
        quantity: 0,
        priceCode: "",
        farmerId: 0,
        productId: 0
    }
}

export async function findAll() {
    return base.findAll(model);
}

export async function findById(orderItemId) {
    return base.findById(model, orderItemId);
}

export async function save(orderItem) {
    return base.save(model, orderItem, orderItem.orderItemId);
}

export async function deleteById(orderItemId) {
    return base.deleteById(model, orderItemId);
}