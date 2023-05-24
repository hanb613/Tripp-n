<template>
  <b-container class="bv-example-row mt-3 ml-1">
    <h3 style="font-family: 'Noto Sans KR', sans-serif">
      <b-icon icon="bookmark-star"></b-icon> 관심 목록
    </h3>
    <br />
    <b-alert show variant="primary" v-if="this.attrs.length === 0"
      >관심 목록이 없습니다.</b-alert
    >
    <!-- Attraction List -->
    <b-container
      v-if="attrs && attrs.length != 0"
      class="bv-example-row-flex-cols"
    >
      <b-row offset="1" class="itemrow">
        <attraction-list-item
          v-for="(attr, index) in attrs"
          :key="index"
          :attr="attr"
        />
      </b-row>
    </b-container>
  </b-container>
</template>

<script>
import { mapState } from "vuex";
import { likeList } from "@/api/member";
import AttractionListItem from "@/components/attraction/AttractionListItem.vue";
const memberStore = "memberStore";

export default {
  name: "UserLikeList",
  data() {
    return {
      isColor: {},
      attrs: [],
    };
  },
  components: {
    AttractionListItem,
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
.bv-example-row.container {
  width: 57.8125vw;
}

.post-meta {
  margin-top: 10px;
}
</style>
