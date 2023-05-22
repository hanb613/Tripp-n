<template>
  <b-container class="bv-example-row mt-3">
    <b-row class="mb-1">
      <!-- <b-col class="text-right" v-if="userInfo.userName === article.userName"> -->
      <b-col class="text-right font">
        <b-button
          variant="outline-primary"
          size="sm"
          @click="moveList"
          class="mr-2"
          >목록</b-button
        >

        <b-button
          v-if="this.article.userNo === this.userInfo.userNo"
          variant="outline-info"
          size="sm"
          @click="moveModifyArticle"
          class="mr-2"
          >수정</b-button
        >
        <b-button
          v-if="this.article.userNo === this.userInfo.userNo"
          variant="outline-danger"
          size="sm"
          @click="deleteArticle"
          >삭제</b-button
        >
      </b-col>
    </b-row>
    <b-row class="mb-1 font">
      <b-col>
        <b-card
          :header-html="`<span style='font-size:30px;'>
          ${article.subject}</span><div><span style='font-size:17px;'>${article.userName}</span></div><div class='font'><div>${article.createTime}</div>`"
          class="mb-2 font"
          border-variant="dark"
          no-body
        >
          <!-- 본문 -->
          <b-card-body class="text-left font">
            <div v-html="message"></div>
            <b-button
              pill
              class="me-2 float-right"
              variant="primary"
              id="likebtn"
              @click="toggleLike"
              v-if="userInfo"
            >
              <b-icon
                icon="suit-heart-fill"
                font-scale="1"
                style="margin-right: 5px"
                v-if="checkLiked()"
              ></b-icon>
              <b-icon
                icon="suit-heart"
                font-scale="1"
                style="margin-right: 5px"
                v-else
              ></b-icon>
              <span class="font">{{ likedUsers.length }}</span>
            </b-button>
          </b-card-body>
        </b-card>
      </b-col>
    </b-row>
    <br /><br />
    <!-- <b-row>
      <b-col>
        <b-alert show><h4>댓글</h4></b-alert>
      </b-col>
    </b-row> -->
    <h4><b-icon icon="list"></b-icon> <span class="font">댓글</span></h4>
    <comment-write :boardNo="this.article.boardNo"></comment-write>
    <comment-list :boardNo="this.article.boardNo"></comment-list>
  </b-container>
</template>

<script>
// import moment from "moment";
import { getArticle, likeArticle } from "@/api/board";
import CommentWrite from "@/components/board/comment/CommentWrite.vue";
import CommentList from "@/components/board/comment/CommentList.vue";
import { mapState } from "vuex";

const memberStore = "memberStore";

export default {
  name: "BoardDetail",
  data() {
    return {
      article: {},
      likedUsers: [],
    };
  },
  components: {
    CommentWrite,
    CommentList,
  },
  computed: {
    ...mapState(memberStore, ["userInfo"]),
    message() {
      if (this.article.content)
        return this.article.content.split("\n").join("<br>");
      return "";
    },
  },
  created() {
    let param = this.$route.params.boardNo;

    getArticle(
      param,
      ({ data }) => {
        console.log(data);
        this.article = data.article;
        this.likedUsers = data.likedUsers;
      },
      (error) => {
        console.log(error);
      }
    );
  },
  methods: {
    moveModifyArticle() {
      this.$router.replace({
        name: "boardmodify",
        params: { boardNo: this.article.boardNo },
      });
    },
    deleteArticle() {
      if (confirm("정말로 삭제?")) {
        this.$router.replace({
          name: "boarddelete",
          params: { boardNo: this.article.boardNo },
        });
      }
    },
    moveList() {
      this.$router.push({ name: "boardlist" });
    },
    toggleLike(event) {
      event.preventDefault();
      //임시: 좋아요 등록/삭제 중 등록만 구현
      if (this.checkLiked()) alert("이미 좋아요를 누른 게시글입니다.");
      else {
        let param = {
          boardNo: this.article.boardNo,
          userNo: this.userInfo.userNo,
        };
        console.log(param.boardNo + " is liked by " + param.userNo);
        likeArticle(
          param,
          ({ data }) => {
            let msg = "등록 처리시 문제가 발생했습니다.";
            if (data === "success") {
              msg = "좋아요 완료";
            }
            alert(msg);
            this.$router.go(0);
          },
          (error) => {
            console.log(error);
          }
        );
      }
    },
    checkLiked() {
      return this.likedUsers.some(
        (item) => item.userNo === this.userInfo.userNo
      );
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
