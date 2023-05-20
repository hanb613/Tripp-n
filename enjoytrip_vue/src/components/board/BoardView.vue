<template>
  <b-container class="bv-example-row mt-3">
    <b-row class="mb-1">
      <!-- <b-col class="text-right" v-if="userInfo.userName === article.userName"> -->
      <b-col class="text-right">
        <b-button
          variant="outline-primary"
          size="sm"
          @click="moveList"
          class="mr-2"
          >목록</b-button
        >
        <b-button
          variant="outline-info"
          size="sm"
          @click="moveModifyArticle"
          class="mr-2"
          >수정</b-button
        >
        <b-button variant="outline-danger" size="sm" @click="deleteArticle"
          >삭제</b-button
        >
      </b-col>
    </b-row>
    <b-row class="mb-1">
      <b-col>
        <b-card
          :header-html="`<h3>
          ${article.subject}</h3><div><h6>${article.userName}</div><div><h6>[좋아요 : ${article.like}]</div><div>${article.createTime}</h6></div>`"
          class="mb-2"
          border-variant="dark"
          no-body
        >
          <b-card-body class="text-left">
            <div v-html="message"></div>
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
    <h4><b-icon icon="list"></b-icon> 댓글</h4>
    <comment-write :boardNo="this.article.boardNo"></comment-write>
    <comment-list :boardNo="this.article.boardNo"></comment-list>
  </b-container>
</template>

<script>
// import moment from "moment";
import { getArticle } from "@/api/board";
import CommentWrite from "@/components/board/comment/CommentWrite.vue";
import CommentList from "@/components/board/comment/CommentList.vue";
//import { mapState } from "vuex";

// const memberStore = "memberStore";

export default {
  name: "BoardDetail",
  data() {
    return {
      article: {},
    };
  },
  components: {
    CommentWrite,
    CommentList,
  },
  computed: {
    //...mapState(memberStore, ["userInfo"]),
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
        this.article = data;
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
  },
};
</script>

<style></style>
