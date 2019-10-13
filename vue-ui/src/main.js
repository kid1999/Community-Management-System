import Vue from 'vue'
import App from './App.vue'
import router from './router'
import axios from 'axios'
import ElementUI from 'element-ui';
import 'element-ui/lib/theme-chalk/index.css';
import store from "./store";


Vue.use(ElementUI);
Vue.config.productionTip = false;

//配置默认请求地址
axios.defaults.baseURL = 'http://localhost:9999/';

new Vue({
  router,
  store,
  render: h => h(App)
}).$mount('#app');
