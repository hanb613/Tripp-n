<template>
  <b-container class="bv-example-row mt-3 ml-1">
    <board-input-item type="modify" />
  </b-container>
</template>

<script>
import BoardInputItem from "@/components/board/item/BoardInputItem";

export default {
  name: "BoardModify",
  components: {
    BoardInputItem,
  },
};
</script>

<style scoped>
.bv-example-row.container {
  width: 57.8125vw;
}
</style>
