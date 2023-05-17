<template>
  <div class="container">
    <section id="search" class="search">
      <!-- 관광지 조회 타이틀 -->
      <div class="row d-flex justify-content-center">
        <div class="col-lg-6 text-center">
          <h2 class="fw-bold">관광지 조회</h2>
        </div>
      </div>
      <!-- 타이틀 종료 -->

      <div class="col-lg-10">
        <div class="col-lg-10 text-center">
          <form
            id="form-arrtaction"
            class="d-flex my-3"
            onsubmit="return false;"
            role="search"
          >
            <select
              id="search-area"
              class="form-select me-2"
              v-model="selectedSido"
            >
              <option
                v-for="item in sidoList"
                :key="item.sidoNo"
                :value="item.sidoNo"
              >
                {{ item.sidoName }}
              </option>
            </select>
            <select
              id="search-gugun-id"
              class="form-select me-2"
              v-model="selectedGugun"
              ref="searchGugunId"
            >
              <option value="0" selected>시/군/구</option>
              <option
                v-for="area in gugunList"
                :key="area.gugunNo"
                :value="area.gugunNo"
              >
                {{ area.gugunName }}
              </option>
            </select>
            <select id="search-content-id" class="form-select me-2">
              <option value="0" selected>관광지 유형</option>
              <option value="12">관광지</option>
              <option value="14">문화시설</option>
              <option value="15">축제공연행사</option>
              <option value="25">여행코스</option>
              <option value="28">레포츠</option>
              <option value="32">숙박</option>
              <option value="38">쇼핑</option>
              <option value="39">음식점</option>
            </select>
            <button
              id="btn-search"
              class="btn btn-outline-success"
              type="submit"
              onclick="btnClick()"
            >
              검색
            </button>
          </form>
        </div>
      </div>

      <!-- kakao map start -->
      <div id="map" class="mt-3" style="width: 100%; height: 400px"></div>
      <!-- kakao map end -->

      <!-- 검색 결과 조회 -->
      <div class="row" id="search-result">
        <section id="blog" class="blog">
          <div class="container" data-aos="fade-up"></div>
        </section>
      </div>
      <!-- End 검색 결과 조회  -->
    </section>
    <!-- End 관광지 조회 섹션 -->
  </div>
  <!-- 메인 컨텐츠 -->
</template>
<!-- End #main -->

<script
  type="text/javascript"
  src="//dapi.kakao.com/v2/maps/sdk.js?appkey=c5aea05dba04c8446448c63fff995b63"
></script>
<script src="../../assets/js/map.js"></script>

<script>
export default {
  name: "AttractionList",
  data() {
    return {
      selectedSido: "",
      selectedGugun: "",
      sidoList: [],
      gugunList: [],
      positions: [],
      mapOption: {
        center: new kakao.maps.LatLng(37.500613, 127.036431),
        level: 5,
      },
      map: null, // 카카오 지도 객체
    };
  },
  watch: {
    selectedSido(newVal) {
      this.changeGugun(newVal);
    },
  },
  mounted() {
    // fetch and set initial sidoList data here

    const serviceKey =
      "hFhQlPE6A5Tzme9oIWJlhgxbn3lEgj3R0hvcCM62zK4Kotk2BG9B1TRphLQfQWSKUIldSrVkCd%2FWxr0BWyqoJw%3D%3D";

    //index page 로딩 후 전국의 시도 설정.
    let areaUrl =
      "https://apis.data.go.kr/B551011/KorService1/areaCode1?serviceKey=" +
      serviceKey +
      "&numOfRows=20&pageNo=1&MobileOS=ETC&MobileApp=AppTest&_type=json";

    console.log(areaUrl);
    fetch(areaUrl, { method: "GET" })
      .then((response) => response.json())
      .then((data) => this.makeOption(data));

    this.map = new kakao.maps.Map(
      document.getElementById("map"),
      this.mapOption
    );
    this.displayMarker();
  },
  methods: {
    makeOption(response) {
      let sel = this.$refs.searchGugunId;
      sel.innerHTML = '<option value="0" selected>시/군/구</option>';

      response.forEach((area) => {
        var opt = document.createElement("option");
        opt.setAttribute("value", area.gugunNo);
        opt.appendChild(document.createTextNode(area.gugunName));
        sel.appendChild(opt);
      });
    },
    changeGugun(sidoCode) {
      console.log(sidoCode);
      fetch("/attraction/getGugun/" + sidoCode, { method: "GET" })
        .then((response) => response.json())
        .then((data) => this.makeOption(data));
    },
    btnClick() {
      let sidoNo = this.selectedSido;
      let gugunNo = this.selectedGugun;
      let contentTypeId = document.querySelector("#search-content-id").value;
      if (sidoNo == 0 || gugunNo == 0) {
        alert("Select Options !!!");
        return;
      } else {
        fetch("/attraction/search", {
          method: "GET",
          headers: {
            "Content-Type": "application/json",
            sidoNo: sidoNo,
            gugunNo: gugunNo,
            contentTypeId: contentTypeId,
          },
        })
          .then((response) => response.json())
          .then((data) => this.makeList(data));
      }
    },
    makeList(response) {
      if (response.length === 0) {
        alert("Select Other Options !!!");
        return;
      } else {
        document
          .querySelector("#search-result")
          .setAttribute("style", "display: ;");

        let tripList = `<div class="row gy-4 posts-list">`;
        this.positions = [];
        response.forEach((area) => {
          if (!area.firstImage) {
            // 기본 이미지
            tripList += '<div class="col-xl-4 col-md-6">';
            tripList +=
              '<article> <div class="post-img"> <img src="./assets/img/noimg.jpg" alt="" class="img-fluid"/> </div>';
            tripList += '<h2 class="title text-dark">' + area.title + " </h2>";
            tripList +=
              '<div class="d-flex align-items-center"> <div class="post-meta"> <p class="post-author-list">' +
              area.addr1 +
              " ";
            if (area.addr2) tripList += "- " + area.addr2;
            tripList +=
              '</p><p class="post-author-list">' +
              area.zipcode +
              "</p></div> </div> </article> </div>";
          } else {
            // db에 저장된 이미지
            tripList += '<div class="col-xl-4 col-md-6">';
            tripList +=
              '<article> <div class="post-img"> <img src=' +
              area.firstImage +
              ' alt="" class="img-fluid" /> </div>';
            tripList += '<h2 class="title text-dark">' + area.title + "</h2>";
            tripList +=
              '<div class="d-flex align-items-center"> <div class="post-meta"> <p class="post-author-list">' +
              area.addr1 +
              " ";
            if (area.addr2) tripList += "- " + area.addr2;
            tripList +=
              '</p><p class="post-author-list">' +
              area.zipcode +
              "</p></div> </div> </article> </div>";
          }

          let markerInfo = {
            title: area.title,
            latlng: new kakao.maps.LatLng(area.latitude, area.longitude),
          };

          this.positions.push(markerInfo);
        });
      }
    },
  },
};
</script>

<style scoped>
/* Add CSS styles here */
</style>
