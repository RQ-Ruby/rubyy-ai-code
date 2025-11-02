import { createRouter, createWebHistory } from 'vue-router'
import HomeView from '../views/HomeView.vue'
import UserLoginView from '@/views/user/UserLoginView.vue'
import UserRegisterView from '@/views/user/UserRegisterView.vue'
import UserManageView from '@/views/admin/UserManageView.vue'
import AboutView from '@/views/AboutView.vue'
import ContactView from '@/views/ContactView.vue'


const router = createRouter({
  history: createWebHistory(import.meta.env.BASE_URL),
  routes: [
    {
      path: '/',
      name: 'home',
      component: HomeView,
    },
    {
      path: '/about',
      name: 'about',
      component: AboutView
    },
    {
      path: '/projects',
      name: 'projects',
      component: () => import('../views/ProjectsView.vue'),
    },
    {
      path: '/contact',
      name: 'contact',
      component: ContactView
    },
    {
      path: '/user/login',
      name: 'userLogin',
      component: () => import('../views/user/UserLoginView.vue'),
    },
    {
      path: '/user/register',
      name: 'userRegister',
      component: () => import('../views/user/UserRegisterView.vue'),
    },
    {
      path: '/admin/UserManage',
      name: 'userManage',
      component: UserManageView,
    },

  ],
})

export default router
