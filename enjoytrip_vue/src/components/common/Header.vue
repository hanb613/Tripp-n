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
      <b-nav id="navbar" class="navbar">
        <b-nav-item href="#">
          <router-link :to="{ name: 'attraction' }" class="link">
            <b-icon icon="map" font-scale="2" class="m-1"></b-icon>
            관광지 조회
          </router-link>
          <router-link :to="{ name: 'board' }" class="m-2 link">
            <b-icon icon="megaphone" font-scale="2" class="m-1"></b-icon>
            공지사항
          </router-link>
          <router-link :to="{ name: 'board' }" class="m-2 link">
            <b-icon icon="clipboard" font-scale="2" class="m-1"></b-icon>
            게시판
          </router-link>
          <router-link :to="{ name: 'house' }" class="m-2 link">
            <b-icon icon="bookmark-star" font-scale="2" class="m-1"></b-icon>
            관심 목록
          </router-link>
        </b-nav-item>
      </b-nav>
      <!-- after login -->
      <b-navbar-nav class="ml-auto navbar" v-if="userInfo">
        <b-nav-item right>
          <b-dropdown
            id="dropdown-1"
            :text="userInfo.name"
            class="m-md-2"
            variant="outline-light"
          >
            <b-dropdown-item class="link" @click="movePage">
              마이페이지
            </b-dropdown-item>
            <b-dropdown-item class="link" @click.prevent="onClickLogout"
              >로그아웃</b-dropdown-item
            >
          </b-dropdown>
        </b-nav-item>
      </b-navbar-nav>
      <!-- before login -->
      <b-navbar-nav class="ml-auto navbar" v-else>
        <b-nav-item right>
          <b-nav-item-dropdown>
            <template #button-content>
              <b-icon icon="people" font-scale="2"></b-icon>
            </template>
            <b-dropdown-item>
              <router-link :to="{ name: 'UserRegister' }" class="link">
                <b-icon icon="person-circle"></b-icon>&nbsp; 회원가입
              </router-link>
            </b-dropdown-item>
            <b-dropdown-item>
              <router-link :to="{ name: 'UserLogin' }" class="link">
                <b-icon icon="key"></b-icon>&nbsp;&nbsp; 로그인
              </router-link>
            </b-dropdown-item>
          </b-nav-item-dropdown>
        </b-nav-item>
      </b-navbar-nav>
    </div>
  </header>
</template>
  
<script>
import { mapState, mapGetters, mapActions } from "vuex";

const memberStore = "memberStore";

export default {
  name: "HeaderView",
  data() {
    return {};
  },
  computed: {
    ...mapState(memberStore, ["isLogin", "userInfo"]),
    ...mapGetters(["checkUserInfo"]),
  },
  methods: {
    ...mapActions(memberStore, ["userLogout"]),
    // ...mapMutations(memberStore, ["SET_IS_LOGIN", "SET_USER_INFO"]),
    onClickLogout() {
      this.userLogout(this.userInfo.id);
      alert("Logout! 🙌");
      if (this.$route.path != "/") this.$router.push("/");
    },
    movePage() {
      this.$router.push({ name: "UserMyPage" });
    },
  },
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
