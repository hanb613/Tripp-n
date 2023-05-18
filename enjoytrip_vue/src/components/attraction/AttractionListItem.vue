<template>
  <!-- <b-row
      class="m-2"
      @click="selectHouse"
      @mouseover="colorChange(true)"
      @mouseout="colorChange(false)"
      :class="{ 'mouse-over-bgcolor': isColor }"
    >
      <b-col cols="2" class="text-center align-self-center">
        <b-img thumbnail src="https://picsum.photos/250/250/?image=58" alt="Image 1"></b-img>
      </b-col>
      <b-col cols="10" class="align-self-center"> [{{ attr.일련번호 }}] {{ attr.아파트 }} </b-col>
    </b-row> -->
  <b-container
    class="bv-example-row mt-3"
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
      <div class="col-xl-4 col-md-6">
        <article>
          <div class="post-img">
            <img
              src="@/assets/img/noimg.jpg"
              alt=""
              class="img-fluid"
              v-if="!attr.firstImage"
            />
            <img :src="attr.firstImage" alt="" class="img-fluid" v-else />
            <h2 class="title text-dark">{{ attr.title }}</h2>
            <div class="d-flex align-items-center">
              <div class="post-meta">
                <p class="post-author-list">{{ attr.addr1 }}</p>
                <p class="post-author-list"></p>
              </div>
            </div>
          </div>
        </article>
      </div>
      <!-- <div class="col-xl-4 col-md-6" v-else>
        <div class="post-img">
          <img :src="attr.firstImage" alt="" class="img-fluid" />
          <h2 class="title text-dark">{{ attr.title }}</h2>
          <div class="d-flex align-items-center">
            <div class="post-meta">
              <p class="post-author-list">{{ attr.addr1 }}</p>
              <p class="post-author-list"></p>
            </div>
          </div>
        </div>
      </div> -->
    </router-link>
  </b-container>
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
  