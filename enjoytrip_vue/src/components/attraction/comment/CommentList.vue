<template>
  <b-container class="bv-example-row mt-3 ml-1">
    <b-row>
      <b-col>
        <b-table :items="comments" :fields="fields" class="font"> </b-table>
      </b-col>
    </b-row>
  </b-container>
</template>

<script>
import { listAttrComment } from "@/api/comment";

export default {
  name: "CommentList",
  data() {
    return {
      comments: [],
      fields: [
        { key: "content", label: "내용", tdClass: "tdSubject", thClass: "th" },
        { key: "userName", label: "작성자", tdClass: "tdClass", thClass: "th" },
      ],
    };
  },
  created() {
    let param = this.$route.params.contentNo;

    listAttrComment(
      param,
      ({ data }) => {
        console.log(data);
        this.comments = data;
      },
      (error) => {
        console.log(error);
      }
    );
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

@import url("https://fonts.googleapis.com/css2?family=Noto+Sans+KR&display=swap");

.font {
  font-family: "Noto Sans KR", sans-serif;
}
</style>
