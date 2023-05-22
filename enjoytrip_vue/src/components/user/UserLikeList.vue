<template>
  <b-container class="bv-example-row mt-3">
    <h3><b-icon icon="bookmark-star"></b-icon> 관심 목록</h3>
    <br />
    <b-alert show variant="primary" v-if="this.attrs.length === 0"
      >관심 목록이 없습니다.</b-alert
    >

    <b-row class="mb-1" v-if="this.attrs.length !== 0" id="container">
      <b-col
        v-for="attr in attrs"
        :key="attr.contentNo"
        cols="3"
        @mouseover="colorChange(attr.contentNo, true)"
        @mouseout="colorChange(attr.contentNo, false)"
        :class="{ 'mouse-over-bgcolor': isColor[attr.contentNo] }"
      >
        <div>
          <router-link
            :to="{
              name: 'AttractionDetail',
              params: { contentNo: attr.contentNo },
            }"
          >
            <article>
              <div class="post-img">
                <img
                  src="@/assets/img/noimg.jpg"
                  alt="noimg"
                  class="img-fluid"
                  v-if="!attr.firstImage"
                />
                <img
                  :src="attr.firstImage"
                  alt="사진"
                  class="img-fluid"
                  v-else
                />
                <h4
                  class="title text-dark"
                  style="font-family: 'Noto Sans KR', sans-serif"
                >
                  {{ attr.title }}
                </h4>
                <div class="d-flex align-items-center">
                  <div class="post-meta">
                    <p class="post-author-list">[주소]<br />{{ attr.addr1 }}</p>
                    <p class="post-author-list">
                      [우편번호]<br />{{ attr.zipcode }}
                    </p>
                  </div>
                </div>
              </div>
            </article>
          </router-link>
        </div>
      </b-col>
    </b-row>
  </b-container>
</template>

<script>
import { mapState } from "vuex";
import { likeList } from "@/api/member";
const memberStore = "memberStore";

export default {
  name: "UserLikeList",
  data() {
    return {
      isColor: {},
      attrs: [],
    };
  },
  computed: {
    ...mapState(memberStore, ["userInfo"]),
  },
  created() {
    let param = this.userInfo.userNo;
    likeList(
      param,
      ({ data }) => {
        this.attrs = data.likeInfo;
      },
      (error) => {
        console.log(error);
      }
    );
  },
  methods: {
    selectAttr() {
      this.detailAttr(this.attr);
    },
    colorChange(contentNo, flag) {
      this.$set(this.isColor, contentNo, flag);
    },
  },
};
</script>

<style scoped>
@import url("https://fonts.googleapis.com/css2?family=Noto+Sans+KR&display=swap");

.mouse-over-bgcolor {
  background-color: lightblue;
}
a {
  text-decoration-line: none;
}
.articleItem {
  margin: 30px 5px 10px 5px;
}
.img-fluid {
  margin-top: 10px;
  margin-bottom: 20px;
}

#container {
  font-family: "Noto Sans KR", sans-serif;
}

.post-meta {
  margin-top: 10px;
}
</style>
