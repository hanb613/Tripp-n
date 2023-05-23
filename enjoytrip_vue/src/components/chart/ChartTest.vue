<template>
  <b-container class="bv-example-row mt-3">
    <b-card title="차트" class="mb-2">
      <Bar :data="data" :options="options" />
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
      data: {
        labels: ["January", "February", "March"],
        datasets: [{ data: [40, 20, 12] }],
      },
      options: {
        responsive: true,
      },
    };
  },
};
</script>