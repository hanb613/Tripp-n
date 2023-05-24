<template>
  <b-container fluid>
    <b-row class="text-center">
      <div id="background-div">
        <b-card-text data-aos="fade-up" data-aos-duration="2000">
          <br /><br />
          <div class="font" style="text-shadow: 3px 3px 5px #000010">
            <h1 style="font-family: 'Noto Sans KR', sans-serif">
              Welcome to Tripp'n
            </h1>
            <span style="font-family: 'Noto Sans KR', sans-serif">
              Pin your favorite place and share
            </span>
          </div>
        </b-card-text>

        <b-row class="scroll-row" data-aos="fade-down" v-show="showArrow">
          <b-col class="scroll-col">
            <a href="#dataContent" class="scroll">
              <span class="scrollA"></span>
              <span class="scrollA"></span>
              <span class="scrollA"></span>
            </a>
          </b-col>
        </b-row>
      </div>
    </b-row>

    <b-row style="height: 100px"></b-row>
    <b-row data-aos-duration="2000" class="ml-3" id="dataContent">
      <b-col data-aos="fade-up" data-aos-duration="2000" class="mt-5 ml-1">
        <h2 style="margin: 0; font-family: 'Jua', sans-serif">
          최근 사람들의 관심 관광지
        </h2>
        <attraction-carousel />
      </b-col>

      <b-col data-aos="fade-up" data-aos-duration="2000" class="mt-5 ml-1">
        <h2 style="font-family: 'Jua', sans-serif">
          사람들이 많이 가는 관광지
        </h2>
        <chart-test style="width: 40vw; height: 450px; margin: 0" />
      </b-col>
    </b-row>
    <b-row class="temp"></b-row>
  </b-container>
</template>

<script>
import AttractionCarousel from "@/components/attraction/AttractionCarousel.vue";
import ChartTest from "@/components/chart/ChartTest.vue";
import AOS from "aos";
import "aos/dist/aos.css";
export default {
  name: "AppMain",
  components: {
    AttractionCarousel,
    ChartTest,
  },
  data() {
    return {
      msg: null,
      sampleImage: "/countryside2.jpg",
      showArrow: true,
    };
  },
  created() {
    AOS.init();
  },
  mounted() {
    if (this.msg !== null) {
      alert(this.msg);
    }
    window.addEventListener("scroll", this.handleScroll);
  },
  beforeUnmount() {
    window.removeEventListener("scroll", this.handleScroll); // 컴포넌트 제거 전에 이벤트 리스너 제거
  },
  methods: {
    handleScroll() {
      const rowElement = document.querySelector(".text-center");
      const arrowElement = document.querySelector(".scroll");

      if (rowElement && arrowElement) {
        const rowRect = rowElement.getBoundingClientRect();
        const arrowRect = arrowElement.getBoundingClientRect();

        if (
          arrowRect.top <= rowRect.bottom &&
          arrowRect.bottom >= rowRect.top
        ) {
          this.showArrow = true; // 화살표 표시
        } else {
          this.showArrow = false; // 화살표 숨김
        }
      }
    },
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
@import url("https://fonts.googleapis.com/css2?family=Do+Hyeon&family=Hahmlet:wght@600&family=Jua&display=swap");
</style>

<style scope>
body {
  overflow-x: hidden;
}

#background-div {
  /* background: ; */
  width: 100vw;
  height: calc(100vh - 90px);
  background: url("../assets/img/countryside2.jpg");
  color: white;
  padding-top: 10vh;
}

.text-center {
  border: 0;
}

.font {
  font-family: "Noto Sans KR", sans-serif;
}

.bv-example-row.container {
  margin-left: 13.0208vw;
  width: 53.3333vw;
}

.card {
  margin-top: 50px;
}
.carousel-item {
  margin-top: 30px;
}
.chart.container {
  padding: 0;
}

.temp {
  height: 200px;
}

.scroll {
  position: relative;
  bottom: 0;
}

.scroll-row {
  position: fixed;
  bottom: 120px; /* 원하는 위치로 조정 가능 */
  left: 50%;
  transform: translateX(-50%);
  z-index: 9999; /* 다른 요소 위에 위치하도록 설정 */
}

.scroll-col {
  text-align: center;
}

.scrollA {
  position: absolute;
  top: 0;
  left: 50%;
  width: 30px;
  height: 30px;
  margin-left: -12px;
  border-left: 4px solid white;
  border-bottom: 4px solid white;
  -webkit-transform: rotate(-45deg);
  transform: rotate(-45deg);
  -webkit-animation: sdb 2s infinite;
  animation: sdb 2s infinite;
  opacity: 0;
  box-sizing: border-box;
}
a span:nth-of-type(1) {
  -webkit-animation-delay: 0s;
  animation-delay: 0s;
}
a span:nth-of-type(2) {
  top: 16px;
  -webkit-animation-delay: 0.15s;
  animation-delay: 0.15s;
}
a span:nth-of-type(3) {
  top: 32px;
  -webkit-animation-delay: 0.3s;
  animation-delay: 0.3s;
}
@-webkit-keyframes sdb {
  0% {
    opacity: 0;
  }
  50% {
    opacity: 1;
  }
  100% {
    opacity: 0;
  }
}
@keyframes sdb {
  0% {
    opacity: 0;
  }
  50% {
    opacity: 1;
  }
  100% {
    opacity: 0;
  }
}
</style>