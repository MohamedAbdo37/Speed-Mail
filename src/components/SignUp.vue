<template>
    <!-- <img  class="logo" src="../assets/hospital_logo.jpeg"> -->
    <h1>sign up</h1>
    <form class="register">
        <input type="text" v-model="name" placeholder="Enter Name" name="name"/>
        <input type="email" v-model="email" placeholder="Enter Email" name="address"/>
        <input type="password" v-model="password" placeholder="Enter Password" name="password"/>
        <input type="button" value="sign up" style="padding: 0px;" @click="signUp"/>
        <p>
            <router-link to="/">Login</router-link>
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
                console.log("signup")
                await axios.get("http://localhost:8081/register", { 
                    params: {
                        name: this.name,
                        address: this.email,
                        password: String(this.password)
                    }    
                    
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
