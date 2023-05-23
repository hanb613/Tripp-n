<template>
<b-container fluid>
  <b-row class="text-center">
  <b-card
    overlay
    :img-src="sampleImage"
    img-alt="Card Image"
    text-variant="white"
    title="Welcome"
    sub-title="Subtitle"
  >
    <b-card-text>
      Some quick example text to build on the card and make up the bulk of the card's content.
    </b-card-text>
  </b-card>
  </b-row>
  <b-row data-aos="fade-up"><attraction-carousel/> </b-row>
  <b-row data-aos="fade-up"><chart-test/></b-row>
</b-container>
</template>

<script>
import AttractionCarousel from '@/components/attraction/AttractionCarousel.vue';
import ChartTest from '@/components/chart/ChartTest.vue';
import AOS from 'aos';
import "aos/dist/aos.css";
export default {
  name: "AppMain",
  components:{
    AttractionCarousel,
    ChartTest
  },
  data() {
    return {
      msg: null,
      sampleImage: "/sample.jpg",
    };
  },
  created(){
    AOS.init();
  },
  mounted() {
    if (this.msg !== null) {
      alert(this.msg);
    }
  },
};
</script>

<style>
@import "../assets/vendor/bootstrap/css/bootstrap.min.css";
@import "../assets/vendor/aos/aos.css";
@import "../assets/vendor/glightbox/css/glightbox.min.css";
@import "../assets/vendor/swiper/swiper-bundle.min.css";
@import "../assets/css/main.css";

.bv-example-row.container{
  /* margin-left:250px; */
  margin-left:13.0208vw;
  width:53.3333vw;
}

</style>
