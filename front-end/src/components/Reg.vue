<template> 
    <div class="reg" >
        <div class="reg-form" >
            <div class="reg-box-img" >
                <img class="reg-img" src="../assets/Logo.png" alt="">
            </div>
            <input class="reg-input" type="text" id="reg-login" placeholder="login">
            <input class="reg-input" type="text" id="reg-email" placeholder="Email">
            <input class="reg-input" type="text" id="reg-name" placeholder="Имя">
            <input class="reg-input" type="text" id="reg-name2" placeholder="Фамилия">
            <input class="reg-input" type="text" id="reg-name3" placeholder="Отчество">
            <input class="reg-input" type="text" id="reg-pass" placeholder="Пароль">
            <div class="box" >
                <input class="reg-input inp-check" type="checkbox" name="" id="">
                <p class="reg-text-check" >Я соглашаюсь на обработку своих персональных данных</p>
            </div>
            <div class="box" >
                <input class="reg-input inp-check" type="checkbox" name="" id="">
                <p class="reg-text-check" >Я подтверждаю что ознакомлен с ...</p>
            </div>
            <button class="reg-btn" @click='registration()' >Зарегестрироватся</button>
            <a @click="toReg" class="reg-link" >Уже клиент банка</a>

        </div>
    </div>
</template>

<script>
import axios from 'axios'

export default {
    name: 'reg',
    methods: {
        toReg() {
            this.$emit('changeAuthScreen', 'login')
        },
        registration() {
            let data = {
                "email": document.querySelector('#reg-email').value,
                "firstName": document.querySelector('#reg-name').value,
                "middleName": document.querySelector('#reg-name3').value,
                "password": document.querySelector('#reg-pass').value,
                "secondName": document.querySelector('#reg-name2').value,
                "username": document.querySelector('#reg-login').value
            }
            
            axios.post('http://localhost:8090/api/auth/signup', data)
                .then((response) => {
                    console.log(response);
                    alert('Вы успешно зарегестрировались')
                    this.$emit('changeAuthScreen', 'login')
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
    justify-content: center;
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
}
.box {
    display: flex;
    justify-content: baseline;
    align-items: center;
    font-size: 14px;
    margin-top: 1px;
    max-width: 250px;
    gap: 10px;
}
.box p {
    margin: 0;
}
.reg-text-check {
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
</style>