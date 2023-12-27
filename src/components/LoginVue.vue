<template>
    <!-- <img  class="logo" src="../assets/hospital_logo.jpeg"> -->
    <h1>Login</h1>
    <form class="login"  @submit="login">
        <input type="email" v-model="email" placeholder="Enter Email" :email='email' required />
        <input type="password" v-model="password" placeholder="Enter Password" required />
        <input type="submit" value="login" style="padding: 0%;"/>
        <p>
            <router-link to="/SignUp">sign up</router-link>
        </p>
    </form>
</template>

<script>
    //import axios from 'axios'
    //import hoMe from './Home.vue'
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
                // let result = await axios.get(
                //     `http://localhost:8081/users?email=${ this.email }&password=${ this.password }`
                // ).then((r) => {
                //    let user = r.data
                //    if(user)
                //        this.$router.push( { name: 'Home', query: { email: this.email } });
                //    else
                //        console.log("user not found")
                //})
                this.$router.push( { name: 'Home', query: { email: this.email } });
            }
        },
        mounted() {
            let user = localStorage.getItem("user-info");
            if(user) {
                this.$router.push( {name: "Home"} );
            }
        },
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