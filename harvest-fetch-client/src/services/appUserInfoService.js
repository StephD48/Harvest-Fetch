import * as base from "./baseService";
const model = "appUserInfo";

export function getEmptyAppUserInfo(){
    return {
        appUserId: 0,
        firstName: "",
        lasName: "",
        address: "",
        zipCode: "",
        city: "",
        state: "",
        email: "",
        phone: "",
        photoUrl: ""
    }
}

export async function findAll() {
    return base.findAll(model);
}

export async function findById(appUserId) {
    return base.findById(model, appUserId);
}

export async function save(appUserInfo) {
    return base.save(model, appUserInfo, appUserInfo.appUserId);
}

export async function deleteById(appUserId) {
    return base.deleteById(model, appUserId);
}