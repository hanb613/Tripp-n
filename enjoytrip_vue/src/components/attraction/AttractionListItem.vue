<template>
  <b-col
    cols="3"
    @mouseover="colorChange(true)"
    @mouseout="colorChange(false)"
    :class="{ 'mouse-over-bgcolor': isColor }"
  >
    <router-link
      :to="{
        name: 'AttractionDetail',
        params: { contentNo: attr.contentNo },
      }"
    >
      <div>
        <article>
          <div class="post-img">
            <img
              src="@/assets/img/noimg.jpg"
              alt="noimg"
              class="img-fluid"
              v-if="!attr.firstImage"
            />
            <img :src="attr.firstImage" alt="사진" class="img-fluid" v-else />
            <h4 class="title text-dark">{{ attr.title }}</h4>
            <div class="d-flex align-items-center">
              <div class="post-meta">
                <p class="post-author-list">{{ attr.addr1 }}</p>
                <p class="post-author-list"></p>
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
.mouse-over-bgcolor {
  background-color: lightblue;
}
a {
  text-decoration-line: none;
}
</style>
