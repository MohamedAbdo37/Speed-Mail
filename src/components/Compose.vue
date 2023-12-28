<template>
 <div class="container">
        <div class="header">
            <h1>New Message</h1>
          <router-link to="/home" @click="home">X</router-link>
        </div>
        <!-- <div class="form-group">
            <label for="from">from</label>
            <input type="text" id="from" required  v-model="from">
        </div> -->
        <div class="form-group">
          <label for="to">To</label>
            <div v-for="(input, index) in to" :key="index">
            <input v-model="input.value" @input="updateInput2(index, $event.target.value)" type="text" :id="'to' + index" required >
            <button @click="removeInput2(index)">Remove</button>
            </div>
            <button @click="addInput2" class="send-button">Add Input</button>
        </div>
        <div class="form-group">
            <label for="subject">subject</label>
            <input type="text" id="subject" required  v-model="subject">
        </div>
        <div class="form-group">
          <label for="tag">Tag</label>
          <div v-for="(input, index) in tag" :key="index">
          <input v-model="input.value" @input="updateInput1(index, $event.target.value)" type="text" :id="'tag' + index" required>
          <button @click="removeInput1(index)">Remove</button>
</div>
<button @click="addInput1" class="send-button">Add Input</button>
        </div>
        <div class="form-group">
            <label for="message">Message</label>
            <textarea id="message" required v-model="message"></textarea>
        </div>
        <div id="Priority" style="padding: 20px;">
            <label for="priority_select">Choose Your priority:</label>
            <select style="background-color: lightblue;" v-model="priority" id="priority_select">
            <option value=100>100 %</option>
            <option value=75>75 %</option>
            <option value=50 selected>50 %</option>
            <option value=25>25 %</option>
            </select>
        </div>
        <button @click="send()" class="send-button">Send email</button>
        <button @click="draft()" class="send-button">draft</button>
 <!-- Add attachment -->
 <div class="input-container">
    <label for="file-upload" class="icon">
      <i class="fas fa-file"></i>
    </label>
    <input type="file" id="file-upload" @change="handleFileUpload" multiple>
  </div>
    <!-- Display attachments -->
    <div v-for="(attachment, index) in attachments" :key="index" class="display">
      <span @click="viewAttachment(attachment)">{{ attachment.name }}</span>
      <button class="send-button" @click="removeAttachment(index)">Remove</button>
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
      attachments: [],
      subject: '',
      to: [],
      message: '',
      from: '',
      date: '',
      tag: [],
      priority:50,
      to2: [],
    };
  },
  mounted() {
    axios.get(`http://localhost:8081/mail?id=${this.draftId}`).then((r) => {
      let draft = r.data
      if(draft) {
        this.subject = draft.subject;
        this.to = draft.to;
        this.message = draft.body;
        this.from = draft.from;
        this.date = draft.date;
        this.tag = draft.types;
        this.priority = draft.priority;
      }
    })
  },
  methods: {
    home() {
      this.$router.push( { name: 'Home', query: { email: this.userEmail } });
    },
    viewAttachment(attachment) {
      const fileURL = URL.createObjectURL(attachment);
      window.open(fileURL);
    },
   async send() {
    this.date = new Date();
    console.log(this.date)
    this.tag.forEach((input, index) => {
    this.tag[index] = input.value;
    });
    this.to.forEach((input, index) => {
      this.to[index] = input.value;
    });
    const params = new URLSearchParams();
    this.attachments.forEach(file => {
      params.append('attachments', file);
    });
    await axios.post(`http://localhost:8081/send?to=${this.to.join(',')}&from=${this.userEmail}&subject=${this.subject}&tag=${this.tag.join(',')}&priority=${this.priority}&date=${this.date}&messasge=${this.message}`
        ).then((r) => {
        console.log('done send');
        console.log(r.data);
        console.log(this.date);
      });
    },
    addInput1() {
          this.tag.push({ value: '' });
        },
        removeInput1(index) {
          this.tag.splice(index, 1);
        },
        updateInput1(index, value) {
          this.tag[index].value = value;
          console.log(this.tag[index])
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
    draft() {
      this.date = new Date();
      this.tag.forEach((input, index) => {
      this.tag[index] = input.value;
    });
    this.to.forEach((input, index) => {
      this.to2[index] = input.value;
    });
    axios.post(`http://localhost:8081/draft?to=${this.to.join(',')}&from=${this.userEmail}&subject=${this.subject}&tag=${this.tag.join(',')}&priority=${this.priority}&date=${this.date}&messasge=${this.message}`
        ).then((r) => {
        console.log('done draft');
        console.log(r.data);
      });
    },
    handleFileUpload(event) {
      const files = event.target.files;
      // Store the files in the attachments array or data property
      for (let i = 0; i < files.length; i++) {
        this.attachments.push(files[i]);
      }
    },
    removeAttachment(index) {
      this.attachments.splice(index, 1);
    },
  },
  computed: {
    userEmail() {
      return this.$route.query.email;
    },
    draftId() {
      return this.$route.query.iD;
    },
    tagvalues(){
      return this.tag.filter(tag1 => tag1.value);
  }
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
