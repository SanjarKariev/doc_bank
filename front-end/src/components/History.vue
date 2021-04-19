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
        /* margin-left: 5%; */
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
</style>