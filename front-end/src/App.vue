<template>
  <div id="app">
    <Reg v-if="authForm == 'reg'" @changeAuthScreen='changeAuthScreen' />    
    <Login v-if="authForm == 'login'" @changeAuthScreen='changeAuthScreen' />
    <Main v-if="authForm == 'main'" />
  </div>
</template>

<script>
import Reg from './components/Reg.vue'
import Login from './components/Login.vue'
import Main from './components/Main.vue'

export default {
  name: 'App',
  components: {
    Reg,
    Login,
    Main
  },
  data() {
    return {
      authForm: 'reg'
    }
  },
  mounted() {
    if (localStorage.token != "undefined" && localStorage.token && localStorage.token != '') {
        this.changeAuthScreen('main')
      }
  },
  methods: {
    changeAuthScreen(data) {
      this.authForm = data;
    }
  }
}
</script>

<style>
@import url('https://fonts.googleapis.com/css2?family=Roboto:ital,wght@0,100;0,300;0,400;0,500;0,700;0,900;1,100;1,300;1,400;1,500;1,700&display=swap');

#app {
    font-family: 'Roboto', sans-serif;
  /* font-family: Avenir, Helvetica, Arial, sans-serif;
  -webkit-font-smoothing: antialiased;
  -moz-osx-font-smoothing: grayscale; */
  /* color: #2c3e50; */
}
body {
  margin: 0;
  padding: 0;
}
* {
  box-sizing: border-box;
}
</style>
