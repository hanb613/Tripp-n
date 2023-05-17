<template>
  <b-tr>
    <b-td>{{ boardNo }}</b-td>
    <b-th class="text-left">
      <router-link :to="{ name: 'boardview', params: { boardNo: boardNo } }">{{ subject }}</router-link>
    </b-th>
    <b-td>{{ hit }}</b-td>
    <b-td>{{ userName }}</b-td>
    <b-td>{{ createtime | dateFormat }}</b-td>
  </b-tr>
</template>

<script>
import moment from "moment";

export default {
  name: "BoardListItem",
  props: {
    boardNo: Number,
    userName: String,
    subject: String,
    like: Number,
    createTime: String,
  },
  filters: {
    dateFormat(regtime) {
      return moment(new Date(regtime)).format("YY.MM.DD");
    },
  },
};
</script>

<style></style>
