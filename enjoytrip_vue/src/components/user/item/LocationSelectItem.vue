<template>
  <b-row class="mt-4 mb-4 text-center">
    <b-col class="sm-3">
      <b-form-select
        v-model="sidoNo"
        :options="sidos"
        @change="gugunList"
      ></b-form-select>
    </b-col>
    <b-col class="sm-3">
      <b-form-select v-model="gugunNo" :options="guguns"></b-form-select
    ></b-col>
  </b-row>
</template>

<script>
import { mapState, mapActions, mapMutations } from "vuex";
const attractionStore = "attractionStore";
export default {
  name: "LocationSelectItem",
  components: {},
  data() {
    return {
      sidoNo: 0,
      gugunNo: 0,
    };
  },
  computed: {
    ...mapState(attractionStore, ["sidos", "guguns", "contents", "attrs"]),
  },
  created() {
    this.CLEAR_SIDO_LIST();
    this.CLEAR_GUGUN_LIST();
    this.getSido();
  },
  watch: {
    sidoNo() {
      this.updateLocNo();
    },
    gugunNo() {
      this.updateLocNo();
    },
  },
  methods: {
    updateLocNo() {
      let locNo = this.sidoNo * 1000 + this.gugunNo;
      this.$emit("update-loc-no", locNo);
    },
    ...mapActions(attractionStore, ["getSido", "getGugun", "getAttrList"]),
    ...mapMutations(attractionStore, [
      "CLEAR_SIDO_LIST",
      "CLEAR_GUGUN_LIST",
      "CLEAR_CONTENTS_LIST",
      "CLEAR_ATTR_LIST",
    ]),
    gugunList() {
      this.CLEAR_GUGUN_LIST();
      this.CLEAR_CONTENTS_LIST();
      this.gugunNo = 0;
      this.contentTypeId = 0;
      if (this.sidoNo) this.getGugun(this.sidoNo);
    },
  },
};
</script>

<style scoped></style>