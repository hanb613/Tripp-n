import { login, logout } from "@/api/member.js";

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
      gender: '',
      joinDate: null,
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
    // async getUserInfo({ commit }, user) {
    //   console.log("누가 로그인?");
    //   console.log(user)
    //   commit("SET_USER_INFO", user);
    //   //   await findById(
    //   //     user,
    //   //     ({ data }) => {
    //   //       if (data.message === "success") {
    //   //         commit("SET_USER_INFO", data.userInfo);
    //   //       } else {
    //   //         console.log("유저 정보 없음!!!!");
    //   //       }
    //   //     },
    //   //     (error) => {
    //   //         console.log(error);
    //   //     }
    //   //   );
    // },
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
