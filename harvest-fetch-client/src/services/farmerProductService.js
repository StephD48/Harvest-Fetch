import * as base from "./baseService";
const model = "farmerProduct";

export function getEmtpyFarmerProduct(){
    return {
        farmerId: 0,
        productId: 0,
        price: 0,
        organic: true,
        isActive: true,
        products: []
    };
}

export async function save(farmerProduct){
    return base.save(model, farmerProduct, farmerProduct.farmerId);
}