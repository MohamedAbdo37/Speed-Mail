<template>
  <div class="container">
  <div class="sidebar">
    <div id="gmail_pic">
    </div>
    <button id="compose" class="compose" @click="compose">
      <i class="fa-solid fa-pencil"></i>Compose
    </button>
    <ul>
      <li>
        <button  id="inbox-button">
          <i class="fa fa-light fa-inbox"></i>Inbox
        </button>
      </li>
      <li>
        <button id="trash-button">
          <i class="fa-solid fa-trash-can"></i>Trash
        </button>
      </li>
      <li>
        <button  id="sent-button">
          <i class="fa fa-light fa-paper-plane"></i>Sent
        </button>
      </li>
      <li>
        <button  id="draft-button">
          <i class="fa-regular fa-file"></i>Draft
        </button>
      </li>
      <li>
        <button
          style="font-weight: bold"
          id="contacts-button"
        >
          Contacts
        </button>
      </li>
      <li>
        <button>
          <i class="fa-solid fa-plus"></i>Add Contact
        </button>
      </li>
      <li>
        <button
          style="font-weight: bold"
          id="folders-button"
        >
          Folders
        </button>
      </li>
      <li>
        <button @click="addfolder" onclick="document.getElementById('foldername').style.display='block',document.getElementById('create').style.display='block'">
          <i class="fa-solid fa-plus"></i>Add Folder
        </button>
      </li>
      <li>
      <input type="text" id="foldername" style="display: none;" required  v-model="foldernames[i]">
      <button @click="addid" id ="create" style="display: none;" onclick=" document.getElementById('foldername').style.display='none',document.getElementById('create').style.display='none',document.getElementById('show').style.display='block'"  >create</button>
      <ul  id="show" style="display: none; " class="folders">
      <li v-for="name in foldernames" :key="name.id">
        <button >
          <i v-if="name" class="fa fa-folder-o"></i>
        </button>
        <button @click="getindex(name)" onclick=" document.getElementById('name').style.display='block', document.getElementById('re').style.display='block'" >
          {{ name }}
        </button>
        <input type="text" id="name" style="display: none;" required  v-model="foldernames[b]">
        <button id ="re" style="display: none;" onclick=" document.getElementById('name').style.display='none',document.getElementById('re').style.display='none'"  >rename</button>
      </li>
    </ul>
    </li>
    </ul>
  </div>

  <div class="associated-main">
    <div class="search-field" id="search-field">
      <button @click="Search">
        <i class="fa-solid fa-magnifying-glass"></i>
      </button>
      <input v-model="type" type="text" id="search-input" placeholder="Type" />
      <input v-model="search" type="text" id="search" placeholder="   Search" />
    </div>
    <div class="light-blocker" id="light-blocker"></div>
    <div class="pop-up-window" id="move-to-folder-window">
      <div class="new-message-label" style="margin-left: 0">
        <span>Move to folder</span>
        <a
          ><button >
            <i class="fa-solid fa-circle-xmark"></i></button
        ></a>
      </div>
      <div class="folder-name">
        <span>Enter the name of the folder:</span>
        <input type="text" id="folder-entered-name" />
      </div>
      <button id="create-folder" >Move</button>
    </div>

    <div class="mails-recieved">
      <div class="emails-options" id="emails-options">
        <button  id="refresh-button">
          <i class="fa-solid fa-arrows-rotate"></i>
          <router-link to="/">Refresh</router-link>
        </button>

        <button  id="move-to-folder-button">
          <i class="fa fa-light fa-up-down-left-right"></i>Move to folder
        </button>

        <button  id="delete-button">
          <i class="fa-solid fa-trash-can"></i>Delete
        </button>

        <button id="restore-button">
          <i class="fa fa-light fa-trash-arrow-up"></i>Restore
        </button>

        <div>
          <label for="sort_select">Sort:</label>
          <select id="sort_select">
            <option selected><button>Date</button></option>
            <option><button>Priority</button></option>
          </select>
          <select id="order_select" v-model="sort">
            <option value="Ascendingly" selected><button>Ascendingly</button></option>
            <option value="Decendingly"><button>Decendingly</button></option>
          </select>
          <button v-if="sort=='Ascendingly'" @click="datesortingasc" id="sort-button">Sort</button>
          <button v-if="sort=='Decendingly'" @click="datesortingdes" id="sort-button">Sort</button>
        </div>

        <div id="filter-div">
          <label for="Filter-select">Filter by:</label>
          <select id="Filter-select" v-model="filterby">
            <option value="sender"><button  >Sender </button></option>
            <option value="subject" selected><button>Subject</button></option>
          </select>
          <div v-for="(input, index) in filter" :key="index">
          <input v-model="input.value" @input="updateInput(index, $event.target.value)" type="text" id="filter-input" style="margin-left: 5px"   >
          <button @click="removeInput(index)">Remove</button>
          </div>
          <button @click="addInput">Add Input</button>
          <button id="filter-button" @click="Filter" >Filter</button>
        </div>
      </div>
      <hr style="margin-top: 20px; margin-bottom: 20px" id="horizontal-line" />
      <h1>Your Inbox</h1>
      <div v-for="mail in mails" :key="mail.id" class="mail">
        <input type="checkbox">
        <div class="properties">
          <div><h5>{{ mail.From }}</h5></div>
          <div><h5>{{ mail.Subject }}</h5></div>
          <div><h5>{{ mail.Date }}</h5></div>
          <div><h5>{{ mail.Type }}</h5></div>
        </div>
      </div>
    </div>
  </div>
