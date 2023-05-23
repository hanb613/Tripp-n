<template>
  <b-container class="bv-example-row mt-3 container">
    <h3 v-if="isNotice"><b-icon icon="megaphone"></b-icon> 공지사항</h3>
    <h3 v-else><b-icon icon="clipboard"></b-icon> 게시판</h3>
    <router-view></router-view>
  </b-container>
</template>
<script>
import { mapState, mapGetters } from "vuex";

const boardStore = "boardStore";
export default {
  name: "AppBoard",
  computed: {
    ...mapState(boardStore, ["isNotice"]),
    ...mapGetters(["checkBoardType"]),
  },
};
</script>
<style scoped>
.container{
  min-height: 60vh;
}
</style>
