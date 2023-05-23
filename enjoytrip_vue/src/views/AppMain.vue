<template>
<b-container fluid>
  <b-row class="text-center">
  <b-card
    overlay
    :img-src="sampleImage"
    img-alt="Card Image"
    text-variant="white"
    style="text-shadow: 2px 2px 5px #0f0f0f"
  >
    <b-card-text data-aos="fade-up" data-aos-duration="2000">
      <br/><br/>
      <h1>Welcome to Tripp'n</h1>
      <span>
        Pin your favorite place and share
      </span>
    </b-card-text>
  </b-card>
  </b-row>
  <b-row data-aos="fade-left" data-aos-duration="2000">
    <h1>최근 사람들이 좋아한 관광지는?</h1>
  </b-row>
  <b-row data-aos="fade-up" data-aos-duration="2000"><attraction-carousel/> </b-row>
    <b-row data-aos="fade-left" data-aos-duration="2000" data-aos-delay="2000">
    <h1>가장 인기있는 지역은?</h1>
  </b-row>
  <b-row data-aos="fade-up" data-aos-duration="2000"><chart-test/></b-row>
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
