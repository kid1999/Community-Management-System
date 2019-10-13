import Vue from 'vue'
import Router from 'vue-router'
import Home from './views/Home.vue'

Vue.use(Router)

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
      path: '/register',
      name: 'register',
      component: () => import('./views/Register')
    },
    {
      path: '/users',
      name: 'users',
      component: () => import('./views/Users')
    },
    {
      path: '/adduser',
      name: 'adduser',
      component: () => import('./views/AddUser')
    }

  ]
})
