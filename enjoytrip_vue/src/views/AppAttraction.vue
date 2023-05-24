<template>
  <b-container class="bv-example-row mt-3 container">
    <h3 style="font-family: 'Noto Sans KR', sans-serif">
      <b-icon icon="map"></b-icon> 관광지
    </h3>
    <router-view></router-view>
  </b-container>
</template>
  
<script>
import { mapMutations } from "vuex";
const attractionStore = "attractionStore";
export default {
  name: "AppAttraction",
  //다른 라우트로 가면 attr 목록 초기화(메인만 빼고 되는것같음)
  beforeRouteLeave(to, from, next) {
    this.CLEAR_ATTR_LIST();
    next();
  },
  methods: {
    ...mapMutations(attractionStore, ["CLEAR_ATTR_LIST"]),
  },
};
</script>
<style scoped>
@import url("https://fonts.googleapis.com/css2?family=Noto+Sans+KR&display=swap");
.container {
  min-height: 60vh;
}
</style>
