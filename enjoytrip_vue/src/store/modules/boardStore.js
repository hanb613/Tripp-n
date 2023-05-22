const boardStore = {
    namespaced: true,
    state: {
        isNotice: false,
    },
    getters: {
        checkBoardType: function (state) {
            return state.isNotice;
        },
    },
    mutations: {
        SET_IS_NOTICE: (state, isNotice) => {
            // console.log("공지사항인가? " + isNotice);
            state.isNotice = isNotice;
        },
    },
    actions: {

    }
};
export default boardStore;