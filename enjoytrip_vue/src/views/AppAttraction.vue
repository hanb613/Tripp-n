<template>
  <b-container class="bv-example-row mt-3 container">
    <h3><b-icon icon="map"></b-icon> 관광지</h3>
    <router-view></router-view>
  </b-container>
</template>
  
  <script>
export default {
  name: "AppAttraction",
};
</script>
<style scoped>
.container {
  min-height: 60vh;
}
</style>
