<template>
  <b-tr>
    <b-td>{{ content }}</b-td>
    <b-td>{{ userName }}</b-td>
  </b-tr>
</template>

<script>
import moment from "moment";

export default {
  name: "CommentListItem",
  props: {
    userName: String,
    content: String,
  },
  filters: {
    dateFormat(regtime) {
      return moment(new Date(regtime)).format("YY.MM.DD");
    },
  },
};
</script>

<style scoped></style>
