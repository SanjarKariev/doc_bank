<template>
    <div class="transition" >
        <div class="card">
                <img src="../assets/Карта.png">
                <div class="titleCardAndBalance">
                    <p>Visa Classic</p>
                    <p class="balance_text">1500P</p>
                </div>
                <div>
                    .... 5432
                </div>
            </div>
        <!-- <div class="card-container" > -->
            <input class="inp" type="text" placeholder="Номер карты" >
            <input class="inp" type="text" placeholder="Сумма" >
            <button class="btn" >Перевести</button>
        <!-- </div> -->
    </div>
</template>


<script>
export default {
    
}
</script>


<style scoped>
    .card {
        display: flex;
        justify-content: space-between;
        align-items: center;
        padding-left: 5%;
        padding-right: 5%;
        margin-top: 10px;
        background: #F2994A;
        border-radius: 6px;
        width: 90%;
        height: 60px;
    }
    .titleCardAndBalance {
        display: flex;
        flex-direction: column;
        align-items: flex-start;
    }
    .titleCardAndBalance p {
        margin: 0;
        font-size: 12px;
    }
    .transition {
        display: flex;
        flex-direction: column;
        align-items: center;
        justify-content: center;
    }
    .inp {
        background: inherit;
        border: 1px solid #2B373F;
        margin-top: 20px;
        width: 85%;
        height: 35px;
        padding: 10px;
        border-radius: 10px;
        font-size: 14px;
        text-align: center;
    }
    .btn {
        background: #233D4D;
        border: none;
        margin-top: 40px;
        width: 85%;
        height: 35px;
        padding: 10px;
        border-radius: 10px;
        font-size: 14px;
        text-align: center;
        color: #F2994A;
        line-height: 0px;
    }
</style>