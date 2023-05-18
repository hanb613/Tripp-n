import { sidoList, gugunList } from "@/api/attraction.js";

const attractionStore = {
  namespaced: true,
  state: {
    sidos: [{ value: 0, text: "지역" }],
    guguns: [{ value: 0, text: "시/군/구" }],
    contents: [{ value: 0, text: "관광지 유형" }],
    attrs: [],
    attr: null,
  },
  getters: {},
  mutations: {
    SET_SIDO_LIST(state, sidos) {
      sidos.forEach((sidoNo) => {
        state.sidos.push({ value: sidoNo.sidoNo, text: sidoNo.sidoName });
      });
    },
    SET_GUGUN_LIST(state, guguns) {
      guguns.forEach((gugun) => {
        state.guguns.push({ value: gugun.gugunNo, text: gugun.gugunName });
      });
    },
    SET_CONTENTS_ID(state, contents) {
      state.contents = contents;
    },
    CLEAR_SIDO_LIST(state) {
      state.sidos = [{ value: 0, text: "지역" }];
    },
    CLEAR_GUGUN_LIST(state) {
      state.guguns = [{ value: 0, text: "시/군/구" }];
    },
    CLEAR_CONTENTS_LIST(state) {
      state.contents = [{ value: 0, text: "관광지 유형" }];
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
    getGugun({ commit }, sidoNo) {
      const params = { sidoNo: sidoNo };
      if (sidoNo !== 0) { // 특정 지역을 선택했으면 
        gugunList(
          params,
          ({ data }) => {
            commit("SET_GUGUN_LIST", data);
          },
          (error) => {
            console.log(error);
          }
        );
      }
    },
  },
};

export default attractionStore;
