import { createRouter, createWebHistory } from 'vue-router';
import hoMe from '../components/Home.vue';
import ComPose from '../components/Compose.vue';
import TraSh from '../components/Trash.vue';
import FolderBrowseVue from '../components/FolderBrowse.vue';

const routes = [
  {
    path: '/',
    name: 'Home',
    component: hoMe,
  },
  {
    path: '/Compose',
    name: 'ComPose',
    component: ComPose,
  },
  {
    path: '/Trash',
    name: 'Trash',
    component: TraSh,
  },
  {
    path: '/Folder',
    name: 'FolderBrowse',
    component: FolderBrowseVue,
  },
];

const router = createRouter({
  history: createWebHistory(process.env.BASE_URL),
  routes,
});

export default router;
