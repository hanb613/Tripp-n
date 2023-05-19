import Vue from "vue";
import VueRouter from "vue-router";
import AppMain from "@/views/AppMain";

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
      }
    ]
  },
  {
    path: "/user",
    name: "user",
    component: () => import(/* webpackChunkName: "user" */ "@/views/AppUser"),
    children: [
      {
        path: "join",
        name: "UserRegister",
        component: () => import(/* webpackChunkName: "user" */ "@/components/user/UserRegister"),
      },
      {
        path: "login",
        name: "UserLogin",
        component: () => import(/* webpackChunkName: "user" */ "@/components/user/UserLogin"),
      },
      {
        path: "mypage",
        name: "UserMyPage",
        // beforeEnter: onlyAuthUser,
        component: () => import(/* webpackChunkName: "user" */ "@/components/user/UserMyPage"),
      },
      {
        path: "modify",
        name: "UserModify",
        // beforeEnter: onlyAuthUser,
        component: () => import(/* webpackChunkName: "user" */ "@/components/user/UserModify"),
      },
      
      {
        path: "findPw",
        name: "UserFindPw",
        // beforeEnter: onlyAuthUser,
        component: () => import(/* webpackChunkName: "user" */ "@/components/user/UserFindPw"),
      },
    ],
  },
  {
    path: "/board",
    name: "board",
    component: () => import(/* webpackChunkName: "board" */ "@/views/AppBoard"),
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
];

const router = new VueRouter({
  mode: "history",
  base: process.env.BASE_URL,
  routes,
});

export default router;
