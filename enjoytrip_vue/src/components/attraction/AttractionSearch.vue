<template>
  <b-container class="bv-example-row mt-3">
    <attraction-search-item></attraction-search-item>
    <attraction-map :attrList="attrs"></attraction-map>

    <!-- <attraction-list></attraction-list> -->
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
  </b-container>
</template>

<script>
import AttractionSearchItem from "@/components/attraction/item/AttractionSearchItem.vue";
import AttractionMap from "@/components/attraction/AttractionMap.vue";
import AttractionListItem from "@/components/attraction/AttractionListItem.vue";
//import AttractionList from "./AttractionList.vue";

import { mapState } from "vuex";

const attractionStore = "attractionStore";

export default {
  name: "AttractionSearch",
  components: {
    AttractionSearchItem,
    AttractionMap,
    AttractionListItem,
    // AttractionList,
  },
  data() {
    return {
      message: "",
    };
  },
  computed: {
    ...mapState(attractionStore, ["attrs"]),
  },
  created() {},
  methods: {},
};
</script>

<style scoped></style>
