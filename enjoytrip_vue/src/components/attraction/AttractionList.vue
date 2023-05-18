<template>
  <b-container v-if="attrs && attrs.length != 0" class="bv-example-row mt-3">
    <attraction-list-item
      v-for="(attr, index) in attrs"
      :key="index"
      :attr="attr"
    />
  </b-container>
  <b-container v-else class="bv-example-row mt-3">
    <b-row>
      <b-col><b-alert show>관광지 목록이 없습니다.</b-alert></b-col>
    </b-row>
  </b-container>
</template>

<script>
import AttractionListItem from "@/components/attraction/AttractionListItem.vue";
import { mapState } from "vuex";

const attractionStore = "attractionStore";

export default {
  name: "AttractionList",
  components: {
    AttractionListItem,
  },
  data() {
    return {};
  },
  computed: {
    ...mapState(attractionStore, ["attrs"]),
  },
};
</script>

<style></style>
