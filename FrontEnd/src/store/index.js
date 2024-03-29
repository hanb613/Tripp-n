import Vue from "vue";
import Vuex from "vuex";
import createPersistedState from "vuex-persistedstate";

// module import
import attractionStore from "@/store/modules/attractionStore";
import memberStore from "@/store/modules/memberStore";
import boardStore from "@/store/modules/boardStore";
import chartStore from "@/store/modules/chartStore";

Vue.use(Vuex);

export default new Vuex.Store({
  modules: {
    attractionStore,
    memberStore,
    boardStore,
    chartStore,
  },
  plugins: [
    createPersistedState({
      // 브라우저 종료시 제거하기 위해 localStorage가 아닌 sessionStorage로 변경. (default: localStorage)
      storage: sessionStorage,
    }),
  ],
});
