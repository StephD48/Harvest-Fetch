const url = "http://localhost:8080";

function convertJwtToUser(jwt) {
    const tokens = jwt.split(".");
    const userJson = atob(tokens[1]);
    const user = JSON.parse(userJson);
    user.jwt = jwt;

    user.hasAnyAuthority = function (...authorities) {
        for (const authority of authorities) {
            if (this.authorities.includes(authority)) {
                return true;
            }
        }
        return false;
    }

    return user;
}
    

export async function authenticate(credentials) {

    const config = {
        method: "POST",
        headers: {
            "Content-Type": "application/json"
        },
        body: JSON.stringify(credentials)
    }

    const response = await fetch(`${url}/authenticate`, config);

    if (response.ok) {
        const json = await response.json();
        return convertJwtToUser(json.jwt);
    }

    return Promise.reject();
}

export async function refresh() {

    const config = {
        method: "POST",
        headers: {
            "Authorization": `Bearer ${localStorage.getItem("HF_JWT")}`
        }
    };

    const response = await fetch(`${url}/refresh`, config);
    if (response.ok) {
        const json = await response.json();
        return convertJwtToUser(json.jwt);
    }

    return Promise.reject();
}