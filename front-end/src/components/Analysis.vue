<template>
    <div class="analysic" >
        <div>
            <h3 class="title" >Траты</h3>
        </div>
        <div>
            <div class="line-h" ></div>
            <div class="line line1" >Одежда</div>
            <div class="line line2" >Продукты</div>
            <div class="line line3" >Мебель</div>
            <div class="line line4" >Штрафы</div>
            <div class="line line5" >Тобак</div>
            <div class="line line6" >фастфуд</div>
            <div class="line line7" >метро</div>
        </div>
    </div>
</template>


<script>
export default {
    
}
</script>


<style scoped>
.analysic {
    padding-left: 20px;
    padding-right: 20px;
}
.title {
    width: 100%;
    text-align: start;
}
.line {
    width: 90%;
    height: 19px;
    margin-top: 14px;
    border-radius: 4px;
    font-size: 12px;
    text-align: center;
    line-height: 20px;
}
.line1 {
    background:#DE88D0;
}
.line2 {
    background:#9E96EF;
    width: 80%;
}
.line3 {
    background:#5DE06A;
    width: 70%;
}
.line4 {
    background:#E8A27C;
    width: 64%;
}
.line5 {
    background:#96DAEF;
    width: 43%;
}
.line6 {
    background:#EF96A6;
    width: 31%;
}
.line7 {
    background:#A3EF96;
    width: 26%;
}
.line-h {
    position: absolute;
    background: #000;
    height: 42%;
    margin-top: -10px;
    width: 3px;
}
</style>