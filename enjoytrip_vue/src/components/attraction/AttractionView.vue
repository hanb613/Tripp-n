<template>
  <b-container class="bv-example-row mt-3">
    <b-row class="mb-1">
      <b-col>
        <b-card
          :img-src="attraction.firstImage"
          class="mb-2"
          img-alt="관광지 사진"
          no-body
        >
          <b-card-text>
            <h2>{{ attraction.title }}</h2>
            {{ attraction.addr1 }}<br />
            설명<br />{{ attraction.overview }}
          </b-card-text>
        </b-card>
      </b-col>
    </b-row>
  </b-container>
</template>

<script>
import { getAttraction } from "@/api/attraction";
export default {
  name: "AttractionDetail",
  components: {},
  data() {
    return {
      attraction: {},
    };
  },
  created() {
    let param = this.$route.params.contentNo;
    getAttraction(
      param,
      ({ data }) => {
        console.log(data);
        this.attraction = data;
      },
      (error) => {
        console.log(error);
      }
    );
  },
  methods: {},
};
</script>

<style scoped></style>
