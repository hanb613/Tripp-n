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
  created() {},
  methods: {},
};
</script>

<style scoped>
#app {
  min-height: calc(100%-9vw);
  position: absolute;
  /* padding-bottom: 160px; */
}
</style>