<template>
  <b-container class="bv-example-row mt-3 container">
    <h3 style="font-family: 'Noto Sans KR', sans-serif"><b-icon icon="map"></b-icon> 관광지</h3>
    <router-view></router-view>
  </b-container>
</template>
  
  <script>
export default {
  name: "AppAttraction",
};
</script>
<style scoped>

@import url("https://fonts.googleapis.com/css2?family=Noto+Sans+KR&display=swap");
.container {
  min-height: 60vh;
}
</style>
