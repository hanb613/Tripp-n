<template>
    <b-container class="bv-example-row">
    <br>
    <h3>최근 좋아요한 관광지</h3>
    <b-row>
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
                    <img :src="slide.imgsrc" class="w-100" height="480">
                </router-link>
            </template>
        </b-carousel-slide>
        </b-carousel>
    </b-row>
    <br>
  </b-container>
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
#carousel-1{
  width:53.3333vw;
  /* height: 480px; */
}
</style>