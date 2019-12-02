import Vue from 'vue'
import App from './App.vue'
import router from './router'
import axios from 'axios'
import store from "./store";
import ElementUI from 'element-ui';
import 'element-ui/lib/theme-chalk/index.css';
import Antd from 'ant-design-vue';
import 'ant-design-vue/dist/antd.css';

import VueCookies from 'vue-cookies'
Vue.use(VueCookies);
Vue.prototype.$axios = axios;

Vue.use(Antd);
Vue.use(ElementUI);
Vue.config.productionTip = false;

//配置默认请求地址
axios.defaults.baseURL = 'http://localhost:9999/';


//使用钩子函数对路由进行权限跳转
// router.beforeEach((to, from, next) => {
//   let cookieRoles = window.$cookies.get("role");
//   if(to.path === '/'){
//     next();
//   } else if (!cookieRoles && to.path !== '/login') { // cookie中有登陆用户信息跳转页面，否则到登陆页面
//     next('/login');
//   } else if (to.meta.permission) {// 如果该页面配置了权限属性（自定义permission）
//     // 如果是管理员权限则可进入
//     roles.indexOf('admin') > -1 ? next() : next('/403');
//   } else {
//     next();
//   }
// });


new Vue({
  router,
  store,
  render: h => h(App)
}).$mount('#app');
