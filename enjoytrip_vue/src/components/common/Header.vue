<template>
  <header id="header" class="header d-flex align-items-center">
    <div
      class="container-fluid container-xl d-flex align-items-center justify-content-between"
    >
      <a href="/" class="logo d-flex align-items-center">
        <h1>Enjoy Trip<span>.</span></h1>
      </a>
      <nav id="navbar" class="navbar">
        <b-nav-item href="#">
          <router-link :to="{ name: 'attraction' }" class="link">
            <b-icon icon="map" font-scale="2"></b-icon>
            관광지 조회
          </router-link>
          <router-link :to="{ name: 'board' }" class="m-2 link">
            <b-icon icon="megaphone" font-scale="2"></b-icon>
            공지사항
          </router-link>
          <router-link :to="{ name: 'board' }" class="m-2 link">
            <b-icon icon="clipboard" font-scale="2"></b-icon>
            게시판
          </router-link>
          <router-link :to="{ name: 'house' }" class="m-2 link">
            <b-icon icon="bookmark-star" font-scale="2"></b-icon>
            관심 목록
          </router-link>
        </b-nav-item>
      </nav>
      <!-- <i class="mobile-nav-toggle mobile-nav-show bi bi-list"></i>
      <i class="mobile-nav-toggle mobile-nav-hide d-none bi bi-x"></i> -->
    </div>
  </header>
</template>
  
  <script>
export default {
  name: "HeaderView",
};
</script>
  
<style>
.navbar .nav-item {
  list-style: none;
}
@import "../../assets/vendor/bootstrap/css/bootstrap.min.css";
@import "../../assets/vendor/bootstrap-icons/bootstrap-icons.css";
@import "../../assets/vendor/aos/aos.css";
@import "../../assets/vendor/glightbox/css/glightbox.min.css";
@import "../../assets/vendor/swiper/swiper-bundle.min.css";
@import "../../assets/css/main.css";
</style>
  