import { Bar } from 'vue-chartjs'

export default {
    extends: Bar,
    props: ['data', 'options'],
    mounted() {
        console.log("drawing chart...");
        this.renderChart(this.data, this.options)
    }
}
