<template>
  <b-container class="bv-example-row mt-3">
    <b-row class="mb-1">
      <b-col id="container">
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
              ><b>우편번호</b><br />
              {{ attraction.zipcode }}</span
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
          <b-button pill class="me-2 float-right" variant="primary">
            <b-icon
              icon="suit-heart"
              font-scale="1"
              style="margin-right: 5px"
            ></b-icon>
            <span class="font">좋아요</span>
          </b-button>
        </b-card>
      </b-col>
    </b-row>
  </b-container>
</template>

<script>
import { getAttraction } from "@/api/attraction";
export default {
  name: "AttractionDetail",
  components: {},
  data() {
    return {
      attraction: {},
    };
  },
  created() {
    let param = this.$route.params.contentNo;
    getAttraction(
      param,
      ({ data }) => {
        console.log(data);
        this.attraction = data;
      },
      (error) => {
        console.log(error);
      }
    );
  },
  methods: {
    moveList() {
      this.$router.push({ name: "AttractionSearch" });
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

.font {
  font-family: "Noto Sans KR", sans-serif;
}

.img {
  width: 60%;
}
</style>
