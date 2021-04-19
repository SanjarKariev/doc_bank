<template>
    <div class="main" >
        <header>
            <img src="../assets/Logo.png" alt="">
            <img src="../assets/Аккаунт.png" alt="">
        </header>

        <Analysis v-if="mainstate == 'Analysis'" />
        <History v-if="mainstate == 'History'" />
        <Cards v-if="mainstate == 'Cards'" />
        <Transition v-if="mainstate == 'Transition'" />
        <Deposits v-if="mainstate == 'Deposits'" />
        
        <nav>
            <img @click="() => toScreen('Deposits')" class="item" src="../assets/Вклады.png" alt="">
            <img @click="() => toScreen('Transition')" class="item" src="../assets/Перевести.png" alt="">
            <img @click="() => toScreen('Cards')" class="item" src="../assets/Главная.png" alt="">
            <img @click="() => toScreen('History')" class="item" src="../assets/История.png" alt="">
            <img @click="() => toScreen('Analysis')" class="item" src="../assets/Анализ.png" alt="">
        </nav>
    </div>
</template>

<script>
import Analysis from './Analysis.vue'
import History from './History.vue'
import Cards from './Cards.vue'
import Transition from './Transition'
import Deposits from './Deposits'

export default {
    name: "main",
    components: {
        Analysis,
        History,
        Cards,
        Transition,
        Deposits
    },
    data() {
        return {
            mainstate: 'Cards'
        }
    },
    methods: {
        toScreen(data) {
            this.mainstate = data
        }
    }
}
</script>

<style scoped>
.main {
    display: flex;
    flex-direction: column;
    padding-bottom: 50px;
}
header {
    height: 50px;
    width: 100%;
    background: #F2994A;

    display: flex;
    justify-content: space-between;
    align-items: center;

    padding-left: 10px;
    padding-right: 10px;

}
nav {
    position: absolute;
    bottom: 0;
    height: 50px;
    width: 100%;
    background: #F2994A;

    display: flex;
    justify-content: space-between;
    align-items: flex-end;
}
.item {
    cursor: pointer;
}
</style>