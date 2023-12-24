<template>
    <!-- <img  class="logo" src="../assets/hospital_logo.jpeg"> -->
    <h1>Login</h1>
    <form class="login"  @submit="login">
        <input type="email" v-model="email" placeholder="Enter Email" />
        <input type="password" v-model="password" placeholder="Enter Password" />
        <input type="submit" value="login" style="padding: 0%;"/>
        <p>
            <router-link to="/sign-up">sign up</router-link>
        </p>
    </form>
</template>

<script>
    import axios from 'axios'
    export default {
        name: "LoginVue",
        data() {
            return {
            email: "",
            password: ""
            }
        },
        methods: {
            async login() {
                let result = await axios.get(
                    `http://localhost:3000/users?email=${ this.email }&password=${ this.password }`
                )
                if(result.status == 200 && result.data.length>0) {
                    localStorage.setItem("user-info", JSON.stringify(result.data[0]));
                    this.$router.push( {name: "Home"} );
                }
                console.log(result)
            }
        },
        mounted() {
            let user = localStorage.getItem("user-info");
            if(user) {
                this.$router.push( {name: "Home"} );
            }
        }
    }
</script>

<style>
    .login input{
        width: 300px;
        height: 40px;
        padding-left: 20px;
        display: block;
        margin-left: auto;
        margin-right: auto;
        margin-bottom: 20px;
        border: 1px solid skyblue;
    }
</style>