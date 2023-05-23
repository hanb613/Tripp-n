import { apiInstance } from "./index.js";

const api = apiInstance();

// 게시판 댓글
function listComment(boardNo, success, fail) {
  api.get(`/board/comment/${boardNo}`).then(success).catch(fail);
}

function writeComment(comment, success, fail) {
  api.post(`/board/comment`, JSON.stringify(comment)).then(success).catch(fail);
}

// 관광지 댓글
function listAttrComment(contentNo, success, fail) {
  api.get(`/attraction/comment/${contentNo}`).then(success).catch(fail);
}

function writeAttrComment(comment, success, fail) {
  api
    .post(`/attraction/comment`, JSON.stringify(comment))
    .then(success)
    .catch(fail);
}

// function getArticle(boardNo, success, fail) {
//   api.get(`/board/${boardNo}`).then(success).catch(fail);
// }

// function modifyArticle(article, success, fail) {
//   api.put(`/board`, JSON.stringify(article)).then(success).catch(fail);
// }

// function deleteArticle(boardNo, success, fail) {
//   api.delete(`/board/${boardNo}`).then(success).catch(fail);
// }

//export { listArticle, writeArticle, getArticle, modifyArticle, deleteArticle };
export { listComment, writeComment, listAttrComment, writeAttrComment };
