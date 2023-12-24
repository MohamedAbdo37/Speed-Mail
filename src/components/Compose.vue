<template>
 <div class="container">
        <div class="header">
            <h1>New Message</h1>
          <router-link to="/">X</router-link>
        </div>
        <div class="form-group">
            <label for="from">from</label>
            <input type="text" id="from" required  v-model="from">
        </div>
        <div class="form-group">
            <label for="to">To</label>
            <input type="text" id="to" required  v-model="to">
        </div>
        <div class="form-group">
            <label for="subject">subject</label>
            <input type="text" id="subject" required  v-model="subject">
        </div>
        <div class="form-group">
            <label for="tag">Tag</label>
            <div v-for="(input, index) in tag" :key="index">
            <input v-model="input.value" @input="updateInput(index, $event.target.value)" type="text" id="tag" required  >
            <button @click="removeInput(index)">Remove</button>
            </div>
            <button @click="addInput" class="send-button">Add Input</button>
        </div>
        <div class="form-group">
            <label for="message">Message</label>
            <textarea id="message" required v-model="message"></textarea>
        </div>
        <div id="Priority">
            <label for="priority_select">Choose Your priority:</label>
            <select v-model="priority" id="priority_select">
            <option value=100>100 %</option>
            <option value=75>75 %</option>
            <option value=50 selected>50 %</option>
            <option value=25>25 %</option>
            </select>
        </div>
        <button @click="send()" class="send-button">Send email</button>
        <button @click="draft()" class="send-button">draft</button>
        <div class="input-container">
            <label for="photo-upload" class="icon">
              <i class="fas fa-camera"></i>
            </label>
            <input type="file" id="photo-upload">
          </div>
  <div class="input-container">
    <label for="file-upload" class="icon">
      <i class="fas fa-file"></i>
    </label>
    <input type="file" id="file-upload">
  </div>
 </div>
  </template>
<script>

import axios from 'axios';

export default {
  name: 'ComPose',
  components: {},
  data() {
    return {
      subject: '',
      to: '',
      message: '',
      from: '',
      date: '',
      tag: [],
      priority:50,

    };
  },
  methods: {
    send() {
      this.date = new Date();
      console.log(this.date)
      axios.get('http://localhost:8081/send', {
        params: {
          to: this.to,
          from: this.from,
          message: this.message,
          subject: this.subject,
          tag: this.tag,
          priority:this.priority,
          date: Number(this.date),
        },
      }).then((r) => {
        console.log('done send');
        console.log(r.data);
        console.log(this.date);
      });
    },
    addInput() {
          this.tag.push({ value: '' });
        },
        removeInput(index) {
          this.tag.splice(index, 1);
        },
        updateInput(index, value) {
          this.tag[index].value = value;
          console.log(this.tag[index])
        },
    draft() {
      this.date = new Date();
      axios.get('http://localhost:8081/draft', {
        params: {
          to: this.to,
          from: this.from,
          message: this.message,
          subject: this.subject,
          tag: this.tag,
          date: Number(this.date),
        },
      }).then((r) => {
        console.log('done draft');
        console.log(r.data);
      });
    },
  },
};
</script>
<style scoped>
.container {
    max-width: 500px;
    margin: 0 auto;
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
