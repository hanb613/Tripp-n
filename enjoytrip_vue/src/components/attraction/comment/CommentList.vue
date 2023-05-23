<template>
  <b-container class="bv-example-row mt-3 ml-1">
    <b-row>
      <b-col>
        <b-alert show variant="primary" v-if="comments.length === 0"
          >작성된 댓글이 없습니다.</b-alert
        >
        <b-table v-else :items="comments" :fields="fields" class="font">
          <template #cell(userName)="data">
            <div class="d-flex justify-content-between align-items-center">
              <div>{{ data.item.userName }}</div>
              <div v-if="data.item.userNo === userInfo.userNo">
                <b-button
                  variant="outline-danger"
                  size="sm"
                  @click="deleteItem(data.item)"
                >
                  삭제
                </b-button>
              </div>
            </div>
          </template>
          <template #cell(content)="data">
            {{ data.item.content }}
          </template>
        </b-table>
      </b-col>
    </b-row>
  </b-container>
</template>

<script>
import { listAttrComment, deleteAttrComment } from "@/api/comment";
import { mapState } from "vuex";

const memberStore = "memberStore";

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
  computed: {
    ...mapState(memberStore, ["userInfo"]),
  },
  methods: {
    deleteItem(data) {
      let param = data.commentNo;

      deleteAttrComment(
        param,
        ({ data }) => {
          let msg = "삭제 처리시 문제가 발생했습니다.";
          if (data === "success") {
            msg = "삭제가 완료되었습니다.";
          }
          alert(msg);
          this.$router.go(0);
        },
        (error) => {
          console.log(error);
        }
      );
    },
  },
};
</script>

<style scope>
@import url("https://fonts.googleapis.com/css2?family=Noto+Sans+KR&display=swap");

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

.font {
  font-family: "Noto Sans KR", sans-serif;
}
</style>
