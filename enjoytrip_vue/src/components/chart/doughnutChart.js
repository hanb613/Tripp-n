import { Doughnut } from 'vue-chartjs';
export default {
    mixins: [Doughnut],
    name: "DoughnutChart",
    props: ["chartData", "options"],
    mounted() {
        console.log("drawing chart");
        this.renderChart(this.chartData, this.options);
    }
};