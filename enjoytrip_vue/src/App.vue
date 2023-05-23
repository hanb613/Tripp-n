<template>
  <div id="app">
    <header-view></header-view>
    <aside id="weather">
      <weather-view></weather-view>
    </aside>
    <router-view></router-view>
    <footer-view></footer-view>
  </div>
</template>

<script>
import HeaderView from "./components/common/Header.vue";
import FooterView from "./components/common/Footer.vue";
import WeatherView from "./components/common/weather.vue";

import { mapState } from "vuex";

const memberStore = "memberStore";

export default {
  name: "App",
  components: {
    HeaderView,
    WeatherView,
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
};
</script>

<style scoped>
#app {
  /* min-height: calc(100%-9vw); */
  min-height: 100vh;
  position: relative;
  /* padding-bottom: 160px; */
}
#weather {
  position: fixed;
  /* right: 0; */
  margin-top: 30px;
  /* margin-right:250px; */
  /* margin-right:13.0208vw; */
  margin-left: 75vw;
}
</style>
