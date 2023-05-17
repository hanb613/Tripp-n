import { sidoList, gugunList } from "@/api/attraction.js";

const attractionStore = {
  namespaced: true,
  state: {
    sidos: [{ value: null, text: "지역" }],
    guguns: [{ value: null, text: "시/군/구" }],
    contents: [{ value: null, text: "관광지 유형" }],
    attrs: [],
    attr: null,
  },
  getters: {},
  mutations: {
    SET_SIDO_LIST(state, sidos) {
      sidos.forEach((sido) => {
        state.sidos.push({ value: sido.sidoCode, text: sido.sidoName });
      });
    },
    SET_GUGUN_LIST(state, guguns) {
      guguns.forEach((gugun) => {
        state.guguns.push({ value: gugun.gugunCode, text: gugun.gugunName });
      });
    },
    SET_CONTENTS_ID(state, contents) {
      state.contents = contents;
    },
    CLEAR_SIDO_LIST(state) {
      state.sidos = [{ value: null, text: "지역" }];
    },
    CLEAR_GUGUN_LIST(state) {
      state.guguns = [{ value: null, text: "시/군/구" }];
    },
    CLEAR_ATTR_LIST(state) {
      state.attrs = [];
      state.attrs = null;
    },
  },
  actions: {
    getSido({ commit }) {
      sidoList(
        ({ data }) => {
          commit("SET_SIDO_LIST", data);
        },
        (error) => {
          console.log(error);
        }
      );
    },
    getGugun({ commit }, sidoCode) {
      const params = { sido: sidoCode };
      gugunList(
        params,
        ({ data }) => {
          commit("SET_GUGUN_LIST", data);
        },
        (error) => {
          console.log(error);
        }
      );
    },
  },
};

export default attractionStore;
