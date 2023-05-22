<template>
    <b-container class="bv-example-row mt-3">
      <b-row>
        <b-col>
          <b-alert variant="secondary" show><h3>비밀번호 찾기</h3></b-alert>
        </b-col>
      </b-row>
      <b-row>
        <b-col></b-col>
        <b-col cols="8">
          <b-card class="text-center mt-3" style="max-width: 40rem" align="left">
            <b-form class="text-left">
            <b-form-group label="아이디:" label-for="id">
                <b-form-input
                  id="id"
                  v-model="user.id"
                  required
                  placeholder="아이디 입력...."
                  @keyup.enter="confirm"
                ></b-form-input>
            </b-form-group>
              <b-form-group label="이름:" label-for="name">
                <b-form-input
                  id="name"
                  v-model="user.name"
                  required
                  placeholder="이름 입력...."
                  @keyup.enter="confirm"
                ></b-form-input>
              </b-form-group>
              <b-form-group label="이메일:" label-for="email">
                <b-form-input
                  id="email"
                  v-model="user.email"
                  required
                  placeholder="이메일 입력...."
                  @keyup.enter="confirm"
                ></b-form-input> </b-form-group
              >
              <b-alert show variant="danger" v-if="isPwFindError"
                >입력하신 회원님의 정보를 다시 확인하세요.</b-alert
              >
              <b-alert show variant="primary" v-if='!isPwFindError && userFindPw!==""'
                >회원님의 PW : <span style="text-decoration: underline;"><b>{{ this.userFindPw }}</b></span></b-alert
              >
              <br />
              <div style="display: flex; justify-content: center">
                <b-button
                  type="button"
                  variant="success"
                  class="m-1"
                  @click="movePage"
                  >이전</b-button
                > 
                <b-button
                  type="button"
                  variant="primary"
                  class="m-1"
                  @click="confirm"
                  >확인</b-button >
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
    name: 'UserFindPw',
    components: {},
    data() {
        return {
            user: {
                id: "",
                name: "",
                email: "",
            }
        }
    },
    created() {
        this.$store.commit(`${memberStore}/SET_USER_FIND_PW`, "")
        this.$store.commit(`${memberStore}/SET_IS_PW_FIND_ERROR`, false)
    },
    computed: {
    ...mapState(memberStore, ["isPwFindError","userFindPw"]),
  },
  methods: {
    ...mapActions(memberStore, ["userPwConfirm"]),

    async confirm() {
      await this.userPwConfirm(this.user);
      if (this.isPwFindError) {
        this.$store.commit(`${memberStore}/SET_USER_FIND_PW`, "");
        this.$store.commit(`${memberStore}/SET_IS_PW_FIND_ERROR`, true);
      } else {
        if (this.userFindPw.length <= 2) {
            this.$store.commit(`${memberStore}/SET_USER_FIND_PW`, "*".repeat(this.userFindPw.length));
        } else {
            this.$store.commit(`${memberStore}/SET_USER_FIND_PW`, this.userFindPw.slice(0, 2) + "*".repeat(this.userFindPw.length - 2));
        }
      }
    },
    movePage() {
        this.$router.push({ name: "UserLogin" });
    },
  },
};
</script>

<style scoped></style>