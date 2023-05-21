<template>
  <b-container class="bv-example-row mt-3">
    <b-row class="mb-1">
      <b-col class="text-right">
        <b-button variant="outline-primary" @click="moveWrite()"
          >글쓰기</b-button
        >
      </b-col>
    </b-row>
    <b-row>
      <b-col>
        <b-table
          striped
          hover
          :items="articles"
          :fields="fields"
          @row-clicked="viewArticle"
        >
          <template #cell(subject)="data">
            <router-link
              :to="{
                name: 'boardview',
                params: { boardNo: data.item.boardNo },
              }"
            >
              {{ data.item.subject }}
            </router-link>
          </template>
        </b-table>
      </b-col>
    </b-row>
  </b-container>
</template>

<script>
import { mapState } from "vuex";

import { listArticle } from "@/api/board";
const memberStore = "memberStore";

export default {
  name: "BoardList",
  data() {
    return {
      articles: [],
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
  },
  created() {
    let param = {
      pg: 1,
      spp: 20,
      key: null,
      word: null,
    };
    listArticle(
      param,
      ({ data }) => {
        console.log(data);
        this.articles = data;
      },
      (error) => {
        console.log(error);
      }
    );
  },
  methods: {
    moveWrite() {
      if (this.userInfo !== null) {
        this.$router.push({ name: "boardwrite" });
      } else {
        alert("로그인 후 이용해주세요!");
        this.$router.push({ name: "UserLogin" });
      }
    },
    viewArticle(article) {
      this.$router.push({
        name: "boardview",
        params: { boardNo: article.boardNo },
      });
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
</style>
