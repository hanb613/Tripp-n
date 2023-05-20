import { apiInstance } from "./index.js";

const api = apiInstance();

// 회원가입
function register(param, success, fail) {
  api.post(`/user/join`, JSON.stringify(param)).then(success).catch(fail);
}

// 회원정보 수정
async function modify(param, success, fail) {
  await api.put(`/user`, JSON.stringify(param)).then(success).catch(fail);
}

// 로그인
async function login(param, success, fail) {
  await api
    .post(`/user/login`, JSON.stringify(param))
    .then(success)
    .catch(fail);
}

// 로그아웃
async function logout(userid, success, fail) {
  await api.get(`/user/logout/${userid}`).then(success).catch(fail);
}

// 해당 유저정보 찾기
async function findById(userid, success, fail) {
  await api.get(`/user/info/${userid}`).then(success).catch(fail);
}

// 회원탈퇴
function removeUser(userid, success, fail) {
  api.delete(`/user/${userid}`).then(success).catch(fail);
}

export { register, login, findById, logout, modify, removeUser };
