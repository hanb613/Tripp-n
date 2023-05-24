<template>
  <b-carousel
  id="carousel-fade"
  :interval="3800"
  fade
  controls
  indicators
  background="transparent"
  style="text-shadow: 2px 2px 5px #0f0f0f"
  >
  <b-carousel-slide
      v-for="(slide, index) in slides"
      :key="index"
      :caption="slide.title"
      :text="slide.addr"
  >
      <template v-slot:img>
          <router-link :to="{ name: 'AttractionDetail', params: { contentNo: slide.contentNo } }">
              <img :src="slide.imgsrc" class="w-100" height="450">
          </router-link>
      </template>
  </b-carousel-slide>
  </b-carousel>
</template>

<script>
import { getRecentLike } from "@/api/attraction.js";
export default {
  name: "AttractionCarousel",
  data() {
    return {
        slides:[],
    };
  },
  created(){
    getRecentLike(
        ({ data }) => {
          console.log(data);
          this.slides = data;
        },
        (error) => {
          console.log(error);
        }
    );
  },
}
</script>

<style>
#carousel-fade{
  width:50vw;
  /* height: 480px; */
}
</style>