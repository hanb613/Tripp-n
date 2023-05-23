import Vue from "vue";
import VueRouter from "vue-router";
import AppMain from "@/views/AppMain";
import store from "@/store";

Vue.use(VueRouter);

const routes = [
  {
    path: "/",
    name: "main",
    component: AppMain,
  },
  {
    path: "/attraction",
    name: "attraction",
    component: () => import("@/views/AppAttraction"),
    redirect: "/attraction/search",
    children: [
      {
        path: "search",
        name: "AttractionSearch",
        component: () =>
          import(
            /* webpackChunkName: "attraction" */ "@/components/attraction/AttractionSearch"
          ),
      },
      {
        path: "detail/:contentNo",
        name: "AttractionDetail",
        component: () =>
          import(
            /* webpackChunkName: "attraction" */ "@/components/attraction/AttractionView"
          ),
      },
    ],
  },
  {
    path: "/user",
    name: "user",
    component: () => import(/* webpackChunkName: "user" */ "@/views/AppUser"),
    children: [
      {
        path: "join",
        name: "UserRegister",
        component: () =>
          import(
            /* webpackChunkName: "user" */ "@/components/user/UserRegister"
          ),
      },
      {
        path: "login",
        name: "UserLogin",
        component: () =>
          import(/* webpackChunkName: "user" */ "@/components/user/UserLogin"),
      },
      {
        path: "mypage",
        name: "UserMyPage",
        // beforeEnter: onlyAuthUser,
        component: () =>
          import(/* webpackChunkName: "user" */ "@/components/user/UserMyPage"),
      },
      {
        path: "modify",
        name: "UserModify",
        // beforeEnter: onlyAuthUser,
        component: () =>
          import(/* webpackChunkName: "user" */ "@/components/user/UserModify"),
      },

      {
        path: "findId",
        name: "UserFindId",
        // beforeEnter: onlyAuthUser,
        component: () =>
          import(/* webpackChunkName: "user" */ "@/components/user/UserFindId"),
      },
      {
        path: "findPw",
        name: "UserFindPw",
        // beforeEnter: onlyAuthUser,
        component: () =>
          import(/* webpackChunkName: "user" */ "@/components/user/UserFindPw"),
      },
      {
        path: "like",
        name: "UserLike",
        // beforeEnter: onlyAuthUser,
        component: () =>
          import(
            /* webpackChunkName: "user" */ "@/components/user/UserLikeList"
          ),
      },
    ],
  },
  {
    //공지사항
    path: "/notice",
    name: "notice",
    component: () =>
      import(/* webpackChunkName: "notice" */ "@/views/AppBoard"),
    beforeEnter: (to, from, next) => {
      store.commit("boardStore/SET_IS_NOTICE", true);
      next();
    },
    redirect: "/notice/list",
    children: [
      {
        path: "list",
        name: "noticelist",
        component: () =>
          import(
            /* webpackChunkName: "notice" */ "@/components/board/BoardList"
          ),
      },
      {
        path: "write",
        name: "noticewrite",
        // beforeEnter: onlyAuthUser,
        component: () =>
          import(
            /* webpackChunkName: "board" */ "@/components/board/BoardWrite"
          ),
      },
      // {
      //   path: "view/:boardNo",
      //   name: "boardview",
      //   // beforeEnter: onlyAuthUser,
      //   component: () =>
      //     import(
      //       /* webpackChunkName: "board" */ "@/components/board/BoardView"
      //     ),
      // },
      // {
      //   path: "modify",
      //   name: "boardmodify",
      //   // beforeEnter: onlyAuthUser,
      //   component: () =>
      //     import(
      //       /* webpackChunkName: "board" */ "@/components/board/BoardModify"
      //     ),
      // },
      // {
      //   path: "delete/:articleno",
      //   name: "boarddelete",
      //   // beforeEnter: onlyAuthUser,
      //   component: () =>
      //     import(
      //       /* webpackChunkName: "board" */ "@/components/board/BoardDelete"
      //     ),
      // },
    ],
  },
  {
    //일반게시판
    path: "/board",
    name: "board",
    component: () => import(/* webpackChunkName: "board" */ "@/views/AppBoard"),
    beforeEnter: (to, from, next) => {
      store.commit("boardStore/SET_IS_NOTICE", false);
      next();
    },
    redirect: "/board/list",
    children: [
      {
        path: "list",
        name: "boardlist",
        component: () =>
          import(
            /* webpackChunkName: "board" */ "@/components/board/BoardList"
          ),
      },
      {
        path: "write",
        name: "boardwrite",
        // beforeEnter: onlyAuthUser,
        component: () =>
          import(
            /* webpackChunkName: "board" */ "@/components/board/BoardWrite"
          ),
      },
      {
        path: "view/:boardNo",
        name: "boardview",
        // beforeEnter: onlyAuthUser,
        component: () =>
          import(
            /* webpackChunkName: "board" */ "@/components/board/BoardView"
          ),
      },
      {
        path: "modify",
        name: "boardmodify",
        // beforeEnter: onlyAuthUser,
        component: () =>
          import(
            /* webpackChunkName: "board" */ "@/components/board/BoardModify"
          ),
      },
      {
        path: "delete/:articleno",
        name: "boarddelete",
        // beforeEnter: onlyAuthUser,
        component: () =>
          import(
            /* webpackChunkName: "board" */ "@/components/board/BoardDelete"
          ),
      },
    ],
  },
  {
    //차트
    path: "/chart",
    name: "chart",
    component: () => import(/* webpackChunkName: "chart" */ "@/components/chart/ChartTest"),
  },
];

const router = new VueRouter({
  mode: "history",
  base: process.env.BASE_URL,
  routes,
});

export default router;
