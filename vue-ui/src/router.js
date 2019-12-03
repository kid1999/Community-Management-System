import Vue from 'vue'
import Router from 'vue-router'
import Home from './views/Home.vue'

Vue.use(Router);

export default new Router({
  mode: 'history',
  base: process.env.BASE_URL,
  routes: [
    {
      path: '/',
      name: 'home',
      component: Home
    },
    {
      path: '/login',
      name: 'login',
      component: () => import('./views/Login.vue')
    },
    {
      path: '/addMember',
      name: 'addMember',
      component: () => import('./views/admin/addMember')
    },
    {
      path: '/users',
      name: 'users',
      component: () => import('./views/admin/Users')
    },
    {
      path: '/addAdmin',
      name: 'addAdmin',
      component: () => import('./views/super/addAdmin'),
      meta: { requiresAuth: true }
    },
    {
      path: '/members',
      name: 'members',
      component: () => import('./views/admin/Members')
    },
    {
      path: '/test',
      name: 'test',
      component: () => import('./views/Test')
    },

  ],

})
