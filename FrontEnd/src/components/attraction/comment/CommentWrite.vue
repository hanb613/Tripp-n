<template>
  <b-container class="bv-example-row mt-3 ml-1">
    <b-row class="mb-1">
      <b-col style="text-align: left">
        <b-form>
          <b-form-group id="content-group" label="내용:" label-for="content">
            <b-row>
              <b-col cols="11">
                <b-form-textarea
                  id="content"
                  v-model="comment.content"
                  placeholder="댓글 입력..."
                  rows="2"
                  max-rows="15"
                ></b-form-textarea>
              </b-col>
              <b-col
                cols="1"
                class="d-flex align-items-center justify-content-center"
              >
                <b-button
                  variant="outline-primary"
                  size="xl"
                  type="submit"
                  @click="registComment"
                  class="mr-2 d-flex align-items-center justify-content-center"
                  style="height: 100%"
                >
                  <span
                    class="d-inline-block text-truncate"
                    style="max-width: 100%; text-align: center"
                  >
                    완료
                  </span>
                </b-button>
              </b-col>
            </b-row>
          </b-form-group>
        </b-form>
      </b-col>
    </b-row>
  </b-container>
</template>

<script>
import { writeAttrComment } from "@/api/comment";
import { mapState, mapGetters } from "vuex";

const memberStore = "memberStore";

export default {
  name: "CommentWrite",
  data() {
    return {
      comment: {
        contentNo: "",
        userNo: "",
        content: "",
      },
      isUserid: false,
    };
  },
  components: {},
  computed: {
    ...mapState(memberStore, ["isLogin", "userInfo"]),
    ...mapGetters(["checkUserInfo"]),
  },
  methods: {
    registComment(event) {
      event.preventDefault();

      if (this.userInfo !== null) {
        // 로그인 했으면 작성 가능
        let msg = "";
        let err = true;
        let param = {
          //로그인유저의 정보
          contentNo: this.$route.params.contentNo,
          userNo: this.userInfo.userNo,
          content: this.comment.content,
        };

        console.log(param);
        !this.comment.content && ((msg = "내용을 입력해주세요"), (err = false));

        if (!err) alert(msg);
        else {
          writeAttrComment(
            param,
            ({ data }) => {
              msg = "다시 써주세요! 😢";
              if (data === "success") {
                msg = "댓글 작성 완료!";
              }
              alert(msg);
              this.comment.content = "";
              this.$router.go(0);
            },
            (error) => {
              console.log(error);
            }
          );
        }
      }
      // 로그인 안 했을 때 댓글 작성 X
      else {
        alert("로그인 후 이용해주세요!");
        this.$router.push({ name: "UserLogin" });
      }
    },
    modifyComment(event) {
      event.preventDefault();
    },
    deleteComment(event) {
      event.preventDefault();
    },
  },
};
</script>

<style scoped></style>
