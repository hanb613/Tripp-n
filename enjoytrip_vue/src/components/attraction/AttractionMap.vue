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
      positions: [],
      markers: [],
    };
  },
  props: {
    attrList: [],
  },
  watch: {
    attrList() {
      this.positions = [];
      this.attrList.forEach((attr) => {
        let obj = {};
        obj.title = attr.title;
        obj.latlng = new kakao.maps.LatLng(attr.latitude, attr.longitude);

        this.positions.push(obj);
      });
      this.loadMaker();
    },
  },
  created() {},
  mounted() {
    if (window.kakao && window.kakao.maps) {
      this.loadMap();
    } else {
      this.loadScript();
    }
  },

  methods: {
    // api 불러오기
    loadScript() {
      const script = document.createElement("script");
      script.src =
        "//dapi.kakao.com/v2/maps/sdk.js?appkey=" +
        process.env.VUE_APP_KAKAO_MAP_API_KEY +
        "&autoload=false";
      /* global kakao */
      script.onload = () => window.kakao.maps.load(this.loadMap);

      document.head.appendChild(script);
    },
    // 맵 출력하기
    loadMap() {
      const container = document.getElementById("map");
      const options = {
        center: new window.kakao.maps.LatLng(37.500613, 127.036431),
        level: 3,
      };

      this.map = new window.kakao.maps.Map(container, options);
      //   this.loadMaker();
    },
    // 지정한 위치에 마커 불러오기
    loadMaker() {
      // 현재 표시되어있는 marker들이 있다면 marker에 등록된 map을 없애준다.
      this.deleteMarker();
      // 마커 이미지를 생성
      //   const imgSrc = require("@/assets/map/markerStar.png");
      // 마커 이미지의 이미지 크기
      //   const imgSize = new kakao.maps.Size(24, 35);
      //   const markerImage = new kakao.maps.MarkerImage(imgSrc, imgSize);

      // 마커를 생성
      this.markers = [];
      this.positions.forEach((position) => {
        const marker = new kakao.maps.Marker({
          map: this.map, // 마커를 표시할 지도
          position: position.latlng, // 마커를 표시할 위치
          title: position.title, // 마커의 타이틀, 마커에 마우스를 올리면 타이틀이 표시
          //   image: markerImage, // 마커의 이미지
        });
        this.markers.push(marker);
      });

      // 지도를 이동시켜주기
      const bounds = this.positions.reduce(
        (bounds, position) => bounds.extend(position.latlng),
        new kakao.maps.LatLngBounds()
      );

      this.map.setBounds(bounds);
    },
    deleteMarker() {
      if (this.markers.length > 0) {
        this.markers.forEach((item) => {
          item.setMap(null);
        });
      }
    },
  },
};
</script>

<style scoped>
#map {
  width: 100%;
  height: 350px;
}
</style>
