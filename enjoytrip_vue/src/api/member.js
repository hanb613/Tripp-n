import { apiInstance } from "./index.js";

const api = apiInstance();

function register(param, success, fail) {
    api.post(`/user/join`, JSON.stringify(param)).then(success).catch(fail);
}

export { register };