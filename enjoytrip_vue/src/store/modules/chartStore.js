import { getLikeCountBySido } from "@/api/attraction.js";

const chartStore = {
    namespaced: true,
    state: {
        items: [{ likeCnt: 0, name: "지역.." }],
    },
    getters: {},
    mutations: {
        SET_ITEMS(state, data) {
            state.items = [];
            data.forEach((item) => {
                console.log(item);
                state.items.push({ likeCnt: item.likeCnt, name: item.name });
            })
        },
        CLEAR_ITEMS(state) {
            state.items = [{ likeCnt: 0, name: "지역.." }];
        },
    },
    actions: {
        getItems({ commit }) {
            // commit("CLEAR_ITEMS");
            getLikeCountBySido(
                ({ data }) => {
                    commit("SET_ITEMS", data);
                },
                (error) => {
                    console.log(error);
                }
            );
        },
    }
};

export default chartStore;