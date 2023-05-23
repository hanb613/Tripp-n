<template>
<div id="app" v-bind:style="{ backgroundImage: 'url(' + weather.img + ')' }">

      <main>
        <div class="search-box">
            <b-row class="mt-4 mb-4 text-center">
                <b-col class="sm-3">
                <b-form-select
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
            <div class="temp">{{ Math.round(weather.main.temp) }}°c</div>
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
    name: 'WeatherView',
    data () {
        return {
            sidoNo: 0,
            weatherNo: 0,
            sido:'',
            api_key: process.env.VUE_APP_WEATHER_API_KEY,
            url_base: process.env.VUE_APP_WEATHER_URL,
            query: '',
            weather: {}
        }
    },
    computed: {
        ...mapState(attractionStore, ["sidos"]),
    },
    created() {
          this.weather.img = '/weather/main.jpg';
        this.CLEAR_SIDO_LIST();
        this.getSido();
    },
    methods: {
        ...mapActions(attractionStore, ["getSido"]),
        ...mapMutations(attractionStore, ["CLEAR_SIDO_LIST"]),

        changeCity() {
            
            if (this.sidoNo.sidoNo === 1) this.query = 'seoul';
            else if(this.sidoNo.sidoNo===2) this.query = 'incheon'; 
            else if(this.sidoNo.sidoNo===3) this.query = 'daejeon'; 
            else if(this.sidoNo.sidoNo===4) this.query = 'daegu'; 
            else if(this.sidoNo.sidoNo===5) this.query = 'gwangju'; 
            else if(this.sidoNo.sidoNo===6) this.query = 'busan'; 
            else if(this.sidoNo.sidoNo===7) this.query = 'ulsan'; 
            else if(this.sidoNo.sidoNo===8) this.query = 'sejong'; 
            else if(this.sidoNo.sidoNo===31) this.query = 'seongnam'; 
            else if(this.sidoNo.sidoNo===32) this.query = 'sokcho'; 
            else if(this.sidoNo.sidoNo===33) this.query = 'cheongju'; 
            else if(this.sidoNo.sidoNo===34) this.query = 'cheonan'; 
            else if(this.sidoNo.sidoNo===35) this.query = 'gumi'; 
            else if(this.sidoNo.sidoNo===36) this.query = 'kimhae'; 
            else if(this.sidoNo.sidoNo===37) this.query = 'jeonju'; 
            else if(this.sidoNo.sidoNo===38) this.query = 'suncheon'; 
            else if(this.sidoNo.sidoNo===39) this.query = 'jeju'; 

            this.fetchWeather()

        },
        fetchWeather () {
            fetch(`${this.url_base}weather?q=${this.query}&units=metric&APPID=${this.api_key}`)
                .then(res => { return res.json(); })
                .then(this.setResults);
        },
        setResults (results) {
            this.weather = results;
            this.weatherNo = this.weather.weather[0].id;
            
            const weather = this.findWeather(this.weatherNo);
            if (weather) {
                this.weather.description = weather[this.weatherNo].description;
                this.weather.img = weather[this.weatherNo].img;
            }
        },
        findWeather (code) {
            return weatherDescKo.find(item => item[code]);
        },
        dateBuilder () {
            let d = new Date();
            let months = ["1", "2", "3", "4", "5", "6", "7", "8", "9", "10", "11", "12"];
            let days = ["일요일", "월요일", "화요일", "수요일", "목요일", "금요일", "토요일"];
            let day = days[d.getDay()];
            let date = d.getDate();
            let month = months[d.getMonth()];
            let year = d.getFullYear();
            return `${year}년 ${month}월 ${date}일 ${day}`;
        }
    }
  }
  </script>
  
  <style scoped>
  * {

    margin: 0;
    padding: 0;
    box-sizing: border-box;
  }
  body {
    font-family: 'montserrat', sans-serif;
  }

  #app {
    width:30vw;
    margin-top:50px;
    background-size: cover;
    background-position: bottom;
    transition: 0.4s;
  }
  main {

    min-height: 70vh;
    padding: 25px;
    background-image: linear-gradient(to bottom, rgba(0, 0, 0, 0.25), rgba(0, 0, 0, 0.75));
  }
  .search-box {
    width: 100%;
    margin-bottom: 30px;
  }
  .search-box .search-bar {
    display: block;
    width: 100%;
    padding: 15px;
    
    color: #313131;
    font-size: 20px;
    appearance: none;
    border:none;
    outline: none;
    background: none;
    box-shadow: 0px 0px 8px rgba(0, 0, 0, 0.25);
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
    color: #FFF;
    font-size: 32px;
    font-weight: 500;
    text-align: center;
    text-shadow: 1px 3px rgba(0, 0, 0, 0.25);
  }
  .location-box .date {
    color: #FFF;
    font-size: 20px;
    font-weight: 300;
    font-style: italic;
    text-align: center;
  }
  .weather-box {
    text-align: center;
  }
  .weather-box .temp {
    display: inline-block;
    padding: 10px 25px;
    color: #FFF;
    font-size: 102px;
    font-weight: 900;
    text-shadow: 3px 6px rgba(0, 0, 0, 0.25);
    background-color:rgba(255, 255, 255, 0.25);
    border-radius: 16px;
    margin: 30px 0px;
    box-shadow: 3px 6px rgba(0, 0, 0, 0.25);
  }
  .weather-box .weather {
    color: #FFF;
    font-size: 48px;
    font-weight: 700;
    font-style: italic;
    text-shadow: 3px 6px rgba(0, 0, 0, 0.25);
  }
  </style>