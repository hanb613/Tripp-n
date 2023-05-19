<template>
  <header id="header" class="header d-flex align-items-center">
    <div
      class="container-fluid container-xl d-flex align-items-center justify-content-between"
    >
      <a href="/" class="d-flex align-items-center">
        <img
          src="@/assets/trippin-logo-shadow.png"
          alt=""
          style="height: 70px"
        />
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
      <!-- after login -->
      <b-navbar-nav class="ml-auto" v-if="userInfo">
        <b-nav-item class="align-self-center">
          <!-- <b-avatar
            variant="primary"
            v-text="userInfo.userid.charAt(0).toUpperCase()"
          ></b-avatar> -->
          {{ userInfo.username }}({{ userInfo.userid }})님 환영합니다.
        </b-nav-item>
        <b-nav-item class="align-self-center">
          <router-link
            :to="{ name: 'UserMyPage' }"
            class="link align-self-center"
            >마이페이지</router-link
          >
        </b-nav-item>
        <b-nav-item
          class="align-self-center link"
          @click.prevent="onClickLogout"
          >로그아웃</b-nav-item
        >
      </b-navbar-nav>
      <!-- before login -->
      <b-navbar-nav class="ml-auto" v-else>
        <b-nav-item-dropdown right>
          <template #button-content>
            <b-icon icon="people" font-scale="2"></b-icon>
          </template>
          <b-dropdown-item href="#">
            <router-link :to="{ name: 'UserRegister' }" class="link">
              <b-icon icon="person-circle"></b-icon> 회원가입
            </router-link>
          </b-dropdown-item>
          <b-dropdown-item href="#">
            <router-link :to="{ name: 'UserLogin' }" class="link">
              <b-icon icon="key"></b-icon> 로그인
            </router-link>
          </b-dropdown-item>
        </b-nav-item-dropdown>
      </b-navbar-nav>
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
#imgDiv {
  /* width: 200%; */
  /* height: 100%; */
}
#logo {
  width: 100%;
  /* height: 100%; */
}
@import "../../assets/vendor/bootstrap/css/bootstrap.min.css";
@import "../../assets/vendor/bootstrap-icons/bootstrap-icons.css";
@import "../../assets/vendor/aos/aos.css";
@import "../../assets/vendor/glightbox/css/glightbox.min.css";
@import "../../assets/vendor/swiper/swiper-bundle.min.css";
@import "../../assets/css/main.css";
</style>
  