</div>
</template>

<script>
import axios from 'axios';

export default {
  name: 'hoMe',
  components: {
  },
  data() {
    return {
      b: 0,
      i: 0,
      sort :"",
      userName: "",
      type: "",
      search: "",
      emailAddress: "",
      password: "",
      foldernames: [],
      contactnames: [],
      filterby: "",
      filter: [],
      mails: [
        {
          To: "Yahiaibrahime123@gmail.com",
          From: "MohamedAbdo2002@gmail.com",
          Subject: "CO Cache Lab",
          Date: "Wed Dec 30 2023 18:45:12 GMT+0200 (Eastern European Standard Time)",
          Type: "social",
          Body: "Dear Yahia\n   J'espere que vous allez bien et votre famille aussi\n J'ai quelques deficultes avex le lab de cache memory, il prend trop long temps en fait et ca me donne l'ampression que le lab a quelque chose qui sonne"
        },
        {
          To: "Yahiaibrahime123@gmail.com",
          From: "MohamedHassan2002@gmail.com",
          Subject: "OOP project",
          Date: "Fri Dec 25 2023 10:30:00 GMT+0200 (Eastern European Standard Time)",
          Type: "social",
          Body: "Dear Yahian\n  J'espere que tu vas bien et votre famille aussi"
        },
        {
          To: "Yahiaibrahime123@gmail.com",
          From: "Hossamosama2003@gmail.com",
          Subject: "OOP project",
          Date: "Sun Dec 31 2023 23:13:43 GMT+0200 (Eastern European Standard Time)",
          Type: "crying",
          Body: "Dear Yahian\n  J'espere que tu vas bien et votre famille aussi"
        }
      ]
    };
  },
  methods: {
    addfolder() {

    },
    datesortingdes() {
  const compareDates = (a, b) => {
  const dateA = new Date(a.Date);
  const dateB = new Date(b.Date);
  return dateB - dateA;
};
this.mails.sort(compareDates);
},
datesortingasc() {
  const compareDates = (a, b) => {
  const dateA = new Date(a.Date);
  const dateB = new Date(b.Date);
  return dateA - dateB;
};
this.mails.sort(compareDates);
},
    getindex(name) {
      this.b=this.foldernames.indexOf(name);
},
    Search() {
      axios.get('http://localhost:8081/search', {
        params: {
          search: this.search,
          type: this.type,
        },
      }).then((r) => {
        console.log('done filter');
        console.log(r.data)
      });
    },
    addInput() {
          this.filter.push({ value: '' });
        },
        removeInput(index) {
          this.filter.splice(index, 1);
        },
        updateInput(index, value) {
          this.filter[index].value = value;
          console.log(this.filter[index])
        },
    Filter() {
      axios.get('http://localhost:8081/filter', {
        params: {
          filterby: this.filterby,
          filter: this.filter,
        },
      }).then((r) => {
        console.log('done filter');
        console.log(r.data)
      });
},
    addid() {
      this.i += 1;
      console.log(this.i);
    },
    compose() {
      this.$router.push('/Compose');
    },
  },
};
</script>
<style scoped>
.container {
    position: relative;
    display: flex;
    height: 100vh;
    margin: 0px;
    padding: 0px;
    box-sizing: border-box;
    font-family: sans-serif;
    background-color: rgba(128, 128, 128, 0.1);
    /* background: rgb(211, 211, 212) !important; */
}

:root {
    --main-hover-color-button: rgba(128, 128, 128, 0.2);
    --main-searching-field-color: #eaf1fb;
}

/* styling of the side nav */
.sidebar {
    width: 250px;
    height: 100vh;
    overflow: hidden;
    overflow-y: scroll;

}

.compose {
    /* margin-top: 50px; */
    margin-left: 40px;
    margin-bottom: 30px;
    background-color: lightblue;
    padding: 20px;
    border-radius: 10px;
    font-size: 1.1em;
    font-weight: 500;
    transition: 0.25s ease-in-out;
}

#welcome-header{
  margin-left: 25%;
  display: flex;
  flex-direction: row;
  align-items: center;
  /* margin-top: 30px; */
}
button:hover {
    cursor: pointer;
}

.compose:hover {
    box-shadow: 2px 2px 2px black;
}

.sidebar ul {
    list-style: none;
    height: 200px;
    width: auto;

}

#gmail_pic{
  display: flex;
  justify-content: center;
  align-items: center;
}

#nice-icon{
  width: 40px;
}

.sidebar ul li {
    margin-bottom: 5px;
    font-size: 20px;
    font-weight: 500;
    font-weight: bold;
}

