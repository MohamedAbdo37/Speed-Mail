import { createRouter, createWebHistory } from 'vue-router';
import hoMe from '../components/Home.vue';
import ComPose from '../components/Compose.vue';
import TraSh from '../components/Trash.vue';
import LoginVue from '../components/LoginVue.vue';
import SignUp from '../components/SignUp.vue';
import DraftBrowse from '../components/DraftBrowse.vue'
import addContact from '../components/addcontact.vue';
import ContacT from '../components/contact.vue';
const routes = [
  {
    path: '/home',
    name: 'Home',
    component: hoMe,
    props: true
  },
  {
    path: '/ContacT',
    name: 'ContacT',
    component: ContacT,
    props: true
  },
  {
    path: '/Compose',
    name: 'ComPose',
    component: ComPose,
  },
  {
    path: '/addContact',
    name: 'addContact',
    component: addContact,
  },
  {
    path: '/Trash',
    name: 'Trash',
    component: TraSh,
  },

  {
    path: '/',
    name: 'LoginVue',
    component: LoginVue,
    props: true
  },
  {
    path: '/SignUp',
    name: 'SignUp',
    component: SignUp
  },
  {
    path: '/draft',
    name: 'DraftBrowse',
    component: DraftBrowse
  },
];

const router = createRouter({
  history: createWebHistory(process.env.BASE_URL),
  routes,
});

export default router;
