<template>
    <b-container class="bv-example-row mt-3 text-center">
      <b-row>
        <b-col>
          <attr-search-bar></attr-search-bar>
        </b-col>
      </b-row>
      <b-row>
        <b-col cols="6" align="left">
          <attr-map />
        </b-col>
        <!-- <b-col cols="6">
          <attr-detail />
        </b-col>-->
      </b-row>
    </b-container>
  </template>
  
  <script>
  import AttrSearchBar from "@/components/attraction/AttractionSearch.vue";
  import AttrMap from "@/components/attraction/AttractionMap.vue";
  
  export default {
    name: "AttractionView",
    components: {
      AttrSearchBar,
      AttrMap,
    },
  };
  </script>
  