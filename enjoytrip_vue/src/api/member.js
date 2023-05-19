import { apiInstance } from "./index.js";

const api = apiInstance();

function register(param, success, fail) {
  api.post(`/user/join`, JSON.stringify(param)).then(success).catch(fail);
}

async function modify(param, success, fail) {
  await api.put(`/user`, JSON.stringify(param)).then(success).catch(fail);
}

async function login(param, success, fail) {
  await api
    .post(`/user/login`, JSON.stringify(param))
    .then(success)
    .catch(fail);
}

async function logout(userid, success, fail) {
  await api.get(`/user/logout/${userid}`).then(success).catch(fail);
}

async function findById(userid, success, fail) {
  await api.get(`/user/info/${userid}`).then(success).catch(fail);
}

export { register, login, findById, logout, modify };
