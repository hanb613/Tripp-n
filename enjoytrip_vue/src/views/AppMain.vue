<template>
<attraction-carousel/>
</template>

<script>
import AttractionCarousel from '@/components/attraction/AttractionCarousel.vue';
export default {
  name: "AppMain",
  components:{
    AttractionCarousel,
  },
  data() {
    return {
      msg: null,
    };
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
