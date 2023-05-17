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
    <b-col class="sm-3">
      <b-form-select
        id="search-content-id"
        class="sm-3"
        v-model="contentTypeId"
        :options="contents"
      >
        <option value="12">관광지</option>
        <option value="14">문화시설</option>
        <option value="15">축제공연행사</option>
        <option value="25">여행코스</option>
        <option value="28">레포츠</option>
        <option value="32">숙박</option>
        <option value="38">쇼핑</option>
        <option value="39">음식점</option>
      </b-form-select>
    </b-col>
    <b-col cols="1">
      <button
        id="btn-search"
        class="btn btn-outline-success"
        type="submit"
        @click="searchAttr"
      >
        검색
      </button></b-col
    >
  </b-row>
</template>

<script>
import { mapState, mapActions, mapMutations } from "vuex";
const attractionStore = "attractionStore";

export default {
  name: "AttractionSearchBar",
  data() {
    return {
      sidoNo: null,
      gugunNo: null,
      contentTypeId: null,
    };
  },
  computed: {
    ...mapState(attractionStore, ["sidos", "guguns", "contents", "attrs"]),
  },
  created() {
    this.CLEAR_SIDO_LIST();
    this.CLEAR_ATTR_LIST();
    this.getSido();
  },
  methods: {
    ...mapActions(attractionStore, ["getSido", "getGugun", "getAttrList"]),
    ...mapMutations(attractionStore, [
      "CLEAR_SIDO_LIST",
      "CLEAR_GUGUN_LIST",
      "CLEAR_ATTR_LIST",
    ]),
    gugunList() {
      this.CLEAR_GUGUN_LIST();
      this.gugunNo = null;
      if (this.sidoNo) this.getGugun(this.sidoNo);
    },
    searchAttr() {
      if (this.gugunNo && this.contentTypeId)
        this.getAttrList(this.gugunNo, this.contentTypeId);
    },
  },
};
</script>

<style scoped></style>
