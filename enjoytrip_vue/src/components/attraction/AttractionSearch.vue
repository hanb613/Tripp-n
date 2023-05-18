<template>
      <b-container class="bv-example-row mt-3">
    <attraction-search-item></attraction-search-item>
    <attraction-map></attraction-map>
    <attraction-list></attraction-list>
  </b-container>
</template>

<script>
import AttractionSearchItem from "@/components/attraction/item/AttractionSearchItem.vue";
import AttractionMap from "@/components/attraction/AttractionMap.vue";
import AttractionList from "./AttractionList.vue";

export default {
    name: 'AttractionSearch',
    components: {
        AttractionSearchItem,
        AttractionMap,
        AttractionList,
    },
    data() {
    return {
            message: '',
        };
    },
    created() {},
    methods: {},
};
</script>

<style scoped></style>