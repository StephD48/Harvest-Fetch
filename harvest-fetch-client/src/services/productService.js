import * as base from "./baseService";
const model = "product";
const url = "http://localhost:8080/api/product";

export function getEmptyProduct(){
    return {
        productId: 0,
        productName: "",
        pictureUrl: "",
        farmers: []
    };
}

export async function removeProductFromFarmer(productId){
    console.log("Did I get here?")
    const config = {
        method: "DELETE",
        headers: {
            "Authorization": `Bearer ${localStorage.getItem("HF_JWT")}`
        }
    }
    const response = await fetch(`${url}/${productId}`, config);
    console.log(response)

    if (response.ok) {
        return;
    }
    return Promise.reject(`Could not delete product with id ${productId}.`)
}

export async function findAll() {
    return base.findAll(model);
}

export async function findById(productId) {
    return base.findById(model, productId);
}

export async function save(product) {
    return base.save(model, product, product.productId);
}

export async function deleteById(productId) {
    return base.deleteById(model, productId);
}