<template>
    <div style="margin-top: 20px;display: flex;justify-content: center;">
        <span class="time" id="time" style="font-size: 1rem;margin-right: 0.5rem;">
            现在的时间是: <span class="date" style="margin-right: 5px;">{{nowTime}}</span>
            <span class="hour" >{{time.hour}}</span>
            <a class="split">:</a>
            <span class="minitus" >{{time.minitus}}</span>
            <a class="split">:</a>
            <span class="seconds" >{{time.seconds}}</span>
        </span>
    </div>
</template>

<script>
    export default {
        name:'DateUtils',
        props:["s"],
        data(){
            return {
                time: {
                    hour:"",
                    minitus:"",
                    seconds:""
                },
                nowTime: "",
                week: [
                    "星期天",
                    "星期一",
                    "星期二",
                    "星期三",
                    "星期四",
                    "星期五",
                    "星期六",
                ]
            }
        },
        mounted() {
            this.dateTime();
        },
        methods:{
            dateTime() {
                this.timeFormate();
                setTimeout(() => {
                    this.dateTime();
                },1000);
            },

            timeFormate() {
                const newtime = new Date();
                this.time.hour = this.getIncrease(newtime.getHours(),2);
                this.time.minitus = this.getIncrease(newtime.getMinutes(),2);
                this.time.seconds = this.getIncrease(newtime.getSeconds(),2);
                this.nowTime =
                    this.getIncrease(newtime.getFullYear(),4) +
                    "-" +
                    this.getIncrease(newtime.getMonth()+1,2) +
                    "-" +
                    this.getIncrease(newtime.getDate(),2) +
                    "  " +
                    this.week[newtime.getDay()];

            },

            getIncrease(num,digit) {
                var increase = "";
                for(var i = 0;i<digit;i++){
                    increase += "0";
                }
                return (increase+num).slice(-digit);
            }
        }
    }
</script>

<style scoped>
@keyframes shark{
    0%,
    100% {
        opacity: 1;
    }
    50% {
        opacity: 0;
    }
}

.split{
    animation: shark 1s step-end infinite;
    vertical-align:middle;
    margin-left: 2px;
    margin-right: 2px;
}


</style>