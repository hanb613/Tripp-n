<template>
  <b-container class="bv-example-row mt-3 ml-1">
    <b-row>
      <b-col>
        <b-alert variant="secondary" show><h3>회원가입</h3></b-alert>
      </b-col>
    </b-row>
    <b-row>
      <b-col></b-col>
      <b-col cols="8">
        <b-card class="text-center mt-3" style="max-width: 40rem" align="left">
          <b-form class="text-left" @submit="onSubmit" @reset="onReset">
            <!-- <b-alert show variant="danger" v-if="isInputError">회원정보를 모두 입력해주세요.</b-alert> -->
            <b-form-group label="아이디" label-for="id">
              <b-form-input
                id="id"
                v-model="user.id"
                required
                placeholder="아이디 입력...."
                @keyup.enter="confirm"
              ></b-form-input>
            </b-form-group>
            <b-form-group label="이름" label-for="name">
              <b-form-input
                type="text"
                id="name"
                v-model="user.name"
                required
                placeholder="이름 입력...."
                @keyup.enter="confirm"
              ></b-form-input>
            </b-form-group>
            <b-form-group label="비밀번호" label-for="password">
              <b-form-input
                type="password"
                id="password"
                v-model="user.password"
                required
                placeholder="비밀번호 입력...."
                @keyup.enter="confirm"
                autocomplete="off"
              ></b-form-input>
            </b-form-group>
            <b-form-group label="이메일" label-for="email">
              <b-input-group>
                <b-col cols="5">
                  <b-form-input
                    type="text"
                    id="email"
                    v-model="user.email"
                    required
                    placeholder="이메일 입력...."
                    @keyup.enter="confirm"
                  ></b-form-input>
                </b-col>
                @
                <b-col cols="5">
                  <b-form-select
                    class="sm-3"
                    v-model="user.domain"
                    :options="options2"
                  ></b-form-select>
                </b-col>
              </b-input-group>
            </b-form-group>
            <b-form-group label="지역 선택" label-for="sidoNo">
              <location-select-item @update-loc-no="updateLocNo" />
              <!-- <b-form-select v-model="sidoNo" :options="sidos"></b-form-select> -->
            </b-form-group>
            <b-form-group label="나이" label-for="age">
              <b-form-input
                type="number"
                id="age"
                v-model="user.age"
                required
                placeholder="나이 입력...."
                @keyup.enter="confirm"
              ></b-form-input>
            </b-form-group>
            <b-form-group label="성별" label-for="gender">
              <b-form-select
                class="sm-3"
                v-model="user.gender"
                :options="options"
              ></b-form-select>
            </b-form-group>
            <div style="display: flex; justify-content: center">
              <b-button type="submit" variant="success" class="m-1"
                >회원가입</b-button
              >
              <b-button type="reset" variant="primary" class="m-1"
                >초기화</b-button
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
import LocationSelectItem from "@/components/user/item/LocationSelectItem.vue";
import { register } from "@/api/member";
import { mapState } from "vuex";

const attractionStore = "attractionStore";

export default {
  name: "UserRegister",
  components: {
    LocationSelectItem,
  },
  data() {
    return {
      user: {
        id: "",
        name: "",
        password: "",
        email: "",
        domain: null,
        locNo: 1001,
        age: "",
        gender: null,
      },
      options: [
        { value: null, text: "성별선택" },
        { value: "M", text: "남자" },
        { value: "F", text: "여자" },
      ],
      options2: [
        { value: null, text: "도메인 선택" },
        { value: "ssafy.com", text: "ssafy.com" },
        { value: "naver.com", text: "naver.com" },
        { value: "gmail.com", text: "gmail.com" },
        { value: "kakao.com", text: "kakao.com" },
      ],
    };
  },
  computed: {
    ...mapState(attractionStore, ["sidos", "guguns"]),
  },
  methods: {
    updateLocNo(locNo) {
      return (this.user.locNo = locNo);
    },
    onSubmit(event) {
      event.preventDefault();

      let param = {
        id: this.user.id,
        name: this.user.name,
        password: this.user.password,
        email: this.user.email + "@" + this.user.domain,
        locNo: this.user.locNo,
        age: this.user.age,
        gender: this.user.gender,
      };

      let msg = "회원 정보를 정확히 입력해주세요! 😭";
      register(
        param,
        ({ data }) => {
          if (data === "success") {
            msg = "회원 등록 완료! 😍";
          }
          alert(msg);
          this.moveLogin();
        },
        (error) => {
          alert(msg);
          console.log(error);
        }
      );
    },
    onReset(event) {
      event.preventDefault();
      this.user.id = "";
      this.user.name = "";
      this.user.password = "";
      this.user.email = "";
      this.user.locNo = "";
      this.user.age = "";
      this.user.gender = null;
      this.moveLogin();
    },
    moveLogin() {
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
