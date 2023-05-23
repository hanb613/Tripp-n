<template>
  <b-container class="bv-example-row mt-3 ml-1">
    <b-row>
      <b-col>
        <b-alert variant="secondary" show><h3>로그인</h3></b-alert>
      </b-col>
    </b-row>
    <b-row>
      <b-col></b-col>
      <b-col cols="8">
        <b-card class="text-center mt-3" style="max-width: 40rem" align="left">
          <b-form class="text-left">
            <b-alert show variant="danger" v-if="isLoginError"
              >아이디 또는 비밀번호를 확인하세요.</b-alert
            >
            <b-form-group label="아이디:" label-for="id">
              <b-form-input
                id="id"
                v-model="user.id"
                required
                placeholder="아이디 입력...."
                @keyup.enter="confirm"
              ></b-form-input>
            </b-form-group>
            <b-form-group label="비밀번호:" label-for="password">
              <b-form-input
                type="password"
                id="password"
                v-model="user.password"
                required
                placeholder="비밀번호 입력...."
                @keyup.enter="confirm"
              ></b-form-input>
            </b-form-group>

            <span style="font-size: 13px; float: right">
              <span
                @click="movePage('findId')"
                style="color: blue; cursor: pointer"
                >아이디 찾기
              </span>
              &nbsp;|&nbsp;&nbsp;
              <span
                @click="movePage('findPw')"
                style="color: blue; cursor: pointer"
                >비밀번호 찾기</span
              > </span
            ><br /><br />

            <div style="display: flex; justify-content: center">
              <b-button
                type="button"
                variant="primary"
                class="m-1"
                @click="confirm"
                >로그인</b-button
              >
              <b-button
                type="button"
                variant="success"
                class="m-1"
                @click="movePage('register')"
                >회원가입</b-button
              >
            </div>
          </b-form>
        </b-card>
      </b-col>
      <b-col></b-col>
    </b-row>
  </b-container>
</template>

<script>
import { mapState, mapActions } from "vuex";

const memberStore = "memberStore";

export default {
  name: "UserLogin",
  data() {
    return {
      user: {
        id: "",
        password: "",
      },
    };
  },
  created() {
    this.$store.commit(`${memberStore}/SET_IS_LOGIN_ERROR`, false);
  },
  computed: {
    ...mapState(memberStore, ["isLogin", "isLoginError", "userInfo"]),
  },
  methods: {
    ...mapActions(memberStore, ["userConfirm", "getUserInfo"]),

    async confirm() {
      await this.userConfirm(this.user);
      if (this.isLogin) {
        alert("멋진 여행 계획을 세워보세요 ! 😎");
        //await this.getUserInfo(this.user);
        this.$router.push("/");
      }
    },
    movePage(data) {
      if (data === "register") {
        this.$router.push({ name: "UserRegister" });
      } else if (data === "findId") {
        this.$router.push({ name: "UserFindId" });
      } else if (data === "findPw") {
        this.$router.push({ name: "UserFindPw" });
      }
    },
  },
};
</script>

<style scoped>
.bv-example-row.container {
  width: 57.8125vw;
}
</style>
