<template>
  <b-container class="mt-4" v-if="userInfo">
    <b-row>
      <b-col>
        <b-alert variant="secondary" show><h3 style="font-family: 'Noto Sans KR', sans-serif">내정보</h3></b-alert>
      </b-col>
    </b-row>
    <b-row>
      <b-col></b-col>
      <b-col cols="8">
        <b-jumbotron>
          <h1 class="display-5 font">My Page</h1>
          <br />

          <span class="font" style="font-size: 18px"
            >내 정보 확인페이지입니다.
          </span>

          <hr class="my-4" />

          <b-container class="mt-4 font">
            <b-row>
              <b-col cols="2"></b-col>
              <b-col cols="2" align-self="end">아이디</b-col
              ><b-col cols="4" align-self="start">{{ userInfo.id }}</b-col>
              <b-col cols="2"></b-col>
            </b-row>
            <b-row>
              <b-col cols="2"></b-col>
              <b-col cols="2" align-self="end">이름</b-col
              ><b-col cols="4" align-self="start">{{ userInfo.name }}</b-col>
              <b-col cols="2"></b-col>
            </b-row>
            <b-row>
              <b-col cols="2"></b-col>
              <b-col cols="2" align-self="end">이메일</b-col
              ><b-col cols="4" align-self="start">{{ userInfo.email }}</b-col>
              <b-col cols="2"></b-col>
            </b-row>
            <b-row>
              <b-col cols="2"></b-col>
              <b-col cols="2" align-self="end">가입일</b-col
              ><b-col cols="4" align-self="start">{{
                userInfo.joinDate | dateFormat
              }}</b-col>
              <b-col cols="2"></b-col>
            </b-row>
          </b-container>
          <hr class="my-4" />

          <b-button variant="primary" href="#" class="mr-1" @click="movePage"
            >정보수정</b-button
          >
          <b-button variant="danger" href="#" @click="remove"
            >회원탈퇴</b-button
          >
        </b-jumbotron>
      </b-col>
      <b-col></b-col>
    </b-row>
  </b-container>
</template>

<script>
import { mapState, mapActions } from "vuex";
import { removeUser } from "@/api/member";

const memberStore = "memberStore";

export default {
  name: "UserMyPage",
  components: {},
  data() {
    return {
      message: "",
    };
  },
  created() {},
  methods: {
    ...mapActions(memberStore, ["userLogout"]),

    remove() {
      let param = this.userInfo.userNo;

      removeUser(
        param,
        ({ data }) => {
          let msg = "회원 탈퇴시 문제가 발생했습니다 😢";
          if (data === "success") {
            msg = "다음에도 저희 Tripp'n을 이용해주세요 🙌";
            this.userLogout(param);
          }
          alert(msg);
          this.$router.push("/");
        },
        (error) => {
          console.log(error);
        }
      );
    },
    movePage() {
      this.$router.push({
        name: "UserModify",
        params: { userOriginInfo: this.userInfo },
      });
    },
  },
  computed: {
    ...mapState(memberStore, ["userInfo"]),
  },
  filters: {
    dateFormat: function (value) {
      if (value) {
        return new Date(value).toLocaleDateString();
      }
    },
  },
};
</script>

<style scoped>
@import url("https://fonts.googleapis.com/css2?family=Noto+Sans+KR&display=swap");

.font {
  font-family: "Noto Sans KR", sans-serif;
}
</style>
