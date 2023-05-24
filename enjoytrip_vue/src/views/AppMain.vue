<template>
<b-container fluid>
  <b-row class="text-center">
    <b-card class="background"
      overlay
      :img-src="sampleImage"
      img-alt="Card Image"
      text-variant="white"
      style="text-shadow: 2px 2px 5px #0f0f0f; padding:0; margin:0;" 
    >
      <b-card-text data-aos="fade-up" data-aos-duration="2000">
        <br/><br/>
          <div class="font">
            <h1 style="font-family: 'Noto Sans KR', sans-serif">Welcome to Tripp'n</h1>
            <span style="font-family: 'Noto Sans KR', sans-serif">
              Pin your favorite place and share
            </span>
        </div>
      </b-card-text>
    </b-card>
  </b-row>
  <b-row  style="height:100px;"></b-row>
  <b-row data-aos="fade-left" data-aos-duration="2000" class="ml-3">

    <b-col data-aos="fade-up" data-aos-duration="2000" class="mt-5 ml-1"> 
      <h2 style="margin:0; font-family: 'Jua', sans-serif;">최근 사람들의 관심 관광지</h2>
      <attraction-carousel/> 
    </b-col>
    
    <b-col data-aos="fade-up" data-aos-duration="2000" class="mt-5 ml-1"> 
      <h2 style="font-family: 'Jua', sans-serif">사람들이 많이 가는 관광지</h2>
      <chart-test style="width:40vw; height:450px; margin:0; "/>
    </b-col>
  </b-row>
  <b-row class="temp"></b-row>
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
      sampleImage: "/countryside2.jpg",
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

@import url("https://fonts.googleapis.com/css2?family=Noto+Sans+KR&display=swap");
@import url('https://fonts.googleapis.com/css2?family=Do+Hyeon&family=Hahmlet:wght@600&family=Jua&display=swap');

.font {
  font-family: "Noto Sans KR", sans-serif;
}

.bv-example-row.container{
  /* margin-left:250px; */
  margin-left:13.0208vw;
  width:53.3333vw;
}

.card{
  margin-top:50px
}
.carousel-item{
  margin-top:30px;
}
.chart.container{
  padding:0;
}

.temp{
  height:200px;
}

</style>
