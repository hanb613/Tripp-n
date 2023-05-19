import { login, logout, modify } from "@/api/member.js";

const memberStore = {
  namespaced: true,
  state: {
    isLogin: false,
    isLoginError: false,
    userInfo: {
      userNo: 0,
      id: null,
      password: null,
      name: null,
      email: null,
      locNo: 0,
      age: 0,
      gender: "",
      joinDate: null,
      changeData: false,
    },
  },
  getters: {
    checkUserInfo: function (state) {
      return state.userInfo;
    },
  },
  mutations: {
    SET_IS_LOGIN: (state, isLogin) => {
      state.isLogin = isLogin;
    },
    SET_IS_LOGIN_ERROR: (state, isLoginError) => {
      state.isLoginError = isLoginError;
    },
    SET_USER_INFO: (state, userInfo) => {
      state.isLogin = true;
      console.log("로그인정보");
      console.log(userInfo);
      state.userInfo = userInfo;
    },
    SET_CHANGE_DATA: (state, changeData) => {
      state.userInfo.changeData = changeData;
    },
  },
  actions: {
    async userConfirm({ commit }, user) {
      await login(
        user,
        ({ data }) => {
          if (data.message === "success") {
            console.log("data: ");
            console.log(data.userInfo);
            commit("SET_IS_LOGIN", true);
            commit("SET_IS_LOGIN_ERROR", false);
            commit("SET_USER_INFO", data.userInfo);
          } else {
            commit("SET_IS_LOGIN", false);
            commit("SET_IS_LOGIN_ERROR", true);
          }
        },
        (error) => {
          console.log(error);
        }
      );
    },
    async modifyUser({ commit }, user) {
      await modify(
        user,
        ({ data }) => {
          console.log("data", data);
          if (data.message === "success") {
            commit("SET_USER_INFO", data.userInfo);
            commit("SET_CHANGE_DATA", true);
          }
        },
        (error) => {
          console.log(error);
          commit("SET_CHANGE_DATA", false);
        }
      );
    },
    async userLogout({ commit }, userid) {
      await logout(
        userid,
        ({ data }) => {
          if (data.message === "success") {
            commit("SET_IS_LOGIN", false);
            commit("SET_USER_INFO", null);
          } else {
            console.log("유저 정보 없음!!!!");
          }
        },
        (error) => {
          console.log(error);
        }
      );
    },
  },
};

export default memberStore;
