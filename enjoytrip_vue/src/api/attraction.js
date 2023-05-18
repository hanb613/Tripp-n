import { apiInstance } from "./index.js";

const api = apiInstance();

function sidoList(success, fail) {
  api.get(`/attraction/sido`).then(success).catch(fail);
}

function gugunList(params, success, fail) {
  api.get(`/attraction/gugun`, { params: params }).then(success).catch(fail);
}

function attrList(params, success, fail) {
  api.get(`/attraction/search`, { params: params }).then(success).catch(fail);
}

function getAttraction(contentNo, success, fail) {
  console.log(contentNo + "번 관광지 호출");
  api.get(`/attraction/${contentNo}`).then(success).catch(fail);
}

export { sidoList, gugunList, attrList, getAttraction };

