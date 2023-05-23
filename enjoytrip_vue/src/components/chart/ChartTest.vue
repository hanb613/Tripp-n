<template>
  <b-container class="bv-example-row mt-3">
    <b-card title="BEST 관광지" class="mb-2">
      <Bar :data="chartData" />
    </b-card>
  </b-container>
</template>

<script lang="ts">
import {
  Chart as ChartJS,
  Title,
  Tooltip,
  Legend,
  BarElement,
  CategoryScale,
  LinearScale,
} from "chart.js";
import { Bar } from "vue-chartjs";
import { getLikeCountBySido } from "@/api/attraction.js";

import { mapActions, mapMutations, mapState } from "vuex";
// const attractionStore = "attractionStore";
const chartStore = "chartStore";

ChartJS.register(
  CategoryScale,
  LinearScale,
  BarElement,
  Title,
  Tooltip,
  Legend
);

export default {
  name: "ChartTest",
  components: {
    Bar,
  },
  data() {
    return {
      loaded: false,
      chartData: {
        labels: [9, 8, 7, 6],
        datasets: [
          {
            label: "value",
            data: [10, 20, 40, 30],
            backgroundColor: "rgba(54, 162, 235, 0.2)",
            borderColor: "rgba(54, 162, 235, 1)",
            borderWidth: 1,
          },
        ],
      },
    };
  },
  computed: {
    ...mapState(chartStore, ["items"]),
  },
  created() {
    // this.chartData = this.fetchData();
    this.chartData = {
      labels: ["A", "S", "D", "F"],
      datasets: [
        {
          label: "value",
          data: [10, 20, 40, 30],
          backgroundColor: "rgba(54, 162, 235, 0.2)",
          borderColor: "rgba(54, 162, 235, 1)",
          borderWidth: 1,
        },
      ],
    };
    // this.CLEAR_ITEMS();
    console.log("cleared");
    this.getItems();
    this.chartData.labels = this.items.map((item) => item.name);
    this.chartData.datasets[0].data = this.items.map((item) => item.likeCnt);
  },
  mounted() {
    // this.chartData = {
    //   labels: ["Aa", "Ss", "Dd", "Ff"],
    //   datasets: [
    //     {
    //       label: "value",
    //       data: [10, 50, 40, 30],
    //       backgroundColor: "rgba(54, 162, 235, 0.2)",
    //       borderColor: "rgba(54, 162, 235, 1)",
    //       borderWidth: 4,
    //     },
    //   ],
    // };
    // this.getItems();
    // console.log("items: ");
    // console.log(this.items);
    // this.chartData.labels = this.items.map((item) => item.name);
    // this.chartData.datasets[0].data = this.items.map((item) => item.likeCnt);
    console.log(this.chartData.labels);
  },
  //   async mounted() {
  //     this.loaded = false;
  //     try {
  //       const cdata = await this.makeData();
  //       this.chartData = cdata;
  //       this.loaded = true;
  //       console.log("mounted: ");
  //       console.log(cdata);
  //     } catch (e) {
  //       console.error(e);
  //     }
  //   },
  methods: {
    ...mapActions(chartStore, ["getItems"]),
    ...mapMutations(chartStore, ["CLEAR_ITEMS"]),
    fetchData() {
      const param = {
        labels: [],
        datasets: [],
      };
      getLikeCountBySido(
        ({ data }) => {
          param.labels = data.map((item) => item.name);
          param.datasets = [
            {
              label: "Like Count",
              data: data.map((item) => item.likeCnt),
              backgroundColor: "rgba(54, 162, 235, 0.2)",
              borderColor: "rgba(54, 162, 235, 1)",
              borderWidth: 1,
            },
          ];
          console.log(param.labels);
        },
        (error) => {
          console.log(error);
        }
      );
      this.loaded = true;
      return param;
    },
  },
};
</script>
