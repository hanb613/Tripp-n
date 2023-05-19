<template>
  <footer id="footer" class="footer">
    <div class="container">
      <div class="credits">
        © Copyright All Rights Reserved
      </div>
      <div class="copyright">
        Developer Yeong-seo Yoo, Kyeong-hee Shin<br>
      </div>
    </div>
  </footer>
</template>
  
  <script>
export default {
  name: "FooterView",
};
</script>
  
  <style>
#footer {
  bottom: 0;
  position: relative;
  width: 100vw;
  height: 9vw;
  margin-top: 30px;
}
.container{
  width:100%;
  height: 100%;
  display: flex;
  flex-direction: column;
  justify-content: center;
  align-content: center;
}
@import "../../assets/vendor/bootstrap/css/bootstrap.min.css";
@import "../../assets/vendor/aos/aos.css";
@import "../../assets/vendor/glightbox/css/glightbox.min.css";
@import "../../assets/vendor/swiper/swiper-bundle.min.css";
@import "../../assets/css/main.css";
</style>
  