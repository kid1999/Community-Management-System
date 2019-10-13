import Vue from 'vue'; //首先引入vue
import Vuex from 'vuex'; //引入vuex
Vue.use(Vuex);

export default new Vuex.Store({
  state: {
    // state 类似 data
    //这里面写入数据
    isLogin:false,
  },
  getters:{
    // getters 类似 computed
    // 在这里面写个方法
  },
  mutations:{
    // mutations 类似methods
    // 写方法对数据做出更改(同步操作)

    Login(state){
      state.isLogin = true;
    },
    Logout(state){
      state.isLogin = false;
    }
  },
  actions:{
    // actions 类似methods
    // 写方法对数据做出更改(异步操作)
  }
})
