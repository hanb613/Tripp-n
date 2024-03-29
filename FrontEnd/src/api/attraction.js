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

function likeAttraction(params, success, fail) {
  api.post(`/attraction/like`, params).then(success).catch(fail);
}

function getLikeCountBySido(success, fail) {
  api.get(`/attraction/chart`).then(success).catch(fail);
}

function getRecentLike(success, fail) {
  api.get(`/attraction/recent`).then(success).catch(fail);
}

function getFiles(success, fail) {
  api.get(`attraction/getFiles`).then(success).catch(fail);
}

function uploadFile(params, success, fail) {
  api
    .post(`/attraction/uploadFile`, params, {
      headers: {
        "Content-Type": "multipart/form-data",
      },
    })
    .then(success)
    .catch(fail);
}

function downloadFile(params, success, fail) {
  api.get(`attraction/download/${params.saveFolder}/${params.originalFile}/${params.saveFile}`).then(success).catch(fail);
}

function showImage(params, success, fail) {
  api.get(`attraction/showImage`, params).then(success).catch(fail);
}

export {
  sidoList,
  gugunList,
  attrList,
  getAttraction,
  likeAttraction,
  getLikeCountBySido,
  getRecentLike,
  uploadFile,
  getFiles,
  downloadFile,
  showImage,
};
