<template>
  <b-container class="bv-example-row mt-3 ml-1">
    <b-row>
      <b-col>
        <b-alert variant="secondary" show
          ><h3 style="font-family: 'Noto Sans KR', sans-serif">
            아이디 찾기
          </h3></b-alert
        >
      </b-col>
    </b-row>
    <b-row>
      <b-col></b-col>
      <b-col cols="8">
        <b-card class="text-center mt-3" style="max-width: 40rem" align="left">
          <b-form class="text-left">
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
              ></b-form-input>
            </b-form-group>
            <b-alert show variant="danger" v-if="isIdFindError"
              >아이디 또는 이메일을 확인하세요.</b-alert
            >
            <b-alert
              show
              variant="primary"
              v-if="!isIdFindError && userFindId !== ''"
              >회원님의 ID :
              <span style="text-decoration: underline"
                ><b>{{ this.userFindId }}</b></span
              ></b-alert
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
                >확인</b-button
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
  name: "UserFindId",
  components: {},
  data() {
    return {
      user: {
        name: "",
        email: "",
      },
    };
  },
  created() {
    this.$store.commit(`${memberStore}/SET_USER_FIND_ID`, "");
    this.$store.commit(`${memberStore}/SET_IS_ID_FIND_ERROR`, false);
  },
  computed: {
    ...mapState(memberStore, ["isIdFindError", "userFindId"]),
  },
  methods: {
    ...mapActions(memberStore, ["userIdConfirm"]),

    async confirm() {
      if (this.user.id !== "" && this.user.email !== "") {
        await this.userIdConfirm(this.user);
        if (this.isIdFindError) {
          this.$store.commit(`${memberStore}/SET_USER_FIND_ID`, "");
          this.$store.commit(`${memberStore}/SET_IS_ID_FIND_ERROR`, true);
        }
      } else {
        this.$store.commit(`${memberStore}/SET_IS_ID_FIND_ERROR`, true);
      }
    },
    movePage() {
      this.$router.push({ name: "UserLogin" });
    },
  },
};
</script>

<style scoped>
.bv-example-row.container {
  width: 57.8125vw;
}
</style>
