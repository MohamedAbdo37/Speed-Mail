<template>
    <!-- <img  class="logo" src="../assets/hospital_logo.jpeg"> -->
    <h1>sign up</h1>
    <form class="register" @submit="signUp">
        <input type="text" v-model="name" placeholder="Enter Name" />
        <input type="email" v-model="email" placeholder="Enter Email" />
        <input type="password" v-model="password" placeholder="Enter Password" />
        <input type="submit" value="sign up" style="padding: 0px;"/>
        <p>
            <router-link to="/Login">Login</router-link>
        </p>
    </form>
</template>
<script>
import axios from 'axios'
    export default {
        name : 'SignUp' ,
        data() {
            return {
                name: "",
                email: "",
                password: ""
            }
        },
        methods: {
            async signUp() {
                await axios.post("http://localhost:8081/users/", { 
                    name: this.name,
                    email: this.email,
                    password: this.password
                }).then((r) => {
                    console.log(r.data);
                    this.$router.push( { name: 'Home', query: { email: this.email } });
                })
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
    .register input{
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
