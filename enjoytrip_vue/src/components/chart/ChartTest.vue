<template>

  <b-container class="bv-example-row mt-3 ">
    <b-card class="mb-2">
      <b-card-title class="title">BEST 여행 지역</b-card-title>
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
    this.getItems();
    this.chartData.labels = this.items.map((item) => item.name);
    this.chartData.datasets[0].data = this.items.map((item) => item.likeCnt);
  },
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

<style scoped>
.title{
  font-size:20px;
}
</style>