<template>
    <div class="container">
           <div class="header">
               <h1>New contact</h1>
             <router-link to="/home" @click="home">X</router-link>
           </div>
           <div class="form-group">
               <label for="from">name</label>
               <input type="text" id="from" required  v-model="name">
           </div>
           <div class="form-group">
             <label for="to">Email address</label>
               <div v-for="(input, index) in to" :key="index">
               <input v-model="input.value" @input="updateInput2(index, $event.target.value)" type="text" :id="'to' + index" required  >
               <button @click="removeInput2(index)">Remove</button>
               </div>
               <button @click="addInput2" class="send-button">Add Input</button>
           </div>
           <button @click="send()" class="send-button">Save contact</button>
    </div>
     </template>
   <script>
   
   import axios from 'axios';
   
   export default {
     name: 'addContact',
     components: {},
     data() {
       return {
         to: [],
         name:"",
       };
     },
     methods: {
       home() {
         this.$router.push( { name: 'Home', query: { email: this.userEmail } });
       },
       save() {
       this.to.forEach((input, index) => {
         this.to[index] = input.value;
       });
         axios.get('http://localhost:8081/savecontact', {
           params: {
             emailadrress: this.to,
             name:this.name,
           },
         }).then((r) => {
           console.log('done contact');
           console.log(r.data);
           console.log(this.date);
         });
       },
           addInput2() {
             this.to.push({ value: '' });
           },
           removeInput2(index) {
             this.to.splice(index, 1);
           },
           updateInput2(index, value) {
             this.to[index].value = value;
             console.log(this.to[index])
           },
     },
     computed: {
       userEmail() {
         return this.$route.query.email;
       },
   }
   };
   </script>
   <style scoped>
   .container {
       max-width: 500px;
       margin: 0 auto;
       padding: 20px;
   }
   
   #Priority_select {
       padding: 10px;
       border-radius: 8px;
       background: rgba(128, 128, 128, 0.1);
       border: none;
       margin-left: 10px;
   }
   .display {
     padding: 20px;
   }
   .header {
       display: flex;
       align-items: center;
       justify-content: space-between;
       margin-bottom: 20px;
       background-color: lightblue;
       padding: 17px;
       border-radius: 8px;
   }
   
   .header h1 {
       font-size: 24px;
       margin: 0;
   }
   
   .back-link {
       text-decoration: none;
       color: #333;
       font-size: 24px;
   }
   
   .form-group {
       margin-bottom: 20px;
   }
   
   label {
       display: block;
       font-weight: bold;
       margin-bottom: 5px;
   }
   
   input[type="text"],
   textarea {
       width: 100%;
       padding: 10px;
       font-size: 16px;
       border-radius: 4px;
       border: 1px solid #ccc;
   }
   
   button.send-button {
       background-color:blue;
       color: #fff;
       padding: 10px 20px;
       border: none;
       border-radius: 4px;
       font-size: 16px;
       cursor: pointer;
       margin-left: 10px;
   }
   button.send-button:hover{
     background-color:  lightblue;
     transition: 0.5s;
   }
   .input-container {
         display: flex;
         align-items: center;
         float: right;
         padding-top: 11px;
       }
       .input-container input[type="file"] {
         display: none;
       }
       .input-container .icon {
         margin-right: 10px;
         cursor: pointer;
       }
   </style>
   