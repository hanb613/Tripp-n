<template>
  <div id="app">
    <main>
      <div class="search-box">
        <div class="title" style="font-family: 'Noto Sans KR', sans-serif">
          <b-icon icon="sun" font-scale="1" class="mr-2"></b-icon>
          날씨
        </div>
        <b-row class="mt-4 mb-4 text-center">
          <b-col class="sm-3">
            <b-form-select
              class="select"
              v-model="sidoNo"
              :options="sidos"
              @change="changeCity"
            ></b-form-select>
          </b-col>
        </b-row>
      </div>

      <div class="weather-wrap" v-if="typeof weather.main != 'undefined'">
        <div class="location-box">
          <div class="location">{{ this.sidoNo.sidoName }}</div>
          <div class="date">{{ dateBuilder() }}</div>
        </div>

        <div class="weather-box">
          <div class="temp2">{{ Math.round(weather.main.temp) }}°c</div>
          <div class="weather">{{ this.weather.description }}</div>
        </div>
      </div>
    </main>
  </div>
</template>

<script>
import { mapState, mapActions, mapMutations } from "vuex";
import weatherDescKo from "@/api/weather";

const attractionStore = "attractionStore";

export default {
  name: "WeatherView",
  data() {
    return {
      sidoNo: 0,
      weatherNo: 0,
      sido: "",
      api_key: process.env.VUE_APP_WEATHER_API_KEY,
      url_base: process.env.VUE_APP_WEATHER_URL,
      query: "",
      weather: {},
    };
  },
  computed: {
    ...mapState(attractionStore, ["sidos"]),
  },
  created() {
    this.CLEAR_SIDO_LIST();
    this.getSido();
  },
  methods: {
    ...mapActions(attractionStore, ["getSido"]),
    ...mapMutations(attractionStore, ["CLEAR_SIDO_LIST"]),

    changeCity() {
      if (this.sidoNo.sidoNo === 1) this.query = "seoul";
      else if (this.sidoNo.sidoNo === 2) this.query = "incheon";
      else if (this.sidoNo.sidoNo === 3) this.query = "daejeon";
      else if (this.sidoNo.sidoNo === 4) this.query = "daegu";
      else if (this.sidoNo.sidoNo === 5) this.query = "gwangju";
      else if (this.sidoNo.sidoNo === 6) this.query = "busan";
      else if (this.sidoNo.sidoNo === 7) this.query = "ulsan";
      else if (this.sidoNo.sidoNo === 8) this.query = "sejong";
      else if (this.sidoNo.sidoNo === 31) this.query = "seongnam";
      else if (this.sidoNo.sidoNo === 32) this.query = "sokcho";
      else if (this.sidoNo.sidoNo === 33) this.query = "cheongju";
      else if (this.sidoNo.sidoNo === 34) this.query = "cheonan";
      else if (this.sidoNo.sidoNo === 35) this.query = "gumi";
      else if (this.sidoNo.sidoNo === 36) this.query = "kimhae";
      else if (this.sidoNo.sidoNo === 37) this.query = "jeonju";
      else if (this.sidoNo.sidoNo === 38) this.query = "suncheon";
      else if (this.sidoNo.sidoNo === 39) this.query = "jeju";

      this.fetchWeather();
    },
    fetchWeather() {
      fetch(
        `${this.url_base}weather?q=${this.query}&units=metric&APPID=${this.api_key}`
      )
        .then((res) => {
          return res.json();
        })
        .then(this.setResults);
    },
    setResults(results) {
      this.weather = results;
      this.weatherNo = this.weather.weather[0].id;

      const weather = this.findWeather(this.weatherNo);
      if (weather) {
        this.weather.description = weather[this.weatherNo].description;
      }
    },
    findWeather(code) {
      return weatherDescKo.find((item) => item[code]);
    },
    dateBuilder() {
      let d = new Date();
      let months = [
        "1",
        "2",
        "3",
        "4",
        "5",
        "6",
        "7",
        "8",
        "9",
        "10",
        "11",
        "12",
      ];
      let days = ["일", "월", "화", "수", "목", "금", "토"];
      let day = days[d.getDay()];
      let date = d.getDate();
      let month = months[d.getMonth()];
      let year = d.getFullYear();
      return `${year}.${month}.${date} (${day})`;
    },
  },
};
</script>

<style scoped>
@import url("https://fonts.googleapis.com/css2?family=Noto+Sans+KR&display=swap");

* {
  margin: 0;
  padding: 0;
  box-sizing: border-box;
  font-family: "Noto Sans KR", sans-serif;
}

#app {
  /* width:200px; */
  width: 10.4167vw;
  transition: 0.4s;
  border-radius: 5%;
}
main {
  /* min-height: 300px; */
  min-height: 15.625vw;
  padding: 25px;
  border-radius: 5%;
}
.search-box {
  width: 100%;
  display: flex;
  flex-direction: column;
  justify-content: center;
  align-content: center;
  text-align: center;
}
.search-box .search-bar {
  display: block;
  width: 100%;
  color: #313131;
  font-size: 20px;
  appearance: none;
  border: none;
  outline: none;
  background: none;
  box-shadow: 0px 0px 8px rgba(0, 0, 0, 0.05);
  background-color: rgba(255, 255, 255, 0.5);
  border-radius: 0px 16px 0px 16px;
  transition: 0.4s;
}
.search-box .search-bar:focus {
  box-shadow: 0px 0px 16px rgba(0, 0, 0, 0.25);
  background-color: rgba(255, 255, 255, 0.75);
  border-radius: 16px 0px 16px 0px;
}
.location-box .location {
  color: black;
  font-size: 22px;
  font-weight: 500;
  text-align: center;
}
.location-box .date {
  color: black;
  font-size: 15px;
  font-weight: 200;
  font-style: italic;
  text-align: center;
}
.weather-box {
  text-align: center;
}
.weather-box .temp2 {
  display: inline-block;
  padding: 10px 5px;
  color: black;
  font-size: 40px;
  font-weight: 700;
  text-shadow: 3px 2px rgba(0, 0, 0, 0.25);
  background-color: rgba(255, 255, 255, 0.25);
  border-radius: 16px;
  margin: 10px 0px;
}
.weather-box .weather {
  color: black;
  font-size: 20px;
  font-weight: 600;
  font-style: italic;
}

.title {
  font-size: 25px;
  font-weight: 500;
}
</style>
