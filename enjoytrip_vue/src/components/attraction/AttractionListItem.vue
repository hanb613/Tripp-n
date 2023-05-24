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
      <article class="articleItem">
        <div>
          <b-card
            :title="attr.title"
            :img-src="imgSrc"
            img-alt="Image"
            img-top
            tag="article"
            style="max-width: 20rem"
            class="mb-2"
            text-variant="dark"
          >
            <b-card-text>
              {{ attr.addr1 }}
            </b-card-text>
          </b-card>
        </div>
      </article>
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
  computed: {
    imgSrc() {
      return this.attr.firstImage || "/noimg.png";
    },
  },
  methods: {
    ...mapActions(attractionStore, ["attrs", "detailAttr"]),
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
@import url("https://fonts.googleapis.com/css2?family=Noto+Sans+KR&display=swap");

.mouse-over-bgcolor {
  background-color: cadetblue;
}
a {
  text-decoration-line: none;
}

.articleItem {
  margin: 10px 1% 1% 1%;
}

.card-img-top {
  max-height: 50%;
}

#container {
  font-family: "Noto Sans KR", sans-serif;
  margin-top: 10px;
}
</style>
