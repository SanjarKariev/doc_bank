<template> 
    <div class="reg" >
        <div action="" method="get" class="reg-form" >
            <div class="reg-box-img" >
                <img class="reg-img" src="../assets/Logo.png" alt="">
            </div>
            <div>
                <input class="reg-input" id="login-login" type="text" placeholder="Login">
                <input class="reg-input" id="login-password" type="text" placeholder="Password">
            </div>
            <div>
                <button @click="login" class="reg-btn" >Войти</button>
                <a @click='toReg' class="reg-link" >Зарегестрироватся</a>
            </div>
        </div>
    </div>
</template>

<script>
import axios from 'axios'

export default {
    name: 'reg',
    methods: {
        toReg() {
            this.$emit('changeAuthScreen', 'reg')
        },
        login() {
            let data ={
                username: document.querySelector('#login-login').value,
                password: document.querySelector('#login-password').value
            };

            axios.post('http://localhost:8090/api/auth/signin', data)
                .then((response) => {
                    console.log(response);
                    localStorage.token = response.data.body.token
                    this.$emit('changeAuthScreen', 'main')
                })
                .catch((error) => {
                    console.log(error.message);
                    alert('Произошла ошибка')
                });

        }
    }
}
</script>

<style scoped>
.reg-form {
    display: flex;
    flex-direction: column;
    justify-content: space-around;
    width: 250px;
    min-height: 550px;
}
.reg {
    display: flex;
    justify-content: center;
    align-items: center;
    width: 100vw;
    height: 100vh;
    background: #f0f4ef;
}
.reg-input,
.reg-btn,
.reg-link {
    background: #f0f4ef;
    width: 255px;
    margin-top: 10px;
    height: 32px;
    border: 1px solid #2B373F;
    border-radius: 10px;
    padding: 10px;
    font-size: 14px;
    text-align: center;
}
.reg-btn {
    background:  #233D4D;
    color: #F2994A;
    line-height: 0px;
    cursor: pointer;
}
.reg-link {
    background: inherit;
    border: none;
    cursor: pointer;
    text-align: center!important;
    width: 100%!important;
}
.box {
    display: flex;
    justify-content: baseline;
    align-items: center;
    font-size: 14px;
    margin-top: 1px;
    max-width: 250px;
}
.inp-check {
    width: 20px;
}
.reg-text-check {
    width: 250px;
    font-size: 10px;
}
.reg-img {
    width: 90px;
}
.reg-box-img {
    width: 250px;
    justify-content: center;
    align-items: center;
}
a {
    margin-left: 60px;
}
</style>