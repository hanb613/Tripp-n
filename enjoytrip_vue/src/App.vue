<template>
  <div id="app">
    <header-view></header-view>
    <router-view></router-view>
    <footer-view></footer-view>
  </div>
</template>

<script>
import HeaderView from "./components/common/Header.vue";
import FooterView from "./components/common/Footer.vue";

import { mapState } from "vuex";

const memberStore = "memberStore";

export default {
  name: "App",
  components: {
    HeaderView,
    FooterView,
  },
  data() {
    return {
      message: "",
    };
  },
  computed: {
    ...mapState(memberStore, ["isLogin", "userInfo"]),
  },
  created() {
    const isLogin = sessionStorage.getItem("isLogin");
    const userInfo = JSON.parse(sessionStorage.getItem("userInfo"));
    if (isLogin && userInfo) {
      this.$store.commit(`${memberStore}/SET_IS_LOGIN`, isLogin === "true");
      this.$store.commit(`${memberStore}/SET_USER_INFO`, userInfo);
    } else {
      this.$store.commit(`${memberStore}/SET_IS_LOGIN`, false);
      this.$store.commit(`${memberStore}/SET_USER_INFO`, null);
    }
  },
  // methods: {
  //   initializeLogin() {
  //     this.userLogout();
  //   },
  // },
  // mounted() {
  //   this.initializeLogin();
  // },
};
</script>

<style scoped>
#app {
  /* min-height: calc(100%-9vw); */
  min-height: 100vh;
  position: absolute;
  /* padding-bottom: 160px; */
}
</style>
