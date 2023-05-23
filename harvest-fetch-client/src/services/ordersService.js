import * as base from "./baseService";
const model = "orders";

export function getEmptyOrders(){
    return {
        orderId: 0,
        orderDate: "",
        orderTotal: 0.00,
        userId: 0
    }
}

export async function findAll() {
    return base.findAll(model);
}

export async function findById(orderId) {
    return base.findById(model, orderId);
}

export async function save(orders) {
    return base.save(model, orders, orders.orderId);
}

export async function deleteById(orderId) {
    return base.deleteById(model, orderId);
}