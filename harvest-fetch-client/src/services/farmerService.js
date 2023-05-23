import * as base from "./baseService";
const model = "farmer";

export function getEmtpyFarmer(){
    return {
        farmerId: 0,
        farmName: "",
        details: "",
        photoUrl: "",
        userId: 0,
        products: []
    };
}

export async function findAll(){
    return base.findAll(model);
}

export async function findById(farmerId){
    return base.findById(model, farmerId);
}

export async function save(farmer){
    return base.save(model, farmer, farmer.farmerId);
}

export async function deleteById(farmerId){
    return base.deleteById(model, farmerId)
}