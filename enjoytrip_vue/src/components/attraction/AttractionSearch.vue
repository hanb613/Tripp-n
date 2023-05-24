<template>
  <b-container class="bv-example-row mt-3 ml-1">
    <attraction-search-item></attraction-search-item>
    <attraction-map :attrList="attrs"></attraction-map>

    <!-- Attraction List -->
    <b-container
      v-if="attrs && attrs.length != 0"
      class="bv-example-row-flex-cols"
    >
      <b-row offset="1" class="itemrow">
        <attraction-list-item
          v-for="(attr, index) in attrs"
          :key="index"
          :attr="attr"
        />
      </b-row>
    </b-container>
    <!-- 결과 없을때 -->
    <b-container v-else class="mt-3 ml-1">
      <b-row>
        <b-col style="padding: 0"
          ><b-alert show>관광지 목록이 없습니다.</b-alert></b-col
        >
      </b-row>
    </b-container>
  </b-container>
</template>

<script>
import AttractionSearchItem from "@/components/attraction/item/AttractionSearchItem.vue";
import AttractionMap from "@/components/attraction/AttractionMap.vue";
import AttractionListItem from "@/components/attraction/AttractionListItem.vue";

import { mapState } from "vuex";

const attractionStore = "attractionStore";

export default {
  name: "AttractionSearch",
  components: {
    AttractionSearchItem,
    AttractionMap,
    AttractionListItem,
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

<style scoped>
.bv-example-row.container {
  width: 57.8125vw;
}
.itemrow {
  display: flex;
  flex-wrap: wrap;
}
</style>
