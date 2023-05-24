<template>
  <b-container class="bv-example-row mt-3 ml-1">
    <b-row class="mb-1">
      <b-col>
        <b-card
          :img-src="attraction.firstImage"
          class="mb-2 img"
          img-alt="관광지 사진"
        >
          <b-card-text>
            <h2 class="font">
              <b>{{ attraction.title }}</b>
            </h2>
            <br />
            <span class="font"
              ><b>유형</b><br />
              {{ contentTypeName() }}</span
            ><br /><br />
            <span class="font"><b>주소</b><br />{{ attraction.addr1 }}</span
            ><br /><br />

            <span class="font" v-if="attraction.tel"
              ><b>전화번호</b><br />{{ attraction.tel }}<br /><br
            /></span>

            <span class="font"
              ><b>상세정보</b><br />
              {{ attraction.overview }}</span
            >
          </b-card-text>
          <!-- 좋아요 했을때만 업로드할수있게 나중에 v-if="checkLiked()" 넣을거임 -->
          <file-upload-item />
          <b-button
            pill
            class="float-right"
            variant="outline-success"
            @click="moveList()"
          >
            <b-icon
              icon="list"
              font-scale="1"
              style="margin-right: 5px"
            ></b-icon>
            <span class="font">목록</span>
          </b-button>
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
        </b-card>
      </b-col>
    </b-row>

    <b-row class="ml-1" style="margin-top: 50px">
      <h4><b-icon icon="list"></b-icon> <span class="font">댓글</span></h4>
      <comment-write :contentNo="this.attraction.contentNo"></comment-write>
      <comment-list :contentNo="this.attraction.contentNo"></comment-list>
    </b-row>
  </b-container>
</template>

<script>
import { getAttraction, likeAttraction } from "@/api/attraction";
import { mapState } from "vuex";

import CommentList from "./comment/CommentList.vue";
import CommentWrite from "./comment/CommentWrite.vue";
import FileUploadItem from "../common/FileUploadItem.vue";

const memberStore = "memberStore";
const attractionStore = "attractionStore";

export default {
  name: "AttractionDetail",
  components: {
    CommentList,
    CommentWrite,
    FileUploadItem,
  },
  data() {
    return {
      attraction: {},
      likedUsers: [],
    };
  },
  computed: {
    ...mapState(memberStore, ["userInfo"]),
    ...mapState(attractionStore, ["attrs"]),
  },
  created() {
    let param = this.$route.params.contentNo;
    getAttraction(
      param,
      ({ data }) => {
        console.log(data);
        this.attraction = data.attraction;
        this.likedUsers = data.likedUsers;
      },
      (error) => {
        console.log(error);
      }
    );
  },
  methods: {
    moveList() {
      this.$router.go(-1);
    },
    toggleLike(event) {
      event.preventDefault();
      //임시: 좋아요 등록/삭제 중 등록만 구현
      if (this.checkLiked()) alert("이미 좋아요를 누른 관광지입니다.");
      else {
        let param = {
          contentNo: this.attraction.contentNo,
          userNo: this.userInfo.userNo,
        };
        console.log(param.contentNo + " is liked by " + param.userNo);
        likeAttraction(
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
    contentTypeName() {
      switch (this.attraction.contentTypeId) {
        case 12:
          return "관광지";
        case 14:
          return "문화시설";
        case 15:
          return "축제공연행사";
        case 28:
          return "레포츠";
        case 32:
          return "숙박";
        case 38:
          return "쇼핑";
        case 39:
          return "음식점";
        default:
          return "";
      }
    },
  },
};
</script>

<style scoped>
@import url("https://fonts.googleapis.com/css2?family=Noto+Sans+KR&display=swap");

#container {
  width: 100%;
  display: flex;
  justify-content: center;
  align-content: center;
}

.bv-example-row.container {
  width: 57.8125vw;
}
.font {
  font-family: "Noto Sans KR", sans-serif;
}

.img {
  width: 60%;
}
.card {
  min-width: 40vw;
}
</style>
