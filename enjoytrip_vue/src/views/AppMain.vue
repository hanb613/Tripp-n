<template>
  <b-container class="bv-example-row">
    <b-row> <h2>Welcome</h2></b-row>
    <b-row>
      <b-col
        ><div>
          <!-- v-model="slide" -->
          <b-carousel
            id="carousel-1"
            :interval="4000"
            controls
            indicators
            background="#ababab"
            img-width="1024"
            img-height="480"
            style="text-shadow: 1px 1px 2px #333"
            ><!-- Text slides with image -->
            <b-carousel-slide
              caption="Here"
              text="description"
              img-src="https://picsum.photos/1024/480/?image=52"
            ></b-carousel-slide>
            <b-carousel-slide
              caption="Here"
              text="description"
              img-src="https://picsum.photos/1024/480/?image=54"
            ></b-carousel-slide>
            <b-carousel-slide
              caption="Here"
              text="description"
              img-src="https://picsum.photos/1024/480/?image=58"
            ></b-carousel-slide>
          </b-carousel>
        </div>
      </b-col>
      <b-col>right</b-col>
    </b-row>
  </b-container>
</template>

<script>
export default {
  name: "AppMain",
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
</style>
