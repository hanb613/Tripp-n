<template>
  <b-col
    cols="3"
    @mouseover="colorChange(true)"
    @mouseout="colorChange(false)"
    :class="{ 'mouse-over-bgcolor': isColor }"
    id="container"
  >
    <router-link
      :to="{
        name: 'AttractionDetail',
        params: { contentNo: attr.contentNo },
      }"
    >
      <div>
        <article class="articleItem">
          <div class="post-img">
            <img
              src="@/assets/img/noimg.jpg"
              alt="noimg"
              class="img-fluid"
              v-if="!attr.firstImage"
            />
            <img :src="attr.firstImage" alt="사진" class="img-fluid" v-else />
            <h4 class="title text-dark" style="font-family: 'Noto Sans KR', sans-serif;">{{ attr.title }}</h4>
            <div class="d-flex align-items-center">
              <div class="post-meta">
                <p class="post-author-list">[주소]<br>{{ attr.addr1 }}</p>
                <p class="post-author-list">[우편번호]<br>{{ attr.zipcode }}</p>
              </div>
            </div>
          </div>
        </article>
      </div>
    </router-link>
  </b-col>
</template>

<script>
import { mapActions } from "vuex";

const attractionStore = "attractionStore";

export default {
  name: "AttractionListItem",
  data() {
    return {
      isColor: false,
    };
  },
  props: {
    attr: Object,
  },
  methods: {
    ...mapActions(attractionStore, ["detailAttr"]),
    selectAttr() {
      this.detailAttr(this.attr);
    },
    colorChange(flag) {
      this.isColor = flag;
    },
  },
};
</script>

<style scoped>
@import url('https://fonts.googleapis.com/css2?family=Noto+Sans+KR&display=swap');

.mouse-over-bgcolor {
  background-color: lightblue;
}
a {
  text-decoration-line: none;
}
.articleItem{
  margin:30px 5px 10px 5px;
}
.img-fluid{
  margin-bottom: 20px;
}

#container{
  font-family: 'Noto Sans KR', sans-serif;
}

.post-meta{
  margin-top:10px;
}
</style>
