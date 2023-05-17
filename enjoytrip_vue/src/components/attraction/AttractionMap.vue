<template>
  <div>
    <div id="map"></div>
  </div>
</template>

<script>
export default {
  name: "AttractionMap",
  components: {},
  data() {
    return {
      map: null,
    };
  },
  created() {},
  methods: {
    initMap() {
      const container = document.getElementById("map");
      const options = {
        center: new kakao.maps.LatLng(37.500613, 127.036431),
        level: 5,
      };
      this.map = new kakao.maps.Map(container, options);
    },
  },
  mounted() {
    if (!window.kakao || !window.kakao.maps) {
      const script = document.createElement("script");
      script.src =
        "//dapi.kakao.com/v2/maps/sdk.js?appkey=c5aea05dba04c8446448c63fff995b63&autoload=false";
      /* global kakao */
      script.addEventListener("load", () => {
        kakao.maps.load(this.initMap);
      });
      document.head.appendChild(script);
    } else {
      //console.log("이미 로딩됨: ", window.kakao);
      this.initMap();
    }
  },
};
</script>

<style scoped>
#map {
  width: 205%;
  height: 350px;
}
</style>
