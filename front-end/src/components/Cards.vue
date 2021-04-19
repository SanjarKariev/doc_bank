<template>
    <div>
        <span class="title"><p>Карты</p> <p class="createCardBtn" @click='createCard' >+</p></span>
        <div v-if="!isCards" class="cards">
            <div class="card">
                <img src="../assets/Карта.png">
                <div class="titleCardAndBalance">
                    <p>Visa Classic</p>
                    <p class="balance_text">1500P</p>
                </div>
                <div>
                    .... 5432
                </div>
                <button class="analis_btn">Анализ</button>
            </div>
        </div>
        <div v-else class="cards" >
            <div style="margin-top: 30px;width: 90%; margin-left: 5%;" >Cards not found</div>
        </div>
        <span class="title">Курс</span>
        <span class="courses">
            <p>Название</p>
            <p>Продажа</p>
            <p>Покупка</p>
        </span>
        <span class="courses_p">
            <p class="name_p">Доллар USA</p>
            <p>90</p>
            <p class="p_p">89</p>
        </span>
        <span class="courses_p">
            <p class="name_p">Евро EUR</p>
            <p>90</p>
            <p class="p_p">89</p>
        </span>
        <span class="courses_p">
            <p class="name_p">Юань</p>
            <p>12</p>
            <p class="p_p">11</p>
        </span>
    </div>
</template>

<script>
    // import axios from 'axios'

    export default {
        name: "cards",
        mounted() {
            this.getCards()
        },
        data() {
            return {
                isCards: false
            }
        },
        methods: {
            createCard() {
                fetch(`http://localhost:8090/api/card/:currency?name=sunt elit dolore`, {
                            headers: {
                                "Accept": "application/json",
                                "Content-Type": "application/json",
                                "Authorization": `Bearer ${localStorage.token}`
                            },
                            method: "POST"
                        })
                        .then((res) => {
                            res.text().then((res) => {
                                console.log(JSON.parse(res));
                                if (JSON.parse(res).message == 'cards_not_found') {
                                    // console.log('!!!');
                                    // document.querySelector('.cards').innerHTML = '<div style="margin-top: 30px;width: 90%; margin-left: 5%; " class="cardsNotFound" > </div>'
                                }
                            })
                        })
            },
            getCards() {
                fetch(`http://localhost:8090/api/card`, {
                            headers: {
                                "Accept": "application/json",
                                "Content-Type": "application/json",
                                "Authorization": `Bearer ${localStorage.token}`
                            },
                            method: "GET"
                        })
                        .then((res) => {
                            res.text().then((res) => {
                                console.log(res);
                                if (JSON.parse(res).message == 'cards_not_found') {
                                    // console.log('!!!');
                                    // document.querySelector('.cards').innerHTML = '<div style="margin-top: 30px;width: 90%; margin-left: 5%; " class="cardsNotFound" > </div>'
                                }
                            })
                        })
            }
        }
    }
</script>

<style scoped>
    .title p {
        margin: 0;
    }
    .title {
        width: 95%;
        margin-left: 2.5%;
        padding-left: 10px;
        padding-right: 10px;
        display: flex;
        background: #13212A;
        margin-top: 10px;
        height: 27px;
        color: #F0F4EF;
        text-align: center;
        align-items: center;
        justify-content: space-between;
    }

    .analis_btn {
        color: #F2994A;
        background: #233D4D;
        border-radius: 20px;
        border: 0;
        height: 25px;
        width: 75px;
        cursor: pointer;
    }

    .cards {
        min-height: 180px;
    }

    .card {
        display: flex;
        justify-content: space-between;
        align-items: center;
        padding-left: 5%;
        padding-right: 5%;
        margin-top: 10px;
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

    .balance_text {}

    .courses {
        width: 86%;
        margin-left: 7%;
        display: flex;
        justify-content: space-between;
        background: #F2994A;
        margin-top: 10px;
        padding-left: 10px;
        padding-right: 10px;
        color: #fff;
    }

    .courses p {
        margin: 0;
        font-size: 14px;
    }

    .courses_p {
        background: inherit;
        width: 86%;
        margin-left: 7%;
        display: flex;
        justify-content: space-between;
        margin-top: 10px;
        padding-left: 10px;
        padding-right: 10px;
    }

    .courses_p p {
        margin: 0;
        margin-top: 10px;
        width: 30%;
    }

    .name_p {
        width: 50% !important;
    }

    .p_p {
        width: 15% !important;
    }
    .cardsNotFound {
        width: 90%;
        margin-left: 5%;
        display: block;
        
    }
    .createCardBtn {
        color: #F2994A;
        font-size: 22px;
        cursor: pointer;
    }
</style>