.sidebar ul button {
    padding: 10px;
    margin-left: 0;
    /* padding-right: 110px; */
    border-radius: 40px 999em 999em 40px;
    transition: 0.2s ease-in-out;
}

.sidebar ul button:hover {
    background-color: rgba(128, 128, 128, 0.2);

}

.sidebar button {
    border: none;
}

i {
    margin-right: 10px;
}

.sidebar a {
    display: block;
    margin-top: 150px;
    outline: none;
    padding: 10px;
    margin-left: 40px;
    width: 50%;
    border-radius: 5px;
    text-align: center;
    color: black;
}
#foldername {
  width: 60%;
}
.folders {
  margin-top: 110px;
  margin-left: 10px;
  width: 10%;
}
.folders li {
  font-size: 1px;
}

/* end the styling of the side nav */

/* styling of the main associated to inbox */

.associated-main {
    display: flex;
    flex-direction: column;
}

.search-field {
    width: 70vw;
    margin-top: 10px;
    border-radius: 20px;
    background-color: #eaf1fb;
}

.search-field button {
    border: none;
    font-size: 20px;
    padding: 10px;
    text-align: center;
    border-radius: 10px;
    background-color: #eaf1fb;
}

.search-field input {
    border: none;
    padding: 10px;
    font-size: 1.25em;
    width: 50vw;
    background-color: #eaf1fb;
    ;
}

.search-field i {
    margin: 0;
}

.search-field button:hover {
    background-color: rgba(128, 128, 128, 0.2);
    border-radius: 50%;
}

.emails-options {
    display: flex;
    flex-direction: row;
    justify-content: center;
    align-items: center;
    justify-content: space-between;
}

.emails-options button {
    border: none;
    background: rgba(128, 128, 128, 0.1);
    padding: 10px;
    border-radius: 8px;
}

.emails-options button:hover {
    background-color: rgba(128, 128, 128, 0.2);
    /* padding: 3px; */
}

.emails-options select {
    padding: 10px;
    border-radius: 8px;
    background: rgba(128, 128, 128, 0.1);
    border: none;
    margin-left: 10px;
}

.emails-options select:hover {
    background-color: rgba(128, 128, 128, 0.2);
    cursor: pointer;
}

.emails-options input {
    font-size: 1.1em;
    border-radius: 3px;
}

#filter-button,
#sort-button {
    background-color: lightblue;
    margin-left: 5px;
}
#filter-button:hover,
#sort-button:hover {
    background-color: rgb(149, 211, 231);
}

.mails-recieved {
    margin-top: 15px;
    background-color: white;
    border: 2px solid white;
    padding: 30px;
    width: 80vw;
    height: 80vh;
    border-radius: 25px;
    overflow: hidden;
    overflow-y: scroll;
}

.light-blocker {
  position: absolute;
  width: 100vw;
  height: 100vh;
  background-color: #000000;
  opacity: 0.3;
  top: 0;
  right: 0;
  display: none;
}

.pop-up-window {
  font-weight: 500;
  position: absolute;
  top: 50%;
  font-size: 1.2em;
  left: 50%;
  transform: translate(-50%, -50%);
  width: 40vw;
  z-index: 10000;
  border-radius: 10px;
  background-color: white;
  display: flex;
  flex-direction: column;
  display: none;
}

.pop-up-window input{
  border: none;
  font-size: 1.1em;
}

.pop-up-window button {
  cursor: pointer;
  background-color: lightgreen;
  padding: 10px;
  font-size: 1.1em;
  font-weight: bold;
  border: none;
  width: 100%;
}
.pop-up-window button:hover{
  background-color: rgb(192, 250, 192);
}

.pop-up-window div:not(.send-mail-window div:first-child) {
  margin-bottom: 30px;
  margin-left: 10px;
}

#move-to-options{
  display: flex;
  flex-direction: column;
}
.new-message-label {
  padding: 7px;
  background-color: rgb(120, 187, 209);
  border-radius: 10px;
  display: flex;
  justify-content: space-between;
  align-items: center;
  margin-bottom: 30px;
}

.new-message-label button {
  border: none;
  background-color: rgb(120, 187, 209);
}

.new-message-label button:hover {
  background-color: rgb(57, 142, 170);
  border-radius: 50%;
}

.new-message-label i {
  padding: 10px;
  margin: 0;
  text-align: center;
  font-size: 1.25em;
}

.mail {
  width: 95%;
  height: 40px;
  display: flex;
  border-radius: 5px;
  margin: auto;
  margin-top: 10px;
  border-width: 2px;
  border:dodgerblue;
  border-style: solid;
}
.mail:hover {
  box-shadow: 0 5px 5px rgba(0, 0, 0, .3);
}
.mail .properties{
  display: grid;
  grid-template-columns: repeat(auto-fit, minmax(25%, 1fr));
  text-align: center;
  padding-left: 10px;
  width: 90%;
}
.mail .properties h5 {
 margin: auto;
 margin-top: 15px;
}
</style>
