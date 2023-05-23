<template>
  <b-container class="bv-example-row mt-3 ml-1">
    <b-row class="mb-1">
      <b-col class="text-right">
        <b-button
          variant="outline-primary"
          v-if="checkAuthority()"
          @click="moveWrite()"
          >글쓰기</b-button
        >
      </b-col>
    </b-row>
    <b-row>
      <b-col style="cursor: pointer">
        <b-table
          striped
          hover
          :items="articles"
          :fields="fields"
          @row-clicked="viewArticle"
        >
        </b-table>
      </b-col>
    </b-row>
  </b-container>
</template>

<script>
import { mapState } from "vuex";

import { listArticle } from "@/api/board";
const memberStore = "memberStore";
const boardStore = "boardStore";

export default {
  name: "BoardList",
  data() {
    return {
      articles: [],
      likeCnts: [],
      fields: [
        { key: "boardNo", label: "글번호", tdClass: "tdClass", thClass: "th" },
        { key: "subject", label: "제목", tdClass: "tdSubject" },
        { key: "userName", label: "작성자", tdClass: "tdClass", thClass: "th" },
        {
          key: "createTime",
          label: "작성일",
          tdClass: "tdClass",
          thClass: "th",
        },
        { key: "like", label: "좋아요", tdClass: "tdClass", thClass: "th" },
      ],
    };
  },
  computed: {
    ...mapState(memberStore, ["userInfo"]),
    ...mapState(boardStore, ["isNotice"]),
  },
  watch: {
    isNotice() {
      this.getBoardArticles();
    },
  },
  created() {
    this.getBoardArticles();
  },
  methods: {
    getBoardArticles() {
      let param = {
        pg: 1,
        spp: 20,
        key: null,
        word: null,
        //공지사항 구분
        boardType: this.isNotice ? 1 : 2,
      };
      listArticle(
        param,
        ({ data }) => {
          // console.log(data);
          // 글정보 articles에 좋아요수 likeCnts를 합친다
          this.likeCnts = data.likedList;
          this.articles = data.articleList.map((article, index) => {
            return { ...article, like: this.likeCnts[index] };
          });
        },
        (error) => {
          console.log(error);
        }
      );
    },
    moveWrite() {
      if (this.userInfo !== null) {
        this.$router.push({
          name: this.isNotice ? "noticewrite" : "boardwrite",
        });
      } else {
        alert("로그인 후 이용해주세요!");
        this.$router.push({ name: "UserLogin" });
      }
    },
    viewArticle(article) {
      if (this.userInfo !== null) {
        this.$router.push({
          name: "boardview",
          params: { boardNo: article.boardNo, userNo: article.userNo },
        });
      } else {
        alert("로그인 후 이용해주세요!");
        this.$router.push({ name: "UserLogin" });
      }
    },
    checkAuthority() {
      //공지사항 작성/수정/삭제는 admin만 가능
      return this.userInfo && (this.userInfo.id === "admin" || !this.isNotice);
    },
  },
};
</script>

<style scope>
.tdClass {
  width: 50px;
  text-align: center;
}
.tdSubject {
  width: 300px;
  text-align: left;
}
.th {
  text-align: center;
}

.bv-example-row.container {
    width: 57.8125vw;
    height: 100%;
    display: flex;
    flex-direction: column;
    justify-content: flex-start;
}
</style>
